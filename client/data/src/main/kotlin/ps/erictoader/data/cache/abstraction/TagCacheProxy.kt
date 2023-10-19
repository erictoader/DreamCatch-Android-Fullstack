package ps.erictoader.data.cache.abstraction

import ps.erictoader.domain.feature.newentry.model.Tag

interface TagCacheProxy {

    suspend fun cacheSystemTags(systemTags: List<String>)

    suspend fun getSystemTags(): List<String>

    suspend fun getUserTags(): List<String>

    suspend fun addUserTag(tag: String): Tag

    suspend fun removeUserTag(tag: String)
}
