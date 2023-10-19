package ps.erictoader.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import ps.erictoader.data.cache.abstraction.TagCacheProxy
import ps.erictoader.data.remote.abstraction.TagRemoteProxy
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.domain.repo.TagRepo

class TagRepoImpl(
    private val remoteProxy: TagRemoteProxy,
    private val cacheProxy: TagCacheProxy
) : TagRepo {

    override suspend fun fetchAndCacheSystemTags(): Unit =
        withContext(Dispatchers.IO) {
            val systemTags = remoteProxy.getSystemTags()
            cacheProxy.cacheSystemTags(systemTags.map { it.mapToDomainModel() })
        }

    override suspend fun getTags(): List<Tag> =
        withContext(Dispatchers.IO) {
            val systemTags = async { getSystemTags().map { Tag(name = it, isEditable = false) } }
            val userTags = async { getUserTags().map { Tag(name = it, isEditable = true) } }

            systemTags.await() + userTags.await()
        }

    private suspend fun getSystemTags(): List<String> =
        withContext(Dispatchers.IO) {
            cacheProxy.getSystemTags()
        }

    private suspend fun getUserTags(): List<String> =
        withContext(Dispatchers.IO) {
            cacheProxy.getUserTags()
        }

    override suspend fun addUserTag(tag: String): Tag =
        withContext(Dispatchers.IO) {
            cacheProxy.addUserTag(tag)
        }

    override suspend fun removeTag(tag: String) =
        withContext(Dispatchers.IO) {
            cacheProxy.removeUserTag(tag)
        }
}
