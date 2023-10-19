package ps.erictoader.data.cache.datastore.abstraction

import ps.erictoader.domain.feature.newentry.model.Tag

interface DataStoreTagRepo {

    suspend fun getSystemTags(): List<String>

    suspend fun getUserTags(): List<String>

    suspend fun addUserTag(tag: String): Tag

    suspend fun removeUserTag(tag: String)

    suspend fun cacheSystemTags(systemTags: List<String>)
}
