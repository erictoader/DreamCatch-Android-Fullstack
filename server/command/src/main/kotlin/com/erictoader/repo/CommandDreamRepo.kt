package com.erictoader.repo

import com.erictoader.entity.Dream
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface CommandDreamRepo : JpaRepository<Dream, Int> {

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE `dream` d " +
                "SET d.tags = " +
                "REPLACE(" +
                "   REPLACE(" +
                "       REPLACE(d.tags, :tagAndSeparator, ''), " +
                "       :separatorAndTag, " +
                "       ''), " +
                "   :systemtag, " +
                "   '') " +
                "WHERE d.tags LIKE %:systemtag% AND d.user_id = :userId",
        nativeQuery = true
    )
    fun cleanupTagFromEntriesForUser(
        @Param("userId") userId: Int,
        @Param("systemtag") tag: String,
        @Param("tagAndSeparator") tagAndSeparator: String,
        @Param("separatorAndTag") separatorAndTag: String
    )
}