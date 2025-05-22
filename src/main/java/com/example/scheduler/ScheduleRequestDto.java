package com.example.scheduler;

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
}
