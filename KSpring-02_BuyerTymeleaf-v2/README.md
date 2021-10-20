# Kotlin Spring Boot Project

## JPA(Java Persistence API, Archtechture)
* 일반적인 DBMS CRUD 는 SQL 을 기반으로 한다
* 가급적 SQL을 적게 사용하기 위하여 MyBatis 등의 도구를 사용하지만
    완전하게 SQL을 사용하지 않을 수 없다
* JPA는 CRUD는 SQL의 도움없이 구현이 가능한 기술이다
* ORM(Object-relational Mapping)의 개념을 Spring에 도입한 것
* DB를 마치 메모리에 변수, 객체를 저장하는 것과 같은 방법으로 
    기본 class를 사용하여 구현하고자 하는 기술
* JPA는 기술적인 개념이다. Spring에서는 JPA를 구현하기 위하여 
    interface 차원에서만 지원한다
* 보통 JPA와 함께 Hibernate라는 구현체를 함께 사용한다
* Spring Boot 에서는 Hibernate는 JPA에 포함되어 구현이 된다

### jQuery import 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>