package com.callor.readbook.config

import com.callor.readbook.service.MemberLoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

// 프로젝트 설정을 위한 클래스임을 선언
@SpringBootConfiguration
// 프로젝트에 Spring Security 설정을 추가
// Spring Security 를 customizing
@EnableWebSecurity
class SecurityConfig:WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {

        web.ignoring().antMatchers(
            "/static/**",
            "/static/css/**",
            "/static/js/**",
            "/static/images/**",
            "*.ico",
            "*.txt"
        )   // 해당 request 는 처리하지 않는다

        web.ignoring().requestMatchers(
            PathRequest.toStaticResources().atCommonLocations()
        ) // staticResources, CommonLocations 으로 설정되어있는 것들은 처리하지 않는다
    }

    // 인증절차를 수행하는 policy(정책) 설정
    override fun configure(http: HttpSecurity) {

        // Client 로 부터 전달된 Req가 인가된 요청인가 확인
//        http.authorizeRequests()
//            .antMatchers("/member/mypage").authenticated()
//
//        http.authorizeRequests()
//            .antMatchers("/**").permitAll()

        /**
         * antMatchers(), mvcMatcher() 등은
         * authorizeRequests(0 함수와 chaining 관계에 있는 함수들이다
         * authorizeRequests(0 함수 아래에 다수의 antMatchers()를
         * 계속 추가할 수 있다
         */
        http.authorizeRequests()
            .antMatchers("/member/login").permitAll()
            .antMatchers("/member/mypage").authenticated() // authenticated 로그인 요청을 해야함함
            .antMatchers("/member/**").permitAll() //모든요청통과 순서에 유의할것!!
            .antMatchers("/**").permitAll()

        // 단독으로 사요되는 method 함수들은 http.함수() 형식으로 사용한다
//        http.httpBasic()
//        http.formLogin()

        // 단독으로 사용되는 method함수들을
        // chaining 방식으로 사용할때는 and()함수로 연결해 준다
        // .and().httpBasic() // popup 로그인
        // custom login form 을 사용할때는 .and 로 사용하지 말것!!!
        http.formLogin() // 로그인 form 보이기
        // security 기본 form 화면을 보이는대신
        // memberController의 login 으로 reirec
            .loginPage("/member/login").permitAll()
                // custom login form 의 action과 같은 URL을 지정
            .loginProcessingUrl("/login")
                // 새로운 변수로 지정을하면 변수이름을 설정해주어야 한다
            .usernameParameter("userid")
            .passwordParameter("pass")

        // logout 을 구현하겠다
        http.logout()
                // ${rootPath}/logout 으로 요청이 들어오면
                // logout 을 수행해라
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))

                // logout 이 정상적으로 수행되면
                // /member/mypage 로 redirect 를 수행하라
            .logoutSuccessUrl("/")

    } // config(http) end

    /**
     * "{noop}12341234
     * Spring Security 에서 제공하는 password 정책
     * 5.x 버전이상에서는 의무적으로 password 를 DB에 저장하거나
     * 비교연산등을 할때 반드시 암호화를 하도록 강제하고 있다
     *
     * 아직 암호화를 구현하지 않을 상태에서 테스트를 하기위해서
     * 임시로 암호화 되지 않은 비밀번호를 사용해서
     * 비밀번호 비교를 하겠다 라는 의미의 메시지
     */
    override fun configure(auth: AuthenticationManagerBuilder) {

//        auth.inMemoryAuthentication()
//            .withUser("inqu0302")
//            .password("{noop}12341234")
//            .roles("USER","ADMIN")

        // security 에게 UserDetailService 인터페이스를 상속받은
        // MemberLoginService 클래스의 객체를 전달하여 회원정보 인증시 사용
        // MemberLoginService.loadUserByUserName() 함수를 실행하여 사용자 정보를 요청
        auth.userDetailsService(MemberLoginService())
            // auth 에 담긴 사용자 정보에서 password 항목을 찾아
            // CustomPasswordEncoder() 에게 전달하여 비밀번호 유효성 검사
            .passwordEncoder(CustomPasswordEncoder())

    }
}

// 로그인을 하기위한 password 유효성 검사용 임시코드
class CustomPasswordEncoder:PasswordEncoder{
    override fun encode(plan: CharSequence): String {
        return plan.toString()
    }

    override fun matches(plan: CharSequence?, crypt: String?): Boolean {
        return true
    }

}