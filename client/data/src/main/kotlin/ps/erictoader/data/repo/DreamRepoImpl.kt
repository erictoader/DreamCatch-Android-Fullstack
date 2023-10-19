package ps.erictoader.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.data.cache.abstraction.DreamCacheProxy
import ps.erictoader.data.cache.abstraction.UserCacheProxy
import ps.erictoader.data.remote.abstraction.DreamRemoteProxy
import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.repo.DreamRepo
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

class DreamRepoImpl(
    private val remoteProxy: DreamRemoteProxy,
    private val cacheProxy: DreamCacheProxy,
    private val userCacheProxy: UserCacheProxy
) : DreamRepo {

    override suspend fun getRecentEntries(): List<Dream> =
        withContext(Dispatchers.IO) {
            remoteProxy.getRecentEntries().map { it.mapToDomainModel() }
        }

    override suspend fun getDreamModules(): List<DreamModule> =
        withContext(Dispatchers.IO) {
            remoteProxy.getDreamModules().map { it.mapToDomainModel() }
        }

    override suspend fun addEntry(newEntry: NewDreamEntry): Unit =
        withContext(Dispatchers.IO) {
            remoteProxy.addEntry(newEntry)
        }

    override suspend fun cleanupTagFromAllEntries(tag: String): Unit =
        withContext(Dispatchers.IO) {
            remoteProxy.cleanupTagFromAllEntries(tag)
        }
}
