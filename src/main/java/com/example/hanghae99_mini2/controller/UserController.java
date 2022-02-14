package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.ResultResponseDto;
import com.example.hanghae99_mini2.dto.LoginDto;
import com.example.hanghae99_mini2.dto.SignupRequestDto;
import com.example.hanghae99_mini2.model.AuthenticationToken;
import com.example.hanghae99_mini2.model.User;
import com.example.hanghae99_mini2.security.UserDetailsImpl;
import com.example.hanghae99_mini2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 요청
    @PostMapping("/user/login")
    public AuthenticationToken login(
            @RequestBody LoginDto loginDto,
            HttpSession session
    ) {
        AuthenticationToken authenticationToken = userService.getAuthenticatoinToken(loginDto, session);
        return authenticationToken;
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


}