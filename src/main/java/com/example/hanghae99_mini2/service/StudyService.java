package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.StudyDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.repository.StudyRepository;
import com.example.hanghae99_mini2.repository.UserRepository;
import com.example.hanghae99_mini2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    // Study 생성 및 등록한 User의 StudyInfo 등록 메소드
    public Study createStudy(StudyDto requestDto, UserDetailsImpl userDetails) {


        // Study 생성 유효성 검사
//        StudyDto studyDto =  StudyRegisterValidation.validationStudyRegister(requestDto, userDetails);

         Long registeredUserId = userDetails.getUser().getId();
//        Long registeredId = 1L;
//        User user = userRepository.findById(registeredId)
//                .orElseThrow(() -> new IllegalArgumentException("createStudy 내부 user find 오류"));

        Study study = new Study(requestDto, registeredUserId);
//        StudyInfo studyInfo = new StudyInfo(user, study);

        // Study 생성 후 바로 StudyInfo 추가
//        studyInfoRepository.save(studyInfo);

        return studyRepository.save(study);
    }

    // Study 업데이트 메소드
//    @Transactional
//    public Study updateStudy(Long StudyId, StudyDto requestDto) {
//        Study study = studyRepository.findById(StudyId).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 스터디 ID 입니다.")
//        );
//        study.update(requestDto);
//        return study;
//    }

    // Study 삭제 메소드
    public void deleteStudy(Long id) {
        studyRepository.deleteById(id);
    }
}
