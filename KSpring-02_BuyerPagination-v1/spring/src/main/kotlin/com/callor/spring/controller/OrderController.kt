package com.callor.spring.controller

import com.callor.spring.config.logger
import com.callor.spring.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = ["/order"])
class OrderController(val orService:OrderService) {

    // localhost:8080/order or localhost:8080/order/
    @RequestMapping(value = ["","/"], method = [RequestMethod.GET])
    fun list(model:Model, pageable: Pageable):String{

        val salesList = orService.selectAll(pageable)
        model["SALES"] = salesList

        return "order/list"

    }

    @RequestMapping(value = ["/sub_page"], method=[RequestMethod.GET])
    fun subPage(model: Model, pageable: Pageable):String{

        val salesList = orService.selectAll(pageable)
        model["SALES"] = salesList

        return "order/sub_page"
    }

    @RequestMapping(value =["list/{page}"], method=[RequestMethod.GET])
    fun list(model:Model, @PathVariable("page") page:String = "0"):String{

        val intPage = try{
            page.toInt()
        } catch (e:Exception){
            logger().debug("페이지 오류")
            0
        }

        val orderList = orService.selectWithPageable(intPage)
        model["SALES"] = orderList

        return "order/list"
    }


    @RequestMapping(value=["/detail/{seq}"], method = [RequestMethod.GET])
    fun detail(model:Model, @PathVariable("seq") seq: Long):String{
        val sales = orService.findById(seq)
        model["SALES"] = sales
        return "order/detail"
    }
}