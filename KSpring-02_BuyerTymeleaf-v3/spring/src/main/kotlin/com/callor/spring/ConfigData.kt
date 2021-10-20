package com.callor.spring

import com.callor.spring.models.Buyer
import kotlin.random.Random

class ConfigData {
    // public static final String APP_NAME = "나라상사 고객관리"
    //      System.out.println(ConfigString.APP_NAME)
    // java static 키워드와 유사한 역할을 하는 객체
    companion object{
        val APP_NAME = "나라상사 고객관리"
        val APP_VERSION = "2021 v2"

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
}