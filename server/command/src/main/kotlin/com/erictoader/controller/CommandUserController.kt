package com.erictoader.controller

import com.erictoader.event.UserCommandEvent
import com.erictoader.mediator.UserCommandEventMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RequestMapping("user")
@RestController
class CommandUserController @Autowired constructor(
    private val eventMediator: UserCommandEventMediator
) {
    @PostMapping("/register")
    fun register(@RequestBody requestEvent: UserCommandEvent.Register) = eventMediator.invoke(requestEvent)

    @PutMapping("/update")
    fun update(@RequestBody requestEvent: UserCommandEvent.Update) = eventMediator.invoke(requestEvent)

}