package com.example.hanghae99_mini2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResultResponseDto {
    boolean result;

    public ResultResponseDto(boolean checkedresult){
        this.result = checkedresult;
    }
}
