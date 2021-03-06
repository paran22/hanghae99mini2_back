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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyInfoRepository studyInfoRepository;
    private final UserRepository userRepository;


    public Study createStudy(StudyRegisterDto requestDto, UserDetailsImpl userDetails) {
        // 매개변수 requestDto, UserId 유효성 검사
        User user = StudyRegisterValidation.validationStudyRegister(requestDto, userDetails, userRepository);

        // 유효성검사 끝난 user의 id 받아서 registeredUserId 설정
        Study study = new Study(requestDto, user.getId());

        // DB에 save먼저하고 StudyInfo에 저장가능합니다.
        // 영속성이 깨진다고 합니다.
        StudyInfo studyInfo = new StudyInfo(user, studyRepository.save(study));

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
        // delete할 Study find
        Study study = studyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("deleteStudy 내부 StudyIdfind 오류 입니다.")
        );
        // StudyinfoDB에서 study 필드 가진 studyInfo find
        List<StudyInfo> studyInfoList =  studyInfoRepository.findAllByStudy(study);
        for (StudyInfo studyInfo : studyInfoList) {
            if (studyInfo.getStudy() == study) {
                // studyInfoList내부 study와 find한 study가 같으면 해당 studyInfo delete
                studyInfoRepository.delete(studyInfo);
            }
        }
        studyRepository.deleteById(id);
    }

    // Study 탈퇴 메소드
    @Transactional
    public void secessionStudy(Long studyid, UserDetailsImpl userDetails) {
        Study study;

        // studyid 유효성검사
        Optional <Study> studyOptional = studyRepository.findById(studyid);
        if(!studyOptional.isPresent()){
            throw new IllegalArgumentException("secessionStudy 내부 findByIdStudy 오류.");
        }else{
            study = studyOptional.get();
        }

        User user = userDetails.getUser();

        // studyInfo 유효성검사
        Optional <StudyInfo> studyInfo = studyInfoRepository.findByUserAndStudy(user, study);
        if(!studyInfo.isPresent()){
            throw new IllegalArgumentException("secessionStudy 내부 findByUserAndStudy 오류.");
        }

        // 스터디 탈퇴 제한 및 recruitState 자동변경
        if(study.getCurrentMemberNum() == 1){
            throw new IllegalArgumentException("스터디 Member 최후의 1인은 나갈 수 없습니다. 스터디를 삭제해주세요");
        }else if(Objects.equals(study.getRecruitState(), "모집완료")){
            study.setRecruitState("모집중");
        }

        // 스터디 탈퇴처리
        study.setCurrentMemberNum(study.getCurrentMemberNum()-1);
        studyInfoRepository.delete(studyInfo.get());

    }
}
