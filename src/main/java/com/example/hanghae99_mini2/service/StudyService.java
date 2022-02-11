package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.StudyDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;

    // Study 생성 메소드
    public Study createStudy(StudyDto studyDto) {
        Study study = new Study(studyDto);
        studyRepository.save(study);
        return study;
    }
}
