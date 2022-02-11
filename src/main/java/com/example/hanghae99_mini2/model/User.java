package com.example.hanghae99_mini2.model;

import com.example.hanghae99_mini2.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private List<StudyInfo> studyInfoList;

    public User(UserDto requestDto){
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.studyInfoList = requestDto.getStudyInfoList();
    }
}
