package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Writer {
    private Long writerId;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;

    public Writer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
