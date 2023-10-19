package com.erictoader.repo

import com.erictoader.entity.Dream
import org.springframework.stereotype.Repository

@Repository
interface QueryDreamRepo : ReadOnlyRepository<Dream, Int> {

    fun findAllByUserId(userId: Int): List<Dream>
}
