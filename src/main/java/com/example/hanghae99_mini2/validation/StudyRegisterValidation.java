//package com.example.hanghae99_mini2.validation;
//
//import com.example.hanghae99_mini2.dto.StudyDto;
//import com.example.hanghae99_mini2.security.UserDetailsImpl;
//
//public class StudyRegisterValidation {
//    // 스터디 생성 유효성검사
//    public static StudyDto validationStudyRegister(StudyDto requestDto, UserDetailsImpl userDetails) {
//        if(userDetails.getUser().getId() != null){
//            requestDto.setRegisteredUserId(userDetails.getUser().getId());
//        }else{
//            throw new IllegalArgumentException("올바르지 않은 Login UserId 입니다.");
//        }
//        String category = requestDto.getCategory();
//        String name = requestDto.getName();
//        String content = requestDto.getContent();
//        Long memberNum = requestDto.getMemberNum();
//        // currentMemberNum은 스터디 생성시 1로 고정(스터디 개설자만 포함)
//        requestDto.setCurrentMemberNum(1L);
//        // recruitState는 스터디 생성시 모집중으로 고정
//        // memberNum 최소가 2 이상이므로 문제없을것으로 예상
//        requestDto.setRecruitState("모집중");
//
//        // category, name, content 확인
//        // 공백, null 입력 제한
//        if(category.trim().isEmpty()){
//            throw new IllegalArgumentException("카테고리를 선택해 주세요.");
//        }else if(name.trim().isEmpty()){
//            throw new IllegalArgumentException("이름을 입력해 주세요.");
//        }else if(content.trim().isEmpty()){
//            throw new IllegalArgumentException("내용을 입력해 주세요.");
//        }
//
//        // memberNum 확인
//        if(memberNum <= 1 || memberNum > 15){
//            // 최소 2명이상부터 입력되도록 설정
//            throw new IllegalArgumentException("스터디 멤버는 2 ~ 15명까지 설정가능합니다.");
//        }
//
//        return requestDto;
//    }
//}
