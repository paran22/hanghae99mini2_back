package com.example.hanghae99_mini2.validation;

import com.example.hanghae99_mini2.dto.StudyUpdateDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.repository.StudyRepository;

import java.util.Objects;

public class StudyUpdateValidation {
    public static Study validationStudyUpdate(Long id, StudyUpdateDto requestDto,
                                              StudyRepository studyRepository) {
        // 매개변수 id 유효성검사 및 study 객체 조회
        Study study = studyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("validationStudyUpdate 내부 StudyIdfind 오류 입니다.")
        );
        String category = requestDto.getCategory();
        String name = requestDto.getName();
        String content = requestDto.getContent();
        Long memberNum = requestDto.getMemberNum();
        String recruitState = requestDto.getRecruitState();
        // findById한 study의 currntMemberNum을 변수로 선언
        Long currentMemberNum = study.getCurrentMemberNum();

        // category, name, content 확인
        // 공백, null 입력 제한
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리를 공백으로 수정할 수 없습니다.");
        } else if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 공백으로 수정할 수 없습니다.");
        } else if (content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 공백으로 수정할 수 없습니다.");
        }

        // memberNum 확인
        if (memberNum <= 1 || memberNum > 15) {
            // 최소 2명이상부터 입력되도록 설정
            throw new IllegalArgumentException("스터디 멤버는 2 ~ 15명까지 설정가능합니다.");
        }else if(memberNum < currentMemberNum){
            throw new IllegalArgumentException("스터디 멤버는 현재 멤버 수보다 적게 수정 할 수 없습니다.");
        }else if(Objects.equals(currentMemberNum, memberNum)){
            // 수정한 memberNum과 study의 currntMemberNum이 같을때, recruitState 모집완료로 변경
            requestDto.setRecruitState("모집완료");
            System.out.println("모집완료 자동 변경완료");
        }

        // recruitState 확인
        if(!Objects.equals(recruitState, study.getRecruitState())){
            // 수정한 recruitState와 study의 recruitState이 같지않을때만 진입
            if(!(Objects.equals(recruitState, "모집중") || Objects.equals(recruitState, "모집완료"))){
                throw new IllegalArgumentException("모집상태는 모집중, 모집완료만 입력 가능합니다.");
            }else if(Objects.equals(recruitState, "모집중")){
                if(Objects.equals(currentMemberNum, memberNum)){
                    throw new IllegalArgumentException("모집인원이 가득차서 모집중으로 변경할 수 없습니다.");
                }
            }
        }
        return study;
    }
}
