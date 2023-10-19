package com.erictoader.handler.dream

import com.erictoader.TAG_SEPARATOR
import com.erictoader.event.DreamCommandEvent
import com.erictoader.handler.EventHandler
import com.erictoader.repo.CommandDreamRepo
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommandCleanupDreamHandler @Autowired constructor(
    private val repo: CommandDreamRepo
) : EventHandler<DreamCommandEvent.Cleanup> {

    override fun handle(event: DreamCommandEvent.Cleanup): Response<*> {
        repo.cleanupTagFromEntriesForUser(
            userId = event.userId,
            tag = event.tag,
            tagAndSeparator = event.tag + TAG_SEPARATOR,
            separatorAndTag = TAG_SEPARATOR + event.tag
        )

        return Response.SingleResponse(ResponseStatus.SUCCESS, null)
    }
}