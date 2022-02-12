package com.example.hanghae99_mini2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpSession;

@Setter
@Getter
public class LoginResponseDto {
    HttpSession session;

    public LoginResponseDto(HttpSession session) {
        this.session = session;
    }
}
