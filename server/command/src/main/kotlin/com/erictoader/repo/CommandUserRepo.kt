package com.erictoader.repo

import com.erictoader.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommandUserRepo : JpaRepository<User, Int> {

    fun findByUsername(username: String): User?

    fun findByEmail(email: String): User?
}