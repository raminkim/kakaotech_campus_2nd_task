package com.example.scheduler.controller;

import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/writer")
public class WriterController {

    private final WriterService writerScheduleService;

    public WriterController(WriterService writerScheduleService) {
        this.writerScheduleService = writerScheduleService;
    }

    @PostMapping
    public ResponseEntity<WriterResponseDto> createWriter(@RequestBody WriterRequestDto requestDto) {
        return new ResponseEntity<>(writerScheduleService.saveWriter(requestDto), HttpStatus.CREATED);
    }
}
