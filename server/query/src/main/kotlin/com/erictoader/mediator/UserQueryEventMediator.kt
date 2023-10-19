package com.erictoader.mediator

import com.erictoader.event.UserQueryEvent
import com.erictoader.handler.user.QueryGetAllUserHandler
import com.erictoader.handler.user.QueryLoginUserHandler
import com.erictoader.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQueryEventMediator @Autowired constructor(
    private val getAllUserHandler: QueryGetAllUserHandler,
    private val loginUserHandler: QueryLoginUserHandler
) {

    fun invoke(event: UserQueryEvent): Response<*> =
        when (event) {
            is UserQueryEvent.GetAll -> getAllUserHandler.handle(event)
            is UserQueryEvent.Login -> loginUserHandler.handle(event)
        }
}