package com.erictoader.controller

import com.erictoader.event.DreamCommandEvent
import com.erictoader.mediator.DreamCommandEventMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RequestMapping("dream")
@RestController
class CommandDreamController @Autowired constructor(
    private val eventMediator: DreamCommandEventMediator
) {
    @PostMapping("/create")
    fun create(@RequestBody requestEvent: DreamCommandEvent.Create) = eventMediator.invoke(requestEvent)

    @PutMapping("/cleanupTag")
    fun cleanupTagFromEntriesForUser(@RequestBody requestEvent: DreamCommandEvent.Cleanup) = eventMediator.invoke(requestEvent)
}