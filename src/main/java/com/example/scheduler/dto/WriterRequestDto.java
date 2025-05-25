package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class WriterRequestDto {
    private long writerId;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;
}
