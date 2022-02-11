package com.example.hanghae99_mini2.model;

import com.example.hanghae99_mini2.dto.StudyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Study {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long memberNum;

    @Column(nullable = false)
    private Long currentMemberNum;

    @Column(nullable = false)
    private String recruitState;

    @OneToMany
    private List<StudyInfo> studyInfoList;

    // createdAt은 Timestamped에서 상속받아 사용하므로 필드에서 뺐음.

    public Study(StudyDto requestDto){
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.content = requestDto.getContent();
        this.memberNum = requestDto.getMemberNum();
        this.currentMemberNum = requestDto.getCurrentMemberNum();
        this.recruitState = requestDto.getRecruitState();
        this.studyInfoList = requestDto.getStudyInfoList();
    }
}