package com.example.userservice;

import org.springframework.stereotype.Component;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class UserSignedUpEvent {
    private Long userId;
    private String email;
    private String name;

    public UserSignedUpEvent(Long userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
    public UserSignedUpEvent() {}

    public Long getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
}
