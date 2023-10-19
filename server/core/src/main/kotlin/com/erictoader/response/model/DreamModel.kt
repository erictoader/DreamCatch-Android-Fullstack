package com.erictoader.response.model

data class DreamModel(
    val description: String,
    val sleepDuration: Float,
    val energyLevel: Float,
    val stressLevel: Float,
    val sleepScore: Float,
    val entryDate: Long
) : Model
