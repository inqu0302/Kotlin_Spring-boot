package com.callor.KSpring03ReadBook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@SpringBootConfiguration
// spring Security 를 custom 하겠다
@EnableWebSecurity
class SecurityConfig:WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        // HttpSecurity 로 넘어오는 정보를 처리
        http.authorizeRequests()
            .mvcMatchers("/**") // 모든 request 에 대해서
            .anonymous() // 그냥통과
            .mvcMatchers("/admin/**") // admin 으로 요청됬을때
            .fullyAuthenticated() // 인증절차 실행
    }
}