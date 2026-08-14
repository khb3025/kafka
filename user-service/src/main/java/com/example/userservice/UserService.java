package com.example.userservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public UserService(UserRepository userRepository, KafkaTemplate kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void signUp(SignUpRequestDto signUpRequestDto) {
        
        User user = new User(
            signUpRequestDto.getEmail(), 
            signUpRequestDto.getName(), 
            signUpRequestDto.getPassword()
        );
        User savedUser = userRepository.save(user);
        UserSignedUpEvent userSignedUpEvent = new UserSignedUpEvent(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getName()
        );
        kafkaTemplate.send("user.signed-up", toJsonString(userSignedUpEvent));
        
    }

    private String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }
}
