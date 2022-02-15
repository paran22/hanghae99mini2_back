package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.StudyRegisterDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.model.StudyInfo;
import com.example.hanghae99_mini2.model.User;
import com.example.hanghae99_mini2.repository.StudyInfoRepository;
import com.example.hanghae99_mini2.repository.StudyRepository;
import com.example.hanghae99_mini2.repository.UserRepository;
import com.example.hanghae99_mini2.validation.StudyRegisterValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyInfoRepository studyInfoRepository;
    private final UserRepository userRepository;

    // Study 생성 및 등록한 User의 StudyInfo 등록 메소드
    public Study createStudy(StudyRegisterDto requestDto) {
        //Study 생성 유효성 검사
        StudyRegisterValidation.validationStudyRegister(requestDto);

        // 임시 registeredUserId 설정, UserDetailsImpl 값 대체
        Long registeredUserId = 1L;
        User user = userRepository.findById(registeredUserId)
                .orElseThrow(() -> new IllegalArgumentException("createStudy 내부 user find 오류"));
        Study study = new Study(requestDto, registeredUserId);

        // DB에 save먼저하고 StudyInfo에 저장가능합니다.
        // 영속성이 깨진다고 합니다.
        studyRepository.save(study);
        StudyInfo studyInfo = new StudyInfo(user, study);

        //Study 생성 후 바로 StudyInfo 추가(생성과 동시에 스터디 가입 처리)
        studyInfoRepository.save(studyInfo);

        return study;
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
