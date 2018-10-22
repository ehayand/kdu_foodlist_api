# 극동대학교 학식
카카오톡 플러스친구를 이용하여 극동대학교 학식 정보를 제공하는 REST API 서버

# 사용 기술
- AWS EC2
- Ubuntu 18.04
- NginX
- Selenium ChromeDriver
- Spring Boot
  - Embedded Tomcat
  - Embedded H2DB
  - Spring Data JPA
    
# 개발 방향
- 서비스 안정화를 위해 리팩토링 예정
  - 유저 정보를 저장하지 않는다. // 외부 데이터베이스를 활용하여 저장 예정
  - H2 데이터베이스를 이용 //
- 스케줄링을 이용하여 일정한 시간 간격으로 데이터베이스 초기화를 실행한다.
- 학교 데이터를 공급받을 수 없기 때문에 웹 파싱을 통해 데이터를 공급받는다.
  - Selenium ChromeDriver 이용
- '오늘', '내일' 외 추가적인 인터페이스 제공 예정
  - Depth 추가 (오늘, 내일) -> (조식, 중식, 석식)
    - Keyboard 수정 완료
    - MenuDataRepository 수정 중
    - DataAccessService 수정 중
    - ApiService 수정 중