package com.erictoader.service

import com.erictoader.TAG_SEPARATOR
import com.erictoader.entity.Dream
import com.erictoader.projection.DreamProjector
import com.erictoader.response.Response
import com.erictoader.response.Response.SingleResponse
import com.erictoader.response.ResponseStatus.SUCCESS
import com.erictoader.response.model.DreamModel
import com.erictoader.repo.CommandDreamRepo
import com.erictoader.request.DreamRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommandDreamService @Autowired constructor(
    private val dreamRepo: CommandDreamRepo
) {
    private val projector = DreamProjector

    fun create(request: DreamRequest.Create): Response<DreamModel> {
        projector.deprecate()

        return SingleResponse(
            SUCCESS,
            dreamRepo.save(
                Dream(
                    userId = request.userId,
                    description = request.description,
                    tags = packTags(request.tags),
                    sleepDuration = request.sleepDuration,
                    energyLevel = request.energyLevel,
                    stressLevel = request.stressLevel
                )
            ).mapToModel()
        )
    }

    fun cleanupTagFromEntriesForUser(request: DreamRequest.Cleanup) {
        projector.deprecate()

        dreamRepo.cleanupTagFromEntriesForUser(
            userId = request.userId,
            tag = request.tag,
            tagAndSeparator = request.tag + TAG_SEPARATOR,
            separatorAndTag = TAG_SEPARATOR + request.tag
        )
    }

    private fun packTags(tags: List<String>): String = tags.joinToString(TAG_SEPARATOR)
}