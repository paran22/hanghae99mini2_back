package com.example.hanghae99_mini2.validation;

import com.example.hanghae99_mini2.dto.StudyRegisterDto;

public class StudyRegisterValidation {
    // 스터디 생성 유효성검사
    public static void validationStudyRegister(StudyRegisterDto requestDto) {
        //@AuthenticationPrincipal UserDetailsImpl userDetails 매개변수에서 우선 배제
//        if(userDetails.getUser().getId() != null){
//            requestDto.setRegisteredUserId(userDetails.getUser().getId());
//        }else{
//            throw new IllegalArgumentException("올바르지 않은 Login UserId 입니다.");
//        }
        String category = requestDto.getCategory();
        String name = requestDto.getName();
        String content = requestDto.getContent();
        Long memberNum = requestDto.getMemberNum();
        // currentMemberNum은 스터디 생성시 1로 고정(스터디 개설자만 포함), 생성자 내용에 포함
        // recruitState는 스터디 생성시 모집중으로 고정, 생성자 내용에 포함
        // memberNum 최소가 2 이상이므로 문제없을것으로 예상

        // category, name, content 확인
        // 공백, null 입력 제한
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리를 선택해 주세요.");
        } else if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해 주세요.");
        } else if (content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해 주세요.");
        }

        // memberNum 확인
        if (memberNum <= 1 || memberNum > 15) {
            // 최소 2명이상부터 입력되도록 설정
            throw new IllegalArgumentException("스터디 멤버는 2 ~ 15명까지 설정가능합니다.");
        }
    }
}
