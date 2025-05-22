package com.example.scheduler.controller;

import com.example.scheduler.ScheduleRequestDto;
import com.example.scheduler.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.saveSchedule(requestDto);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto findScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.findScheduleById(scheduleId);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
        @PathVariable Long scheduleId,
        @RequestBody ScheduleRequestDto requestDto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId, requestDto.getWriterName(), requestDto.getWorkToDo(), requestDto.getPassword()),
        HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        scheduleService.deleteSchedule(scheduleId, requestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
