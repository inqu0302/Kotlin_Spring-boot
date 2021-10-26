package com.callor.spring.repository

import com.callor.spring.models.Buyer
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
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

    @Query("SELECT B FROM Buyer B")
    fun findWithPagination(pageable: Pageable?):List<Buyer>

    // tbl_buyer table에서 가장 마지막 userid를 찾아서 return
    /**
     * JSQL(JPA SQL)
     * JPA 가 적용된 프로젝트에서 기본 규칙으로 제공하는 함수 외에
     * custom 함수를 만들고자 할때 적용하는 SQL
     *
     * FROM 절에 table 이름이 아닌 DTO 클래스를 적용한다
     */
    @Query("SELECT max(userid) FROM Buyer")
    fun maxuUserId():String?
}