package com.example.scheduler;

import com.example.scheduler.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Long scheduleId;
    private String writerName;
    private String workToDo;
    private String createdAt;
    private String updatedAt;

    public ScheduleResponseDto(Long scheduleId, String writerName, String workToDo, String createdAt, String updatedAt) {
        this.scheduleId = scheduleId;
        this.writerName = writerName;
        this.workToDo = workToDo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.writerName = schedule.getWriterName();
        this.workToDo = schedule.getWorkToDo();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
