package com.erictoader.mediator

import com.erictoader.event.UserCommandEvent
import com.erictoader.handler.user.CommandRegisterUserHandler
import com.erictoader.handler.user.CommandUpdateUserHandler
import com.erictoader.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserCommandEventMediator @Autowired constructor(
    private val registerUserHandler: CommandRegisterUserHandler,
    private val updateUserHandler: CommandUpdateUserHandler
) {

    fun invoke(event: UserCommandEvent): Response<*> =
        when (event) {
            is UserCommandEvent.Register -> registerUserHandler.handle(event)
            is UserCommandEvent.Update -> updateUserHandler.handle(event)
        }

    companion object {
        const val MINIMUM_USERNAME_LENGTH = 6
        const val MINIMUM_PASSWORD_LENGTH = 8
        val EMAIL_REGEX = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)".toRegex()
        val PASSWORD_REGEX = ".*\\d.*".toRegex()
    }
}