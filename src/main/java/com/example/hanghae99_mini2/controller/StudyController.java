package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.StudyDto;
import com.example.hanghae99_mini2.dto.UserDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class StudyController {
    private final StudyService studyService;

    @PostMapping("/board/write")
    public Study createStudy(@RequestBody StudyDto requestDto, @RequestBody UserDto userDto){
        return studyService.createStudy(requestDto);
    }

//    @PutMapping("/board/{id}/update")
//    public void
}
