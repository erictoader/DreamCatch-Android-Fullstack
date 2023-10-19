package com.erictoader.controller

import com.erictoader.event.UserQueryEvent
import com.erictoader.mediator.UserQueryEventMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RequestMapping("user")
@RestController
class QueryUserController @Autowired constructor(
    private val eventMediator: UserQueryEventMediator
) {

    @GetMapping("/get")
    fun getAll() = eventMediator.invoke(UserQueryEvent.GetAll)

    @GetMapping("/login")
    fun login(
        @RequestParam("user") username: String,
        @RequestParam("pass") password: String
    ) = eventMediator.invoke(UserQueryEvent.Login(username, password))

}