package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAll(Long writerId);

    Optional<Schedule> findScheduleById(Long scheduleId);

    Schedule findScheduleByIdOrElseThrow(Long scheduleId);

    int updateSchedule(Long scheduleId, String writerName, String workToDo, String password);

    int deleteSchedule(Long scheduleId, String password);
}