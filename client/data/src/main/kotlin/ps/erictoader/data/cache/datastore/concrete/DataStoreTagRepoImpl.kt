package ps.erictoader.data.cache.datastore.concrete

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first
import ps.erictoader.data.cache.datastore.abstraction.DataStoreTagRepo
import ps.erictoader.data.cache.datastore.util.DREAM_CATCH_SYSTEM_TAGS
import ps.erictoader.data.cache.datastore.util.DREAM_CATCH_USER_TAGS
import ps.erictoader.domain.feature.newentry.exception.TagExistsException
import ps.erictoader.domain.feature.newentry.model.Tag

class DataStoreTagRepoImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreTagRepo {

    private val systemTagsKey = stringSetPreferencesKey(DREAM_CATCH_SYSTEM_TAGS)
    private val userTagsKey = stringSetPreferencesKey(DREAM_CATCH_USER_TAGS)

    override suspend fun getSystemTags(): List<String> {
        val preferences = dataStore.data.first()
        return preferences[systemTagsKey]?.toList() ?: listOf()
    }

    override suspend fun getUserTags(): List<String> {
        val preferences = dataStore.data.first()
        return preferences[userTagsKey]?.toList() ?: listOf()
    }

    override suspend fun addUserTag(tag: String): Tag {
        dataStore.data.first()[userTagsKey]?.let { tagSet ->
            if (tagSet.contains(tag)) throw TagExistsException(tag)
        }
        dataStore.edit {
            it[userTagsKey] = it[userTagsKey]?.plus(tag) ?: setOf()
        }
        return Tag(
            name = tag,
            isEditable = true
        )
    }

    override suspend fun removeUserTag(tag: String) {
        dataStore.edit {
            it[userTagsKey] = it[userTagsKey]?.minus(tag) ?: setOf()
        }
    }

    override suspend fun cacheSystemTags(systemTags: List<String>) {
        dataStore.edit {
            it[systemTagsKey] = systemTags.toSet()
        }
    }
}
