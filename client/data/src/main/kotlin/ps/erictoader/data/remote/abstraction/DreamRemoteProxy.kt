package ps.erictoader.data.remote.abstraction

import ps.erictoader.data.model.DreamData
import ps.erictoader.data.model.DreamModuleData
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

interface DreamRemoteProxy {

    suspend fun getRecentEntries(): List<DreamData>

    suspend fun getDreamModules(): List<DreamModuleData>

    suspend fun addEntry(newEntry: NewDreamEntry): DreamData

    suspend fun cleanupTagFromAllEntries(tag: String)
}
