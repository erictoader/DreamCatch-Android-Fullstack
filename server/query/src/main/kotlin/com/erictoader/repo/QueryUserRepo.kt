package com.erictoader.repo

import com.erictoader.entity.User
import org.springframework.stereotype.Repository

@Repository
interface QueryUserRepo : ReadOnlyRepository<User, Int> {

    fun findByUsernameAndPassword(username: String, password: String): User?
}