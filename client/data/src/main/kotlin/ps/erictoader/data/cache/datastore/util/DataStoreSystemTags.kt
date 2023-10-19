package ps.erictoader.data.cache.datastore.util

lateinit var SYSTEM_TAGS: Set<String>

fun isSystemTag(tagName: String): Boolean = SYSTEM_TAGS.contains(tagName)
