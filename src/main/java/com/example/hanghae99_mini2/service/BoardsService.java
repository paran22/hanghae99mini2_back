package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.model.StudyInfo;
import com.example.hanghae99_mini2.repository.BoardsRepository;
import com.example.hanghae99_mini2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final UserRepository userRepository;
    private final StudyInfoRepository studyInfoRepository;


    public  void recruitStudy(Long id, Long userId) {
        Study study = boardsRepository.findById(id);
        User user = userRepository.findById(userId);


        StudyInfo newInfo = new StudyInfo();

        Long memberNum = study.getMemberNum();
        Long currentMemberNum = study.getCurrentMemberNum();

        if (++currentMemberNum > memberNum) {
            throw new IllegalArgumentException("모집인원이 이미 충족되었습니다.");
        } else {
            study.setCurrentMemberNum(++currentMemberNum);
            if(++currentMemberNum == memberNum) {
                study.setRecruitState("모집완료");
            }


            newInfo.setUser(user);
            newInfo.setStudy(boardsRepository.save(study));

            studyInfoRepository.save(newInfo);

        }

    }
}
