package ps.erictoader.data.cache.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.data.cache.abstraction.TagCacheProxy
import ps.erictoader.data.cache.datastore.abstraction.DataStoreTagRepo
import ps.erictoader.domain.feature.newentry.model.Tag

class TagCacheProxyImpl(
    private val dataSoreTagRepo: DataStoreTagRepo
) : TagCacheProxy {

    override suspend fun cacheSystemTags(systemTags: List<String>) =
        withContext(Dispatchers.IO) {
            dataSoreTagRepo.cacheSystemTags(systemTags)
        }

    override suspend fun getSystemTags(): List<String> =
        withContext(Dispatchers.IO) {
            dataSoreTagRepo.getSystemTags()
        }

    override suspend fun getUserTags(): List<String> =
        withContext(Dispatchers.IO) {
            dataSoreTagRepo.getUserTags()
        }

    override suspend fun addUserTag(tag: String): Tag =
        withContext(Dispatchers.IO) {
            dataSoreTagRepo.addUserTag(tag)
        }

    override suspend fun removeUserTag(tag: String) =
        withContext(Dispatchers.IO) {
            dataSoreTagRepo.removeUserTag(tag)
        }
}
