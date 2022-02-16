package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.ResultResponseDto;
import com.example.hanghae99_mini2.dto.SignupRequestDto;
import com.example.hanghae99_mini2.dto.UserInfoDto;
import com.example.hanghae99_mini2.security.UserDetailsImpl;
import com.example.hanghae99_mini2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResultResponseDto registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return new ResultResponseDto(true);
    }

    //아이디 중복체크
    @GetMapping("/user/signup/checkUsername/{username}")
    public ResultResponseDto duplicateUsername(@PathVariable String username) {
        return userService.duplicateUsername(username);
    }

    //아이디 중복체크
    @GetMapping("/user/signup/checkEmail/{email}")
    public ResultResponseDto duplicateEmail(@PathVariable String email) {
        return userService.duplicateEmail(email);
    }

    //회원 정보 조회
    @GetMapping("/userinfo")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserInfoDto userInfoDto = userService.getUserInfo(userDetails);
        return userInfoDto;
    }

}