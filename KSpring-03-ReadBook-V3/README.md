# ReadBook Project V3 with Spring Security

* 2021-11-01
* 독서록 프로젝트에 Spring Security 를 적용하여 로그인 기능구현
* Spring Security를 적용하면 로그인, 로그인 후 session 관리 등을 편리하게 구현가능
  Spring Security 는 초기 설정이 어렵고 불편하지만, 초기설정만 잘 되면 상당부분을 개발자가 직>접 핸들링 하지 않고도 인가, 인증, 권한설정 등을 쉽게 할수 있다

## Security 관련된 용어 정리
* 인증(Authentication) : username, password 를 사용하여 허가된 사용자 인지 판별
* 인가(Authorization) : 로그인 된 사용자에게 권한을 허락(부여) 하는 것
* 권한설정(Authority, Role) : 인증받은 사용자의 인가 정보를 확인하여 접근할 수 있는 곳(페이>지) 등을 확인하고, 적적히 처리하는 것

* 예) 사용자 로그인시
1. 정상적인 절차로 회원가입이 되었는지, 본인확인 절차를 통과했는지?
   자신의 mypage에 접근할수 있는 권한을 부여

2. 사전에 부여된 역할이 무엇인가를 판단
   User(일반) : mypage 에서 정보를 보기,수정,삭제 가 가능하다
   Admin(관리자) : 자신또는 다른user의 리스트를보고 내용수정들이 가능하다


# Spring Security를 사용한 Login 구현
* SecurityConfig(WebSecurityConfigurerAdapter 를 상속)에 설정을 하여 login form 을 custom 할 수 있다.
* 이때 login form 의 method 는 반드시 post 로 action=${rootPath}/login 으로 설정
즉 security에서 제공하는 LoginProcessor URL로 login 정보를 request 하기
Spring security 에서 기본으로 제공하는 login 기능을 사용하겠다는 의미
* 기본 login 기능은 username 과 password 값을 받아서 인증 절차를 수행
* 만약 인증절차가 실패(username 이 없거나, password 가 틀리면)하면 무조건 원래의 login(/member/login) 
으로 redirect 한다 
* 이때 error 라는 매개변수(params)를 전달한다
* thymeleaf 로 만든 login form 에서는 th:if="${param.login}" 코드를 사용하여 오류가 발생했음을 view 에 보여줄수 있다

# Spring Security login LOGIC 흐름
1. authenticated 가 설정된 page 에 접근(Request)
2. login 정보가 있는지 확인
3. 없으면 loginPage() 설정된 곳으로 redirect : login.html
4. 로그인 수행 : ${rootPath}/login 에 POST 전송
5. configure(auth: AuthenticationManagerBuilder) 에 설정된 동작 수행
6. auth.userDetailsService(MemberLoginService()) 설정을 확인
   1. MemberLoginService() 클래스가 지정
   2. 이 클래스는 UserDetailsService interface 를 상속받아 작성된 클래스
   3. 이 클래스의 loadUserByUserName() method 를 실행
   4. loadUserByUsername() method 는 username 을 기준으로 사용자 정보를 조회하여
        MemberVO 객체 데이터를 만든다
   5. 그리고 configure 의 auth 에게 MemberVO 를 전달한다
7. passwordEncoder(CustomPasswordEncoder()
   1. auth 에 담긴 사용자 정보에서 password 항목을 찾아
      CustomPasswordEncoder() 에게 전달하여 비밀번호 유효성 검사