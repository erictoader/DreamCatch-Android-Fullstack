package com.erictoader.response.model

data class DreamModuleModel(
    val tag: String,
    val items: List<DreamModel>
) : Model
