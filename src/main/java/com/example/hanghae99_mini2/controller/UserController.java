package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.SignupRequestDto;
import com.example.hanghae99_mini2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지 호출
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지 호출
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //requestdto추가해야함
    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    //아이디 중복체크
    @GetMapping("/user/signup/{username}")
    public ResponseEntity<Boolean> duplicateUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.duplicateUsername(username));
    }

    //아이디 중복체크
    @GetMapping("/user/signup/{email}")
    public ResponseEntity<Boolean> duplicateEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.duplicateEmail(email));
    }


}