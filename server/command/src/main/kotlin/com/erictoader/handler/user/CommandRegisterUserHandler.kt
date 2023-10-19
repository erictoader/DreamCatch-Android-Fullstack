package com.erictoader.handler.user

import com.erictoader.entity.User
import com.erictoader.event.UserCommandEvent
import com.erictoader.handler.EventHandler
import com.erictoader.mediator.UserCommandEventMediator.Companion.EMAIL_REGEX
import com.erictoader.mediator.UserCommandEventMediator.Companion.MINIMUM_PASSWORD_LENGTH
import com.erictoader.mediator.UserCommandEventMediator.Companion.MINIMUM_USERNAME_LENGTH
import com.erictoader.mediator.UserCommandEventMediator.Companion.PASSWORD_REGEX
import com.erictoader.repo.CommandUserRepo
import com.erictoader.response.Response
import com.erictoader.response.Response.SingleResponse
import com.erictoader.response.ResponseStatus.ERROR_EMAIL_EXISTS
import com.erictoader.response.ResponseStatus.ERROR_EMAIL_INVALID
import com.erictoader.response.ResponseStatus.ERROR_PASSWORD_INVALID
import com.erictoader.response.ResponseStatus.ERROR_PASSWORD_TOO_SHORT
import com.erictoader.response.ResponseStatus.ERROR_USERNAME_EXISTS
import com.erictoader.response.ResponseStatus.ERROR_USERNAME_TOO_SHORT
import com.erictoader.response.ResponseStatus.SUCCESS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommandRegisterUserHandler @Autowired constructor(
    private val repo: CommandUserRepo
) : EventHandler<UserCommandEvent.Register> {

    override fun handle(event: UserCommandEvent.Register): Response<*> {
        if (event.username.length < MINIMUM_USERNAME_LENGTH) return SingleResponse(ERROR_USERNAME_TOO_SHORT, null)
        if (event.password.length < MINIMUM_PASSWORD_LENGTH) return SingleResponse(ERROR_PASSWORD_TOO_SHORT, null)
        if (!event.email.matches(EMAIL_REGEX)) return SingleResponse(ERROR_EMAIL_INVALID, null)
        if (!event.password.matches(PASSWORD_REGEX)) return SingleResponse(ERROR_PASSWORD_INVALID, null)

        if (repo.findByEmail(event.email) != null) return SingleResponse(ERROR_EMAIL_EXISTS, null)
        if (repo.findByUsername(event.username) != null) return SingleResponse(ERROR_USERNAME_EXISTS, null)

        return SingleResponse(
            SUCCESS,
            repo.save(
                User(
                    email = event.email,
                    username = event.username,
                    password = event.password,
                    firstName = event.firstName,
                    lastName = event.lastName
                )
            ).mapToModel()
        )
    }
}