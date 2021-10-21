package com.callor.spring.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * DTO(VO) 클래스
 */
@Entity
@Table(name = "tbl_buyer", schema = "naraDB")
data class Buyer(

    @Id // primarry key 지정, unique 설정, name을 다른변수명으로도 설정가능
    @Column(columnDefinition = "CHAR(4)",
            nullable = false, // not null
            unique = true,     // UNIQUE
            name="userid")
    var userid : String? = null,

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    var name : String? = null,

    @Column(columnDefinition = "VARCHAR(25)", nullable = true)
    var tel : String? = null,

    @Column( nullable = true)// columnDefinition을 작성안하면 VARCHAR(125) 자동지정됨
    var address : String? = null,

    @Column(columnDefinition = "VARCHAR(25)", nullable = true)
    var manager : String? = null,

    @Column(columnDefinition = "VARCHAR(25)", nullable = true)
    var man_tel : String? = null,

    @Column(nullable = true)
    var buy_total : Int? = null
)
