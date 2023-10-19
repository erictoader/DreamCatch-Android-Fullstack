package com.erictoader.response

object ResponseStatus {
    val SUCCESS = Pair(200, "Request successful")

    val ERROR_EMAIL_EXISTS = Pair(601, "E-mail address already in use")
    val ERROR_USERNAME_EXISTS = Pair(602, "Username already in use")
    val ERROR_EMAIL_INVALID = Pair(603, "E-mail address is invalid")
    val ERROR_PASSWORD_TOO_SHORT = Pair(604, "Password is too short")
    val ERROR_PASSWORD_INVALID = Pair(605, "Password invalid. Must contain at least one digit")
    val ERROR_USERNAME_TOO_SHORT = Pair(606, "Username is too short")
    val ERROR_INCORRECT_CREDENTIALS = Pair(607, "Incorrect username or password")
    val ERROR_USER_NOT_FOUND = Pair(608, "User not found")
}