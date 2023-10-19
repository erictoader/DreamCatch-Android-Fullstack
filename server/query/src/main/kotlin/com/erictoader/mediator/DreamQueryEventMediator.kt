package com.erictoader.mediator

import com.erictoader.event.DreamQueryEvent
import com.erictoader.handler.dream.QueryGetAllByUserIdDreamHandler
import com.erictoader.handler.dream.QueryGetAllByUserIdGroupedByTagDreamHandler
import com.erictoader.handler.dream.QueryGetAllDreamHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DreamQueryEventMediator @Autowired constructor(
    private val getAllDreamHandler: QueryGetAllDreamHandler,
    private val getAllByUserIdDreamHandler: QueryGetAllByUserIdDreamHandler,
    private val getAllByUserIdGroupedByTagDreamHandler: QueryGetAllByUserIdGroupedByTagDreamHandler
) {

    fun invoke(event: DreamQueryEvent) =
        when (event) {
            is DreamQueryEvent.GetAll -> getAllDreamHandler.handle(event)
            is DreamQueryEvent.GetAllByUserId -> getAllByUserIdDreamHandler.handle(event)
            is DreamQueryEvent.GetAllByUserIdGroupedByTag -> getAllByUserIdGroupedByTagDreamHandler.handle(event)
        }
}