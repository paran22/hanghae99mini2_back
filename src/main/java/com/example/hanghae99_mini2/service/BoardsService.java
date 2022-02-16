package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.model.StudyInfo;
import com.example.hanghae99_mini2.model.User;
import com.example.hanghae99_mini2.repository.BoardsRepository;
import com.example.hanghae99_mini2.repository.StudyInfoRepository;
import com.example.hanghae99_mini2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final UserRepository userRepository;
    private final StudyInfoRepository studyInfoRepository;


    public void recruitStudy(Long id, Long userId) {
        Study study;
        User user;

        Optional<Study> tempStudy = boardsRepository.findById(id);
        if(tempStudy.isPresent()) {
            study = tempStudy.get();
        } else {
            throw new IllegalArgumentException("해당 스터디가 없습니다.");
        }

        Optional<User> tempUser = userRepository.findById(userId);
        if(tempUser.isPresent()) {
            user = tempUser.get();
        } else {
            throw new IllegalArgumentException("해당 유저가 없습니다.");
        }

        List<StudyInfo> studyInfos = studyInfoRepository.findAllByUser(user);
        for (int i = 0; i <studyInfos.size(); i++) {
            if(studyInfos.get(i).getStudy().equals(study)) {
                throw new IllegalArgumentException("이미 등록된 유저입니다.");
            }
        }

        StudyInfo newInfo = new StudyInfo();

        Long memberNum = study.getMemberNum();
        Long currentMemberNum = study.getCurrentMemberNum();

        if (currentMemberNum >= memberNum) {
            throw new IllegalArgumentException("모집인원이 이미 충족되었습니다.");
        } else {
            study.setCurrentMemberNum(currentMemberNum + 1);
            if(study.getCurrentMemberNum().equals(memberNum)) {
                study.setRecruitState("모집완료");
            }

            newInfo.setUser(user);
            newInfo.setStudy(boardsRepository.save(study));

            studyInfoRepository.save(newInfo);
        }

    }
}
