package com.example.hanghae99_mini2.repository;

import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.model.StudyInfo;
import com.example.hanghae99_mini2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyInfoRepository extends JpaRepository<StudyInfo, Long> {
    List<StudyInfo> findAllByUser(User user);

}
