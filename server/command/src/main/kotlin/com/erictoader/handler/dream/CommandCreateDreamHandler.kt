package com.erictoader.handler.dream

import com.erictoader.TAG_SEPARATOR
import com.erictoader.entity.Dream
import com.erictoader.event.DreamCommandEvent
import com.erictoader.handler.EventHandler
import com.erictoader.repo.CommandDreamRepo
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommandCreateDreamHandler @Autowired constructor(
    private val repo: CommandDreamRepo
) : EventHandler<DreamCommandEvent.Create> {

    override fun handle(event: DreamCommandEvent.Create) =
        Response.SingleResponse(
            ResponseStatus.SUCCESS,
            repo.save(
                Dream(
                    userId = event.userId,
                    description = event.description,
                    tags = packTags(event.tags),
                    sleepDuration = event.sleepDuration,
                    energyLevel = event.energyLevel,
                    stressLevel = event.stressLevel
                )
            ).mapToModel()
        )

    private fun packTags(tags: List<String>): String = tags.joinToString(TAG_SEPARATOR)
}