package com.erictoader.handler.user

import com.erictoader.event.UserQueryEvent
import com.erictoader.handler.EventHandler
import com.erictoader.repo.QueryUserRepo
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QueryLoginUserHandler @Autowired constructor(
    private val userRepo: QueryUserRepo
) : EventHandler<UserQueryEvent.Login> {

    override fun handle(event: UserQueryEvent.Login): Response<*> {
        val user = userRepo.findByUsernameAndPassword(event.username, event.password)
            ?: return Response.SingleResponse(ResponseStatus.ERROR_INCORRECT_CREDENTIALS, null)
        return Response.SingleResponse(ResponseStatus.SUCCESS, user.mapToModel())
    }
}