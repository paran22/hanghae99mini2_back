package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.DulplicateResponseDto;
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
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // 로그인
    @PostMapping("/user/login")
    public AuthenticationToken login(
            @RequestBody LoginDto loginDto,
            HttpSession session
    ) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        User user = userService.readUser(username);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new AuthenticationToken(userDetails.getUsername(), userDetails.getAuthorities(), session.getId());
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