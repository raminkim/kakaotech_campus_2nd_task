# kakaotech_campus_2nd_task
카카오테크 캠퍼스 2번째 과제

## 일정관리 앱 API 설계
|기능|Method|URL|request|response|상태코드|
|------|---|---|------|------|------|
|일정 등록|POST|/api/schedule|requestBody|등록한 일정 정보|200: 정상등록|
|일정 조회|GET|/api/schedule/{scheduleId}|requestParam|단건 응답 정보|200: 정상조회|
|일정 목록 조회|GET|/api/schedule|requestParam|다건 응답 정보|200: 정상조회|
|일정 수정|PATCH|/api/schedule/{scheduleId}|requestBody|수정한 일정 정보|200: 정상수정|
|일정 삭제|DELETE|/api/schedule/{scheduleId}|requestParam|-|200: 정상삭제|
