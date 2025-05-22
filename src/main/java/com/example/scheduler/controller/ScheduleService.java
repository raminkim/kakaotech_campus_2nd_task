package com.example.scheduler.controller;

import com.example.scheduler.ScheduleRequestDto;
import com.example.scheduler.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findScheduleById(Long scheduleId);

    ScheduleResponseDto updateSchedule(Long scheduleId, String writerName, String workToDo, String password);

    void deleteSchedule(Long scheduleId, String password);
}
