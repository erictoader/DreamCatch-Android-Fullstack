package com.erictoader.request

sealed class UserRequest {

    data class Register(
        val email: String,
        val username: String,
        val password: String,
        val firstName: String,
        val lastName: String
    ) : UserRequest()

    data class Update(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
    ) : UserRequest()
}
