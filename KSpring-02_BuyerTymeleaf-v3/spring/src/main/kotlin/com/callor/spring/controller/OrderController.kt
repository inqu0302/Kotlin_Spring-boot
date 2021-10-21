package com.callor.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = ["/order"])
class OrderController {

    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun list():String{

        return "order/list"

    }
}