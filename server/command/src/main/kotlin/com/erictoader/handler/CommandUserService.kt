package com.erictoader.service

import com.erictoader.entity.User
import com.erictoader.response.Response
import com.erictoader.response.Response.SingleResponse
import com.erictoader.response.ResponseStatus.ERROR_EMAIL_EXISTS
import com.erictoader.response.ResponseStatus.ERROR_EMAIL_INVALID
import com.erictoader.response.ResponseStatus.ERROR_PASSWORD_INVALID
import com.erictoader.response.ResponseStatus.ERROR_PASSWORD_TOO_SHORT
import com.erictoader.response.ResponseStatus.ERROR_USERNAME_EXISTS
import com.erictoader.response.ResponseStatus.ERROR_USERNAME_TOO_SHORT
import com.erictoader.response.ResponseStatus.ERROR_USER_NOT_FOUND
import com.erictoader.response.ResponseStatus.SUCCESS
import com.erictoader.response.model.UserModel
import com.erictoader.repo.CommandUserRepo
import com.erictoader.request.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommandUserService @Autowired constructor(
    private val userRepo: CommandUserRepo
) {

    fun register(request: UserRequest.Register): Response<UserModel> {
        if (request.username.length < MINIMUM_USERNAME_LENGTH) return SingleResponse(ERROR_USERNAME_TOO_SHORT)
        if (request.password.length < MINIMUM_PASSWORD_LENGTH) return SingleResponse(ERROR_PASSWORD_TOO_SHORT)
        if (!request.email.matches(EMAIL_REGEX)) return SingleResponse(ERROR_EMAIL_INVALID)
        if (!request.password.matches(PASSWORD_REGEX)) return SingleResponse(ERROR_PASSWORD_INVALID)

        if (userRepo.findByEmail(request.email) != null) return SingleResponse(ERROR_EMAIL_EXISTS)
        if (userRepo.findByUsername(request.username) != null) return SingleResponse(ERROR_USERNAME_EXISTS)

        return SingleResponse(
            SUCCESS,
            userRepo.save(
                User(
                    email = request.email,
                    username = request.username,
                    password = request.password,
                    firstName = request.firstName,
                    lastName = request.lastName
                )
            ).mapToModel()
        )
    }

    fun update(request: UserRequest.Update): Response<UserModel> {
        if (request.password.length < MINIMUM_PASSWORD_LENGTH) return SingleResponse(ERROR_PASSWORD_TOO_SHORT)
        if (!request.password.matches(PASSWORD_REGEX)) return SingleResponse(ERROR_PASSWORD_INVALID)
        if (!request.email.matches(EMAIL_REGEX)) return SingleResponse(ERROR_EMAIL_INVALID)

        val existing = userRepo.findById(request.id).orElse(null) ?: return SingleResponse(ERROR_USER_NOT_FOUND)
        if (existing.email != request.email) {
            val userByEmail = userRepo.findByEmail(request.email)
            if (userByEmail != null) return SingleResponse(ERROR_EMAIL_EXISTS)
        }

        return SingleResponse(
            SUCCESS,
            userRepo.save(
                existing.copy(
                    firstName = request.firstName,
                    lastName = request.lastName,
                    email = request.email,
                    password = request.password
                )
            ).mapToModel()
        )
    }

    companion object {
        private const val MINIMUM_USERNAME_LENGTH = 6
        private const val MINIMUM_PASSWORD_LENGTH = 8
        private val EMAIL_REGEX = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)".toRegex()
        private val PASSWORD_REGEX = ".*\\d.*".toRegex()
    }
}