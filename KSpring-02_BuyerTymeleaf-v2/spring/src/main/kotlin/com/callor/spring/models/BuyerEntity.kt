package com.callor.spring.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

// DB 에서 table 의 논리적인 개념 : Entity
@Entity
@Table(name="tbl_buyer", schema = "naraDB")
class BuyerEntity {

    @Id // primarry key 지정정
   @Column(columnDefinition = "CHAR(4)", nullable = false)
    private val userid : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val name : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val tel : String? = null

    @Column( nullable = false)// columnDefinition을 작성안하면 VARCHAR(125) 자동지정됨
    private val address : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val manager : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val man_tel : String? = null


    private val buy_total : Int = 0
}