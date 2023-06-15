## 사용기술
- SpringBoot 2.7.12
- SpringDataJdbc
- h2-database
- gradle

## 패키지 구조
- config - 빈 등록을 위한 구성정보 패키지
- controller - 프롬프트에서 입력받은 값을 토대로 하는 작업 관련 로직 패키지
- domain - 핵심 비즈니스 로직이 담긴 패키지
- exception - 예외처리
- service - 값을 읽어들이고 비즈니스로직을 처리하는 패키지
- ui - 화면 관련 패키지

## 구현 방향
- 과제 요구사항을 중점적으로 구현한다.
- domain layer와 service layer의 testCoverage를 100%로 유지한다.
- 테스트하기 어려운 ui관련 테스트는 진행하지 않고 도메인에 집중하도록 한다.
- 코드의 추상화 수준을 맞춘다.
- 객체지향적인 코드작성을 위해 스프링을 활용한다.
- csv파일은 h2데이터베이스에 import하여 사용한다.
- 서비스 구현은 구현체가 변경될 가능성이 높기 때문에 반드시 인터페이스를 활용한다.
- 데이터접근 계층도 마찬가지이다. 인터페이스를 활용한다.