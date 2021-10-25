package com.callor.spring.config

import com.callor.spring.ConfigData
import com.callor.spring.models.Sales
import com.callor.spring.repository.SalesRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import java.text.SimpleDateFormat
import java.util.*

/**
 * Spring Boot 가 시작될때 사용할 초기값, 설정 등을 수행하는 클래스
 *
 * SalesRepository 를 생성자 주입방식으로 wiering 한다
 * 생성자를 클래스의 매개변수로 설정
 */
@SpringBootConfiguration
class SalesDataInit(val salesDao:SalesRepository) {

    val logger = LoggerFactory.getLogger(SalesDataInit::class.java)

    private val pnames = listOf(
        "아이폰13", "갤럭시폴드",
        "에어팟프로", "갤럭시버즈",
        "애플워치", "갤럭시워치"
    )

    @Bean
    fun dataInit():CommandLineRunner{

        for(num in 1..100){
            salesDataInit()
        }

        return CommandLineRunner{
            logger.debug("Sales 데이터 complete")
        }
    }

    private fun salesDataInit(){

        val userid = String.format("B%03d", ConfigData.RND.nextInt(20) + 1)
        val pname = pnames[ConfigData.RND.nextInt(pnames.size)]

        val qty = ConfigData.RND.nextInt(10) + 10 * 10
        val price = ConfigData.RND.nextInt(100) + 100 * 1000

        val df = SimpleDateFormat("yyyy-MM-dd")
        val dt = SimpleDateFormat("hh:mm:ss")

        val toDate = df.format(Date())
        val toTime = dt.format(Date())

        val sales = Sales(
            userid = userid,
            pname = pname,
            qty = qty,
            amt = price,
            total = qty * price,

            date = toDate,
            time = toTime
        )

        salesDao.save(sales)
    }
}