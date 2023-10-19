package com.erictoader.repo

import com.erictoader.entity.SystemTag
import org.springframework.stereotype.Repository

@Repository
interface QuerySystemTagRepo : ReadOnlyRepository<SystemTag, Int>