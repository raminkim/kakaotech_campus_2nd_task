package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    // 도전 기능 3에 의한 작성자의 고유 식별자를 통해 일정이 검색이 될 수 있도록 추가한 전체 일정 조회 코드
    @GetMapping("/writerId/{writerId}")
    public ResponseEntity<List<ScheduleResponseDto>> findAll(@PathVariable Long writerId) {
        return new ResponseEntity<>(scheduleService.findAll(writerId), HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(scheduleService.findScheduleById(scheduleId), HttpStatus.OK);
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
