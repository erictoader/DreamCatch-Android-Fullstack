package ps.erictoader.domain.feature.newentry.model

import kotlin.properties.Delegates

class Rating(value: Float) {

    var value by Delegates.notNull<Float>()
        private set

    init {
        this.value = if (value.isOutsideRange()) RATING_MAX_VALUE else value
    }

    companion object {
        private const val RATING_MIN_VALUE = 0f
        private const val RATING_MAX_VALUE = 5f

        private fun Float.isOutsideRange() =
            this < RATING_MIN_VALUE || this > RATING_MAX_VALUE
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rating

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
