# 카카오테크 캠퍼스 2번째 과제

## 필수 기능(0\~2)

### 일정관리 앱 API 설계

#### API 명세서 작성

| 기능 | Method | URL | request | response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedule | requestBody | 등록한 일정 정보 | 201: 정상등록 |
| 일정 조회 | GET | /api/schedule/{scheduleId} | requestParam | 단건 응답 정보 | 200: 정상조회 |
| 일정 목록 조회 | GET | /api/schedule | requestParam | 다건 응답 정보 | 200: 정상조회 |
| 일정 수정 | PATCH | /api/schedule/{scheduleId} | requestBody | 수정한 일정 정보 | 200: 정상수정 |
| 일정 삭제 | DELETE | /api/schedule/{scheduleId} | requestParam | \- | 200: 정상삭제 |

### ERD

<img width="400" height="500" alt="image" src="https://github.com/user-attachments/assets/44b24b47-cf65-4b54-a362-4156ee48ef8d"/><br>

#### 일정 테이블

1. **scheduleId - bigint**: 일정의 고유 id 값(PK)
    
2. **writerName - varchar(255)**: 일정 작성자의 이름 (=작성자명)
    
3. **workToDo - text**: 할 일을 적은 텍스트
    
4. **password - varchar(255)**: 일정을 수정하고자 할 때 사용되는 비밀번호
    
5. **createdAt - datetime**: 일정 작성일. 날짜와 시간을 모두 포함한다.
    
6. **updatedAt - datetime**: 일정 수정일. 날짜와 시간을 모두 포함한다. 최초 입력 시, 작성일과 동일하다.

7. **writerId - bigint**: 작성자 테이블의 고유 id 값(FK)


#### 작성자 테이블

1. **writerId - bigint**: 작성자의 고유 id 값(PK)

2. **name - varchar(255)**: 작성자의 이름

3. **email - varchar(255)**: 작성자의 이메일

4. **createdAt - datetime**: 작성자 생성일.

5. **updatedAt - datetime**: 작성자 수정일. 최초 입력 시, 작성일과 동일하다.
