package com.example.hanghae99_mini2.model;

import com.example.hanghae99_mini2.domain.Timestamped;
import com.example.hanghae99_mini2.dto.StudyRegisterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Study extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long registeredUserId;

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


    // createdAt 은 Timestamped에서 상속받아 사용하므로 필드에서 뺐음.

    public Study(StudyRegisterDto requestDto, Long registeredUserId){
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.content = requestDto.getContent();
        this.memberNum = requestDto.getMemberNum();
        this.registeredUserId = registeredUserId;
        // currentMemberNum은 스터디 생성시 1로 고정(스터디 개설자만 포함)
        this.currentMemberNum = 1L;
        // recruitState는 스터디 생성시 모집중으로 고정
        this.recruitState = "모집중";
    }

    public void update(StudyRegisterDto requestDto){
        this.category = requestDto.getCategory();
//        this.registeredUserId = requestDto.getRegisteredUserId();
        this.name = requestDto.getName();
        this.content = requestDto.getContent();
        this.memberNum = requestDto.getMemberNum();
        this.currentMemberNum = 1L;
        this.recruitState = "모집중";
    }
}
