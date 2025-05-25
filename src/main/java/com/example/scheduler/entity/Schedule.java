package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long scheduleId; // 고유 id
    private String writerName; // 작성자명
    private String workToDo; // 할 일
    private String password; // 비밀번호
    private String createdAt; // 작성일
    private String updatedAt; // 수정일
    private Long writerId; // 작성자 id (FK)

    public Schedule(String writerName, String workToDo, String password, Long writerId) {
        this.writerName = writerName;
        this.workToDo = workToDo;
        this.password = password;
        this.writerId = writerId;
    }

    public void updateSchedule(String writerName, String workToDo, String password) {
        if (this.password.equals(password)) {
            this.writerName = writerName;
            this.workToDo = workToDo;
        }
    }
}
