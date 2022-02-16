package com.example.hanghae99_mini2.dto;

import com.example.hanghae99_mini2.model.Study;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BoardResponseDto {
    private Long id;
    private Long registeredUserId;
    private String category;
    private String name;
    private String content;
    private Long memberNum;
    private Long currentMemberNum;
    private String recruitState;
    private String createdAt;
    private String modifiedAt;
    private List<Long> userIds;

}
