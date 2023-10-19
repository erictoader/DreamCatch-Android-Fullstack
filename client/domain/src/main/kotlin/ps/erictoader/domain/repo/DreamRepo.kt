package ps.erictoader.domain.repo

import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

interface DreamRepo {

    suspend fun getRecentEntries(): List<Dream>

    suspend fun getDreamModules(): List<DreamModule>

    suspend fun addEntry(newEntry: NewDreamEntry)

    suspend fun cleanupTagFromAllEntries(tag: String)
}
