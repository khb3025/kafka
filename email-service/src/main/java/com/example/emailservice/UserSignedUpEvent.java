package com.example.emailservice;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class UserSignedUpEvent {
    private Long userId;
    private String email;
    private String name;

    public UserSignedUpEvent() {
    }
    
    public UserSignedUpEvent(
        Long id, 
        String email, 
        String name
    ) {
        this.userId = id;
        this.email = email;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static UserSignedUpEvent fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, UserSignedUpEvent.class);
        }catch(JacksonException e) {
            e.printStackTrace();
            throw new RuntimeException("Json 파싱 실패");
        }
    }
}
