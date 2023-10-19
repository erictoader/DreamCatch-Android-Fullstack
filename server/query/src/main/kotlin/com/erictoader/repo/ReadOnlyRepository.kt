package com.erictoader.repo

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.*

@NoRepositoryBean
interface ReadOnlyRepository<T, ID> : Repository<T, ID> {

    fun findById(id: ID): Optional<T>

    fun existsById(id: ID): Boolean

    fun findAll(): List<T>

    fun count(): Long

}