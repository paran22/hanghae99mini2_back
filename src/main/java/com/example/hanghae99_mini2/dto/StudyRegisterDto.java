package com.example.hanghae99_mini2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StudyRegisterDto {
    private String category;
    private String name;
    private String content;
    private Long memberNum;
}
