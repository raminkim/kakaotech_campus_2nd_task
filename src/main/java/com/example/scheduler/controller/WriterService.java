package com.example.scheduler.controller;

import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto requestDto);
}
