package com.callor.spring.service.impl

import com.callor.spring.ConfigData
import com.callor.spring.config.logger
import com.callor.spring.models.Buyer
import com.callor.spring.repository.BuyerRepository
import com.callor.spring.service.BuyerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

/**
 * 클래스의 매개변수를 사용하여 생성자 주입하기
 *
 * class 클래스(주입받을객체, 변수 선언)
 */
@Service("bServiceV1")
class BuyerServiceImplV1(val bRepo:BuyerRepository):BuyerService {

    // setter 주입방식 와이어링 하기
    // 메모리 누수문제등이 발생할수 있음
//    @Autowired
//    lateinit var bDao : BuyerRepository

    override fun selectAll(): Array<Buyer> {

        return bRepo.findAll().toTypedArray()
    }

    override fun selectAll(pageable: Pageable): Page<Buyer> {

        return bRepo.findAll(pageable)
    }

    override fun selectWithPageable(intPage: Int): Array<Buyer> {
        // PageRequest.of(몇페이지, 몇개)
        val pageRequest = PageRequest.of(intPage, 10)

        return bRepo.findWithPagination(pageRequest).toTypedArray()
    }

    override fun findById(userid: String): Buyer {

        // repository 의 findById()는
        // 실제데이터(Buyer)를 Optional 이라는 특별한 객체로
        // wrapping 하여 가져온다
        // 필요한 데이터는 .get() method 를 사용하여
        // 한번 더 추출해 주어야 한다
        val buyer:Optional<Buyer> = bRepo.findById(userid)

        return buyer.get()
    }

    override fun findByName(name: String): Array<Buyer> {
        return  bRepo.findByName(name)

    }

    override fun findByTel(tel: String): Array<Buyer> {
        return bRepo.findByTel(tel)
    }

    // 새로운 고객ID(userid)를 생성하여 Buyer 에 담아서 return
    override fun insert(): Buyer {
        var userid = bRepo.maxuUserId()


        val userSeq = try{
            // B012 이면 1번째 인덱스 위치부터 자른다
            // 012 만 뽑아내서 Int 형으로 형변환
            userid?.substring(1)?.toInt()

            // exception 이 발생하면 1을 저장
        } catch (e:Exception){
            logger().debug("고객데이터 없음")
            1
        }

        if (userSeq != null) {
            userid = String.format("B%03d",userSeq + 1)
        }

        return Buyer(userid = userid)
    }

    override fun insert(buyer: Buyer): Buyer {
        // Insert Or Update
        return bRepo.save(buyer)
    }

    override fun delete(userid: String) {
        bRepo.deleteById(userid)
    }

    override fun update(buyer: Buyer): Buyer {
        return bRepo.save(buyer)
    }

}