package ps.erictoader.domain.repo

import ps.erictoader.domain.feature.newentry.model.Tag

interface TagRepo {

    suspend fun fetchAndCacheSystemTags()

    suspend fun getTags(): List<Tag>

    suspend fun addUserTag(tag: String): Tag

    suspend fun removeTag(tag: String)
}
