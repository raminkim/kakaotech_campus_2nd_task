package com.example.scheduler.repository;

import com.example.scheduler.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAll();

    Optional<Schedule> findScheduleById(Long scheduleId);

    int updateSchedule(Long scheduleId, String writerName, String workToDo, String password);

    int deleteSchedule(Long scheduleId, String password);
}