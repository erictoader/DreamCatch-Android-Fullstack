package com.erictoader.event

sealed class DreamCommandEvent : CommandEvent {

    data class Create(
        val userId: Int,
        val description: String,
        val tags: List<String>,
        val sleepDuration: Float,
        val energyLevel: Float,
        val stressLevel: Float
    ) : DreamCommandEvent()

    data class Cleanup(
        val userId: Int,
        val tag: String
    ) : DreamCommandEvent()
}
