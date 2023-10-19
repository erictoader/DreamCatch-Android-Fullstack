package com.erictoader.handler

import com.erictoader.event.CommandEvent
import com.erictoader.response.Response

interface EventHandler<E: CommandEvent> {

    fun handle(event: E): Response<*>

}