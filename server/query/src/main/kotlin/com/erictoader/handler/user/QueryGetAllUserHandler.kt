package com.erictoader.handler.user

import com.erictoader.event.UserQueryEvent
import com.erictoader.handler.EventHandler
import com.erictoader.repo.QueryUserRepo
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class QueryGetAllUserHandler @Autowired constructor(
    private val userRepo: QueryUserRepo
) : EventHandler<UserQueryEvent.GetAll> {

    override fun handle(event: UserQueryEvent.GetAll): Response<*> {
        val users = userRepo.findAll()
        return Response.CollectionResponse(
            ResponseStatus.SUCCESS,
            users.map { it.mapToModel() }
        )
    }
}