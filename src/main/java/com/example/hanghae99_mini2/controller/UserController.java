package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.DulplicateResponseDto;
import com.example.hanghae99_mini2.dto.LoginDto;
import com.example.hanghae99_mini2.dto.SignupRequestDto;
import com.example.hanghae99_mini2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    //아이디 중복체크
    @GetMapping("/user/signup/checkUsername/{username}")
    public DulplicateResponseDto duplicateUsername(@PathVariable String username) {
        return userService.duplicateUsername(username);
    }

    //아이디 중복체크
    @GetMapping("/user/signup/checkEmail/{email}")
    public DulplicateResponseDto duplicateEmail(@PathVariable String email) {
        return userService.duplicateEmail(email);
    }


}