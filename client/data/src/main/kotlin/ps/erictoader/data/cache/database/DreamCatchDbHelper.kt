package ps.erictoader.data.cache.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.CALCULATE_SCORE_TRIGGER
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_DATE
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_DESCRIPTION
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_ENERGY_LEVEL
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_ID
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_SLEEP_DURATION
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_SLEEP_SCORE
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_STRESS
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.COLUMN_NAME_TAGS
import ps.erictoader.data.cache.database.DreamCatchContract.DreamCatchEntry.TABLE_NAME

class DreamCatchDbHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ITEM_TABLE)
        db.execSQL(SQL_CALCULATE_SCORE_TRIGGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DROP_ITEM_TABLE)
        db.execSQL(SQL_DROP_CALCULATE_SCORE_TRIGGER)
        onCreate(db)
    }

    companion object {
        private const val SQL_CREATE_ITEM_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY, " +
                    "$COLUMN_NAME_DESCRIPTION TEXT, " +
                    "$COLUMN_NAME_TAGS TEXT, " +
                    "$COLUMN_NAME_SLEEP_DURATION FLOAT NOT NULL DEFAULT 0, " +
                    "$COLUMN_NAME_ENERGY_LEVEL FLOAT NOT NULL DEFAULT 0, " +
                    "$COLUMN_NAME_STRESS FLOAT NOT NULL DEFAULT 0, " +
                    "$COLUMN_NAME_SLEEP_SCORE FLOAT NOT NULL DEFAULT 0, " +
                    "$COLUMN_NAME_DATE LONG NOT NULL DEFAULT CURRENT_TIMESTAMP)"

        private const val SQL_CALCULATE_SCORE_TRIGGER =
            "CREATE TRIGGER $CALCULATE_SCORE_TRIGGER " +
                    "AFTER INSERT ON $TABLE_NAME " +
                    "BEGIN " +
                    "UPDATE $TABLE_NAME " +
                    "SET $COLUMN_NAME_SLEEP_SCORE = $COLUMN_NAME_SLEEP_DURATION * $COLUMN_NAME_ENERGY_LEVEL / ($COLUMN_NAME_STRESS + 1) " +
                    "WHERE $COLUMN_NAME_ID = NEW.$COLUMN_NAME_ID; " +
                    "END;"

        private const val SQL_DROP_CALCULATE_SCORE_TRIGGER =
            "DROP TRIGGER IF EXISTS $CALCULATE_SCORE_TRIGGER;"

        private const val SQL_DROP_ITEM_TABLE =
            "DROP TABLE IF EXISTS $TABLE_NAME"

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DreamCatch.db"
    }
}
