package com.erictoader.controller

import com.erictoader.event.SystemTagQueryEvent
import com.erictoader.mediator.SystemTagQueryEventMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("*")
@RequestMapping("system-tags")
@RestController
class QuerySystemTagController @Autowired constructor(
    private val eventMediator: SystemTagQueryEventMediator
) {

    @GetMapping("/get")
    fun getAll() = eventMediator.invoke(SystemTagQueryEvent.GetAll)
}