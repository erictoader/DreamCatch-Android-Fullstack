package ps.erictoader.data.cache.database.operations.abstraction

import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

interface DreamDbOperations {

    fun getAllEntries(): List<Dream>

    fun getAllEntriesByTag(tag: String): List<Dream>

    fun addNewEntry(entry: NewDreamEntry)

    fun cleanupTagFromAllEntries(tag: String)

    /**
     * Only call this when freeing up memory is needed.
     * It is not worth closing the database connection after every query.
     * Rather, closing the connection should happen on relevant activity
     * lifecycle events such as onPause.
     */
    fun closeDatabase()
}
