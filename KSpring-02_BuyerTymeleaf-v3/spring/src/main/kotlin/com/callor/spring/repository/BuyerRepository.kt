package com.callor.spring.repository

import com.callor.spring.models.Buyer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// 현재 BuyerRepository interface 를 bean 으로 등록
// 선택사항이지만 설정을 해주는것이 좋다
@Repository
interface BuyerRepository:JpaRepository<Buyer,String> {

    /**
    * JpaRepository 상속받은 Repository 에서 기본 CRUD 이외의
    * 다른 칼럼으로 조회하는 method 를 추가할수 있다
    * 단 조건이 있다
    * findBy 로 시작해야한다
    * data(Entity) 클래스에 정의된 맴버 변수 이름으로만 설정이 가능
    */
    fun findByName(name:String):Array<Buyer>
    fun findByTel(tel:String):Array<Buyer>
}