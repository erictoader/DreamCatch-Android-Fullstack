package ps.erictoader.data.cache.database.operations.concrete

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_DATE
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_DESCRIPTION
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_ENERGY_LEVEL
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_ID
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_SLEEP_DURATION
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_SLEEP_SCORE
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_STRESS
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_TAGS
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.TABLE_NAME
import ps.erictoader.data.cache.database.operations.abstraction.DreamDbOperations
import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

class DreamDbOperationsImpl(
    private val dbHelper: SQLiteOpenHelper
) : DreamDbOperations {

    private var readableDatabase: SQLiteDatabase? = null
    private var writableDatabase: SQLiteDatabase? = null

    private fun getWritableDatabase(): SQLiteDatabase =
        writableDatabase ?: dbHelper.writableDatabase.also {
            writableDatabase = it
        }

    private fun getReadableDatabase(): SQLiteDatabase =
        readableDatabase ?: dbHelper.readableDatabase.also {
            readableDatabase = it
        }

    override fun getAllEntries(): List<Dream> {
        val entryList = getQuery()
        Log.d(TAG, "getAllEntries: $entryList")
        return entryList
    }

    override fun getAllEntriesByTag(tag: String): List<Dream> {
        val selection = "$COLUMN_NAME_TAGS LIKE ?"
        val selectionArgs = arrayOf("%$tag%")

        val entryList = getQuery(
            selection = selection,
            selectionArgs = selectionArgs
        )

        Log.d(TAG, "getAllEntriesByTag $tag: $entryList")
        return entryList
    }

    override fun addNewEntry(entry: NewDreamEntry) {
        val db = getWritableDatabase()
        val values = ContentValues().apply {
            put(COLUMN_NAME_DESCRIPTION, entry.description)
            put(COLUMN_NAME_TAGS, packTags(entry.tags))
            put(COLUMN_NAME_SLEEP_DURATION, entry.sleepDuration.value)
            put(COLUMN_NAME_ENERGY_LEVEL, entry.energyLevel.value)
            put(COLUMN_NAME_STRESS, entry.stress.value)
        }

        db.insert(TABLE_NAME, null, values)
    }

    override fun cleanupTagFromAllEntries(tag: String) {
        val db = getWritableDatabase()
        val query = "UPDATE $TABLE_NAME " +
                "SET $COLUMN_NAME_TAGS = " +
                "REPLACE(" +
                "   REPLACE(" +
                "       REPLACE($COLUMN_NAME_TAGS, '$tag$TAG_SEPARATOR', ''), " +
                "       '$TAG_SEPARATOR$tag', " +
                "       ''), " +
                "   '$tag', " +
                "   '') " +
                "WHERE $COLUMN_NAME_TAGS LIKE '%$tag%'"

        db.execSQL(query)
    }

    override fun closeDatabase() {
        readableDatabase?.close()
        readableDatabase = null
        writableDatabase?.close()
        writableDatabase = null
    }

    private fun getQuery(
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        groupBy: String? = null,
        having: String? = null,
        orderBy: String? = "$COLUMN_NAME_DATE ASC"
    ): List<Dream> {
        val db = getReadableDatabase()
        val projection =
            arrayOf(
                COLUMN_NAME_ID,
                COLUMN_NAME_DESCRIPTION,
                COLUMN_NAME_TAGS,
                COLUMN_NAME_SLEEP_DURATION,
                COLUMN_NAME_ENERGY_LEVEL,
                COLUMN_NAME_STRESS,
                COLUMN_NAME_SLEEP_SCORE,
                "datetime($COLUMN_NAME_DATE)"
            )

        val cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            groupBy,
            having,
            orderBy
        )
        val entryList = mutableListOf<Dream>()

        if (!cursor.moveToFirst()) {
            cursor.close()
            return entryList
        }
        do {
            val desc = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DESCRIPTION))
            val packedTags = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TAGS))
            val duration = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_NAME_SLEEP_DURATION))
            val energy = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_NAME_ENERGY_LEVEL))
            val stress = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_NAME_STRESS))
            val sleepScore = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_NAME_SLEEP_SCORE))
            val date = cursor.getString(cursor.getColumnIndexOrThrow("datetime($COLUMN_NAME_DATE)"))

            entryList.add(
                Dream(
                    description = desc,
//                    tags = unpackTags(packedTags),
                    sleepDuration = duration,
                    energyLevel = energy,
                    stress = stress,
                    sleepScore = sleepScore,
                    entryDate = 0L//date.dateTimeToUnixTimestamp()
                )
            )
        } while (cursor.moveToNext())

        cursor.close()
        return entryList
    }

    private fun packTags(tags: List<String>): String = tags.joinToString(TAG_SEPARATOR)

    private fun unpackTags(packedTags: String): List<String> = packedTags.split(TAG_SEPARATOR)

    companion object {
        private val TAG = DreamDbOperationsImpl::class.java.simpleName
        private const val TAG_SEPARATOR = ";"
        const val DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"
    }
}
