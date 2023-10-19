package com.erictoader.handler.user

import com.erictoader.event.UserCommandEvent
import com.erictoader.handler.EventHandler
import com.erictoader.mediator.UserCommandEventMediator.Companion.EMAIL_REGEX
import com.erictoader.mediator.UserCommandEventMediator.Companion.MINIMUM_PASSWORD_LENGTH
import com.erictoader.mediator.UserCommandEventMediator.Companion.PASSWORD_REGEX
import com.erictoader.repo.CommandUserRepo
import com.erictoader.response.Response
import com.erictoader.response.Response.SingleResponse
import com.erictoader.response.ResponseStatus.ERROR_EMAIL_EXISTS
import com.erictoader.response.ResponseStatus.ERROR_EMAIL_INVALID
import com.erictoader.response.ResponseStatus.ERROR_PASSWORD_INVALID
import com.erictoader.response.ResponseStatus.ERROR_PASSWORD_TOO_SHORT
import com.erictoader.response.ResponseStatus.ERROR_USER_NOT_FOUND
import com.erictoader.response.ResponseStatus.SUCCESS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommandUpdateUserHandler @Autowired constructor(
    private val repo: CommandUserRepo
) : EventHandler<UserCommandEvent.Update> {

    override fun handle(event: UserCommandEvent.Update): Response<*> {
        if (event.password.length < MINIMUM_PASSWORD_LENGTH) return SingleResponse(ERROR_PASSWORD_TOO_SHORT, null)
        if (!event.password.matches(PASSWORD_REGEX)) return SingleResponse(ERROR_PASSWORD_INVALID, null)
        if (!event.email.matches(EMAIL_REGEX)) return SingleResponse(ERROR_EMAIL_INVALID, null)

        val existing = repo.findById(event.id).orElse(null) ?: return SingleResponse(ERROR_USER_NOT_FOUND, null)
        if (existing.email != event.email) {
            val userByEmail = repo.findByEmail(event.email)
            if (userByEmail != null) return SingleResponse(ERROR_EMAIL_EXISTS, null)
        }

        return SingleResponse(
            SUCCESS,
            repo.save(
                existing.copy(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    email = event.email,
                    password = event.password
                )
            ).mapToModel()
        )
    }
}