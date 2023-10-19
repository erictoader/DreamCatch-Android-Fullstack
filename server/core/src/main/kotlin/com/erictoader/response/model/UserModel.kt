package com.erictoader.response.model

data class UserModel(
    val id: Int?,
    val email: String,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String
) : Model
