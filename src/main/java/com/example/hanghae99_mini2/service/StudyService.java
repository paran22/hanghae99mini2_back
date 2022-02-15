package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.StudyRegisterDto;
import com.example.hanghae99_mini2.dto.StudyUpdateDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.model.StudyInfo;
import com.example.hanghae99_mini2.model.User;
import com.example.hanghae99_mini2.repository.StudyInfoRepository;
import com.example.hanghae99_mini2.repository.StudyRepository;
import com.example.hanghae99_mini2.repository.UserRepository;
import com.example.hanghae99_mini2.security.UserDetailsImpl;
import com.example.hanghae99_mini2.validation.StudyRegisterValidation;
import com.example.hanghae99_mini2.validation.StudyUpdateValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyInfoRepository studyInfoRepository;
    private final UserRepository userRepository;

    // Study 생성 및 등록한 User의 StudyInfo 등록 메소드
    public Study createStudy(StudyRegisterDto requestDto, UserDetailsImpl userDetails) {
        // 매개변수 requestDto, UserDetails 유효성 검사
        StudyRegisterValidation.validationStudyRegister(requestDto, userDetails, userRepository);

        // 유효성검사 끝난 user의 id 받아서 registeredUserId 설정
        Long registeredUserId = userDetails.getUser().getId();
        // (테스트용)
//        Long registeredUserId = 1L;

        Study study = new Study(requestDto, registeredUserId);

        // (테스트용)
        User user = userRepository.findById(registeredUserId)
                .orElseThrow(() -> new IllegalArgumentException("createStudy 내부 user find 오류"));

        // DB에 save먼저하고 StudyInfo에 저장가능합니다.
        // 영속성이 깨진다고 합니다.
        studyRepository.save(study);
        StudyInfo studyInfo = new StudyInfo(user, study);

        //Study 생성 후 바로 StudyInfo 추가(생성과 동시에 스터디 가입 처리)
        studyInfoRepository.save(studyInfo);

        return study;
    }

    // Study 업데이트 메소드
    @Transactional
    public Study updateStudy(Long id, StudyUpdateDto requestDto) {
        // 매개변수 id, requestDto 유효성검사
        Study study = StudyUpdateValidation.validationStudyUpdate(id, requestDto, studyRepository);
        study.update(requestDto);
        return study;
    }

    // Study 삭제 메소드
    public void deleteStudy(Long id) {
        studyRepository.deleteById(id);
    }
}
