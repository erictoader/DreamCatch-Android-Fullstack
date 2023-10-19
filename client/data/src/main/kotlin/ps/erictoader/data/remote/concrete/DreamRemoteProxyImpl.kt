package ps.erictoader.data.remote.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.data.model.DreamData
import ps.erictoader.data.model.DreamModuleData
import ps.erictoader.data.remote.abstraction.DreamRemoteProxy
import ps.erictoader.data.remote.api.DreamApi
import ps.erictoader.data.remote.request.DreamRequest
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

class DreamRemoteProxyImpl(
    private val api: DreamApi
) : DreamRemoteProxy {

    override suspend fun getRecentEntries(): List<DreamData> =
        withContext(Dispatchers.IO) {
            val response = api.getAll(1)
            response.data
        }

    override suspend fun getDreamModules(): List<DreamModuleData> =
        withContext(Dispatchers.IO) {
            val response = api.getDreamModules(1)
            response.data
        }

    override suspend fun addEntry(newEntry: NewDreamEntry): DreamData =
        withContext(Dispatchers.IO) {
            val response = api.create(
                DreamRequest.Create(
                    userId = 1,
                    description = newEntry.description,
                    tags = newEntry.tags,
                    sleepDuration = newEntry.sleepDuration.value,
                    energyLevel = newEntry.energyLevel.value,
                    stressLevel = newEntry.stress.value
                )
            )
            response.data
        }

    override suspend fun cleanupTagFromAllEntries(tag: String): Unit =
        withContext(Dispatchers.IO) {
            api.cleanupTag(
                DreamRequest.Cleanup(
                    userId = 1,
                    tag = tag
                )
            )
        }
}
