package com.erictoader.mediator

import com.erictoader.event.SystemTagQueryEvent
import com.erictoader.handler.systemtag.QueryGetAllSystemTagHandler
import com.erictoader.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SystemTagQueryEventMediator @Autowired constructor(
    private val getAllSystemTagHandler: QueryGetAllSystemTagHandler
) {

    fun invoke(event: SystemTagQueryEvent): Response<*> {

        return when (event) {
            is SystemTagQueryEvent.GetAll -> getAllSystemTagHandler.handle(event)
        }
    }

}