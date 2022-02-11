package com.example.hanghae99_mini2.dto;

import com.example.hanghae99_mini2.model.StudyInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;
    private List<StudyInfo> studyInfoList;
}
