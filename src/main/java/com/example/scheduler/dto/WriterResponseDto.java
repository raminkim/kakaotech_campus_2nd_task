package com.example.scheduler.dto;

import com.example.scheduler.entity.Writer;
import lombok.Getter;

@Getter
public class WriterResponseDto {
    private long writerId;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;

    public WriterResponseDto(Writer writer) {
        this.writerId = writer.getWriterId();
        this.name = writer.getName();
        this.email = writer.getEmail();
        this.createdAt = writer.getCreatedAt();
        this.updatedAt = writer.getUpdatedAt();
    }
}
