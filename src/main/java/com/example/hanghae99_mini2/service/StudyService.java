package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.StudyDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;

    // Study 생성 메소드
    public Study createStudy(StudyDto studyDto) {
        Study study = new Study(studyDto);
        return studyRepository.save(study);
    }

    // Study 업데이트 메소드
    @Transactional
    public Study updateStudy(Long StudyId, StudyDto requestDto) {
        Study study = studyRepository.findById(StudyId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 스터디 ID 입니다.")
        );
        study.update(requestDto);
        return study;
    }

    // Study 삭제 메소드
    public void deleteStudy(Long id) {
        studyRepository.deleteById(id);
    }
}
