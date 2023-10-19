package com.erictoader.controller

import com.erictoader.event.DreamQueryEvent
import com.erictoader.mediator.DreamQueryEventMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RequestMapping("dream")
@RestController
class QueryDreamController @Autowired constructor(
    private val eventMediator: DreamQueryEventMediator
) {
    @GetMapping("/get")
    fun getAll() = eventMediator.invoke(DreamQueryEvent.GetAll)

    @GetMapping("/getByUserId")
    fun getByUserId(@RequestParam("userId") userId: Int) =
        eventMediator.invoke(DreamQueryEvent.GetAllByUserId(userId))

    @GetMapping("/getByUserIdGroupedByTag")
    fun getByUserIdGroupedByTag(@RequestParam("userId") userId: Int) =
        eventMediator.invoke(DreamQueryEvent.GetAllByUserIdGroupedByTag(userId))
}