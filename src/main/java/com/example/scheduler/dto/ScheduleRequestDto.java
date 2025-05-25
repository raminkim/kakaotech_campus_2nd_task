package com.example.scheduler.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {
    private Long scheduleId;
    private String writerName;
    private String workToDo;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private Long writerId; // 레벨 3에 의한 writerId 추가
}
