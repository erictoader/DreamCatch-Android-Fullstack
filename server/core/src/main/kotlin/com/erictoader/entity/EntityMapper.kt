package com.erictoader.entity

import com.erictoader.response.model.Model

interface EntityMapper<M: Model> {

    fun mapToModel(): M
}