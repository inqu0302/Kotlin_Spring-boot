package com.callor.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HomeController {

    /**
     * Spring 일반 Controller 에서 method가 문자열을 return 하면
     * view 파일을 열어서 rendering 하여 client로 보냄
     *
     * Restful API 방식
     * RestController 또는 method 에 @ResponseBody 가 부착되면
     * 문자열을 그대로 client 로 보냄
     */
    @RequestMapping(value=["/"], method = [RequestMethod.GET])
    fun home() : String{
        return "home"
    }

}