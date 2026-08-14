package com.example.userservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> signup(
        @RequestBody SignUpRequestDto signUpRequestDto
    ) {
        userService.signUp(signUpRequestDto);
        return ResponseEntity.ok("회원가입 성공");
    }
}