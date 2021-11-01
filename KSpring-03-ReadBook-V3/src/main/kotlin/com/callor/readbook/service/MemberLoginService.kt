package com.callor.readbook.service

import com.callor.readbook.MemberVO
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Security login Service 클래스
 * ( *UserDetailsService )
 *
 * Security login Service UserDetailsService 를 상속받는다
 */
@Service
class MemberLoginService : UserDetailsService{

    // 가상의 member list 생성해 두기
    private val userList = listOf(
        MemberVO(username = "inqu0302", password = "12341234"),
        MemberVO(username = "inqu00", password = "12341234"),
        MemberVO(username = "inqu0033", password = "12341234"),
    )


    // findByUserName(username) : UserDetails
    override fun loadUserByUsername(username: String): UserDetails {

        // 배열.find {} : 배열의 요소중 원하는 값이 담겨있는가?
        // 담겨있으면 해당 값을 return 하고 없으면 null 을 return 한다
        val member: MemberVO? = userList.find{ it.username == username}
        member ?:throw UsernameNotFoundException("사용자 ID 가 잘못되었습니다")

        return member
    }


}