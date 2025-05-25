package com.example.scheduler.dto;

import com.example.scheduler.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long scheduleId;
    private String writerName;
    private String workToDo;
    private String createdAt;
    private String updatedAt;
    private Long writerId;

    public ScheduleResponseDto(Long scheduleId, String writerName, String workToDo, String createdAt, String updatedAt, Long writerId) {
        this.scheduleId = scheduleId;
        this.writerName = writerName;
        this.workToDo = workToDo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.writerId = writerId;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.writerName = schedule.getWriterName();
        this.workToDo = schedule.getWorkToDo();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.writerId = schedule.getWriterId();
    }
}
