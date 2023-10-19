package com.erictoader.mediator

import com.erictoader.event.DreamCommandEvent
import com.erictoader.handler.dream.CommandCleanupDreamHandler
import com.erictoader.handler.dream.CommandCreateDreamHandler
import com.erictoader.projection.DreamProjector
import com.erictoader.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DreamCommandEventMediator @Autowired constructor(
    private val createDreamHandler: CommandCreateDreamHandler,
    private val cleanupDreamHandler: CommandCleanupDreamHandler
) {

    private val projector = DreamProjector

    fun invoke(event: DreamCommandEvent): Response<*> {
        projector.deprecate()

        return when (event) {
            is DreamCommandEvent.Create -> createDreamHandler.handle(event)
            is DreamCommandEvent.Cleanup -> cleanupDreamHandler.handle(event)
        }
    }
}