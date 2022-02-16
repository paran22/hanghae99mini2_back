package com.example.hanghae99_mini2.dto;

import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.model.StudyInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {
    Long userId;
    String username;
    List<Study> registerStudyList;
}
