# kakaotech_campus_2nd_task
카카오테크 캠퍼스 2번째 과제

# 필수 기능(0\~2)

## 일정관리 앱 API 설계

#### API 명세서 작성

| 기능 | Method | URL | request | response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedule | requestBody | 등록한 일정 정보 | 200: 정상등록 |
| 일정 조회 | GET | /api/schedule/{scheduleId} | requestParam | 단건 응답 정보 | 200: 정상조회 |
| 일정 목록 조회 | GET | /api/schedule | requestParam | 다건 응답 정보 | 200: 정상조회 |
| 일정 수정 | PATCH | /api/schedule/{scheduleId} | requestBody | 수정한 일정 정보 | 200: 정상수정 |
| 일정 삭제 | DELETE | /api/schedule/{scheduleId} | requestParam | \- | 200: 정상삭제 |

## ERD

<img src="https://content.pstmn.io/9ee49852-f7db-4570-8df3-6016450c6215/aW1hZ2UucG5n" alt="" height="289" width="450">

#### 일정 테이블

1. **scheduleId - int:** 일정의 고유 id 값
    
2. **writerName - varchar(255)**: 일정 작성자의 이름 (=작성자명)
    
3. **workToDo - text**: 할 일을 적은 텍스트
    
4. **password - varchar(255)**: 일정을 수정하고자 할 때 사용되는 비밀번호
    
5. **createdAt - datetime**: 일정 작성일. 날짜와 시간을 모두 포함한다.
    
6. **updatedAt - datetime**: 일정 수정일. 날짜와 시간을 모두 포함한다. 최초 입력 시, 작성일과 동일하다.
