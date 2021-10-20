package com.callor.spring.service.impl

import com.callor.spring.models.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service("bServiceV1")
class BuyerServiceImplV1:BuyerService {

    // 현재 BuyerServiceImplV1 에서 사용할 가상 데이터를 선언
    // 현재 클래스 내부에서만 사용하는 static 변수선언
    private companion object{
        val RND = Random(System.currentTimeMillis())
        val BUYER_LIST = arrayOf(
            Buyer(userid="B001",
                name="홍길동",
                address="서울시",
                tel="02-111-1234",
                manager="이몽룡",
                man_tel="010-111-2222",
                buy_total=10000
            ),
            Buyer(userid="B002",
                name="성춘향",
                address="남원시",
                tel="063-123-2345",
                manager="월매",
                man_tel="010-123-2222",
                buy_total=14500)
            )
    }

    override fun selectAll(): Array<Buyer> {

        return BUYER_LIST
    }

    override fun findById(userid: String): Buyer {

        val findUser = BUYER_LIST.filter { buyer -> buyer.userid == userid}
        return findUser[0]
    }

    override fun findByName(name: String): Array<Buyer> {

        val userNum = RND.nextInt(BUYER_LIST.size)
        return arrayOf(BUYER_LIST[userNum])
    }

    override fun insert(buyer: Buyer): Int {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String): Int {
        TODO("Not yet implemented")
    }

    override fun update(buyter: Buyer): Int {
        TODO("Not yet implemented")
    }
}