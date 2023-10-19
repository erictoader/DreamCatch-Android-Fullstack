package com.erictoader.event

sealed class UserCommandEvent : CommandEvent {

    data class Register(
        val email: String,
        val username: String,
        val password: String,
        val firstName: String,
        val lastName: String
    ) : UserCommandEvent()

    data class Update(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
    ) : UserCommandEvent()
}
