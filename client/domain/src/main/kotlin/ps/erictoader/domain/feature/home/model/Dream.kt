package ps.erictoader.domain.feature.home.model

import ps.erictoader.domain.feature.common.ContentItem

data class Dream(
    val description: String,
    val sleepDuration: Float,
    val energyLevel: Float,
    val stress: Float,
    val sleepScore: Float,
    val entryDate: Long
) : ContentItem
