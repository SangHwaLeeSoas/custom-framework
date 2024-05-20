

# 작업 진행도
## 완료
- [x] 회원가입 API
- [x] 로그인 API
- [x] 견적서 API
- [x] 송금 API
- [x] 송금 내역 조회 API
### 미완료
- Unit Test
> 작업 시간이 부족해 Unit Test 작성을 완료하지 못했습니다. 예상 하고 있던 구현 방식은 Junit5와 mockito를 사용하여 작성할 예정이었습니다.

# 개발환경
- JDK 17 / Kotlin 1.7.10
- Spring Boot 3.2.5
- DB : H2
- IDE : IntelliJ
- Spring Profile : local로 설정하시고 실행하시면 됩니다.


# 공통
## Exception
- `CommonException` : 비지니스 로직에서 사용하기 위한 Custom RuntimeException
- `ExceptionHandlerController` : Exception 처리를 위한 ControllerAdvice

## Response
- `ResCode` : API 응답 코드 Enum class. 기본적으로 모든 응답은 ResCode 객체를 거치는 걸 목표로 해당 AP의 모든 응답 코드를 관리.
- `Response / ResponseData` : API 응답 객체. 응답 코드와 메시지를 포함.

## Annotation
- `CheckEnum` : javax validator를 사용하며, 동시에 enum 유효성 검증을 효율적으로 하기 위한 custom annotation

## 암호화
- 패스워드는 기본 BCryptPasswordEncoder를 사용하여 암호화 하였습니다.
- 기타 개인정보는 AES256 암호화를 사용하여 암호화 하였습니다.

## Security
- 로그인 구현을 위한 기본적인 세팅. (로그아웃 X, Refresh Token X)
- JWT 토큰을 사용하기 위한 Filter 추가


# 회원
## Entity
- `User` : 회원정보 관리를 위한 테이블
- `LoginHistory` : 로그인 이력 저장을 위해 기본적으로 추가한 테이블


# 송금
## Entity
- `Quote` : 견적서 테이블
> 송금 신청 이후의 프로세스가 없어 Quote 테이블 데이터로 관리하도록 했습니다.

## 특이사항
송금 화폐는 원화인데, 송금 제한은 USD로 설정 되어 있습니다.
*  환율을 적용하여 USD로 변환하여 계산해야 하는데, 환율에 대한 기준이 하루동안 일정하지 않으므로 추가적인 정책이 필요하여 해당 Method 주석 처리해두었습니다.

# 참고 사항
- 테이블 구조 / 주석은 최소화 하여 진행했습니다.
- API Response 데이터셋이 규격화 되지 않아 오타로 이해하고 API들은 공통된 Response model을 사용하도록 했습니다.
- 데이터 형식 등이 확실하지 않은 것은 개발 중 임의의 타입으로 사용 했습니다. (DateTime format / BigDecimal 등 추후 유지보수를 예상하여 사용)
- 개발 시 추후 개선이나, 방향성에 따라 변경되어야 하는 부분은 TODO로 코멘트 달아두었습니다.
- API 테스트는 PostMan으로 진행했습니다.

