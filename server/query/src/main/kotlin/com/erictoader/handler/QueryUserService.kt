package com.erictoader.service

import com.erictoader.response.Response
import com.erictoader.response.Response.CollectionResponse
import com.erictoader.response.Response.SingleResponse
import com.erictoader.response.ResponseStatus.ERROR_INCORRECT_CREDENTIALS
import com.erictoader.response.ResponseStatus.SUCCESS
import com.erictoader.response.model.UserModel
import com.erictoader.repo.QueryUserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QueryUserService @Autowired constructor(
    private val userRepo: QueryUserRepo
) {

    fun getAll(): Response<List<UserModel>> {
        val users = userRepo.findAll()
        return CollectionResponse(
            SUCCESS,
            users.map { it.mapToModel() }
        )
    }

    fun login(username: String, password: String): Response<UserModel> {
        val user = userRepo.findByUsernameAndPassword(username, password)
            ?: return SingleResponse(ERROR_INCORRECT_CREDENTIALS)
        return SingleResponse(SUCCESS, user.mapToModel())
    }
}