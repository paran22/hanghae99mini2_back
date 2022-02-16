package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.ResultResponseDto;
import com.example.hanghae99_mini2.dto.SignupRequestDto;
import com.example.hanghae99_mini2.dto.UserInfoDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.model.StudyInfo;
import com.example.hanghae99_mini2.model.User;
import com.example.hanghae99_mini2.repository.StudyInfoRepository;
import com.example.hanghae99_mini2.repository.StudyRepository;
import com.example.hanghae99_mini2.repository.UserRepository;
import com.example.hanghae99_mini2.security.UserDetailsImpl;
import com.example.hanghae99_mini2.validation.SignupValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final StudyInfoRepository studyInfoRepository;
    private final StudyRepository studyRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, StudyInfoRepository studyInfoRepository, StudyRepository studyRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.studyInfoRepository = studyInfoRepository;
        this.studyRepository = studyRepository;
    }

    public ResultResponseDto registerUser(SignupRequestDto requestDto) {

        if (SignupValidation.validationSignupInput(requestDto)) {
            String username = requestDto.getUsername();
            if (userRepository.existsByUsername(username)) {
                throw new IllegalArgumentException("중복된 username 입니다.");
            }
            // 패스워드 암호화
            String password = passwordEncoder.encode(requestDto.getPassword());
            String email = requestDto.getEmail();

            User user = new User(username, email, password);
            userRepository.save(user);
        }
        return new ResultResponseDto(true);
    }

    //username 중복확인
    public ResultResponseDto duplicateUsername(String username) {
        return new ResultResponseDto(userRepository.existsByUsername(username));
    }

    //email 중복확인
    public ResultResponseDto duplicateEmail(String email) {
        return new ResultResponseDto(userRepository.existsByEmail(email));
    }

    public UserInfoDto getUserInfo(UserDetailsImpl userDetails) {
        List<StudyInfo> studyInfoList = studyInfoRepository.findAllByUser(userDetails.getUser());
        List<Study> registerStudyList = new ArrayList<>();
        for (StudyInfo studyInfo : studyInfoList) {
            registerStudyList.add(studyInfo.getStudy());
        }
        return new UserInfoDto(
                userDetails.getUser().getId(),
                userDetails.getUsername(),
                registerStudyList
        );
    }

}