package ps.erictoader.domain.feature.home.model

import ps.erictoader.domain.feature.common.ContentItem

data class DreamModule(
    val tag: String,
    val items: List<Dream>
) : ContentItem {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DreamModule

        if (tag != other.tag) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tag.hashCode()
        result = 31 * result + items.hashCode()
        return result
    }
}
