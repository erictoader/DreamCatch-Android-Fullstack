package com.erictoader.event

sealed class UserQueryEvent : QueryEvent {

    object GetAll : UserQueryEvent()

    data class Login(val username: String, val password: String): UserQueryEvent()
}