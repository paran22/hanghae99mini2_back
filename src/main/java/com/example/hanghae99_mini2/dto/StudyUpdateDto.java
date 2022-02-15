package com.example.hanghae99_mini2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StudyUpdateDto {
    private String category;
    //    private Long registeredUserId;
    private String name;
    private String content;
    private Long memberNum;
//    private Long currentMemberNum;
//    private String recruitState;
}
