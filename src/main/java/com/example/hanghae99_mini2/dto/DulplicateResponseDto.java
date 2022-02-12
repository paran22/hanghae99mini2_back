package com.example.hanghae99_mini2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DulplicateResponseDto {
    boolean result;

    public DulplicateResponseDto(boolean checkedresult){
        this.result = checkedresult;
    }
}
