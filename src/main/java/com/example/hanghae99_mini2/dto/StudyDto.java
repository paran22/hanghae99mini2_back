package com.example.hanghae99_mini2.dto;

import com.example.hanghae99_mini2.model.StudyInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class StudyDto {
    private String category;
    private String name;
    private String content;
    private Long memberNum;
    private Long currentMemberNum;
    private String recruitState;
    private List<StudyInfo> studyInfoList;
}
