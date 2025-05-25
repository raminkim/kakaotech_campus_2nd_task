# 카카오테크 캠퍼스 2번째 과제

## 필수 기능(0\~2)

### 일정관리 앱 API 설계

#### API 명세서 작성

| 기능 | Method | URL | request | response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedule | requestBody | 등록한 일정 정보 | 201: 정상등록 |
| 일정 조회 | GET | /api/schedule/{scheduleId} | requestParam | 단건 응답 정보 | 200: 정상조회 |
| 일정 목록 조회 | GET | /api/schedule/writerId/{writerId} | requestParam | 다건 응답 정보 | 200: 정상조회 |
| 일정 수정 | PATCH | /api/schedule/{scheduleId} | requestBody | 수정한 일정 정보 | 200: 정상수정 |
| 일정 삭제 | DELETE | /api/schedule/{scheduleId} | requestParam | \- | 200: 정상삭제 |

| 기능 | Method | URL | request | response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 작성자 등록 | POST | /api/writer | requestBody | 등록한 작성자 정보 | 201: 정상등록 |

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


#### 마지막 질문에 대한 답변
- **Q1. 적절한 관심사 분리를 적용하셨나요? (Controller, Service, Repository)**<br>
A1. 네, 적용하였습니다.<br>

- **Q2. RESTful한 API를 설계하셨나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?**<br>
A2. 네, /api/schedule, /api/schedule/{scheduleId}와 같이 계층 관계를 적절히 나타낸 것 같습니다.<br>
하지만, @GetMapping("/writerId/{writerId}") 부분을 좀 더 가독성있게 표현하지 못한 점이 아쉽습니다.<br>

- **Q3. 수정, 삭제 API의 request를 어떤 방식으로 사용 하셨나요? (param, query, body)**<br>
A3. Body에 json 형식으로, 수정에는 이름, 할일, 비밀번호, 작성자 id를 받았고, 삭제는 비밀번호를 받았습니다.<br>
그리고 수정/삭제 모두 PathVariable로 schedule id 값을 받아 처리하였습니다.<br>
