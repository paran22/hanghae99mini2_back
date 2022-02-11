package com.example.hanghae99_mini2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StudyInfo {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public StudyInfo(User user) {
        this.user = user;
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private Study study;

//    public StudyInfo(User user, Study study) {
//        this.user = user;
//        this.study = study;
//    }
}
