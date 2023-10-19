package com.erictoader.handler

import com.erictoader.event.QueryEvent
import com.erictoader.response.Response

interface EventHandler<E: QueryEvent> {

    fun handle(event: E): Response<*>

}