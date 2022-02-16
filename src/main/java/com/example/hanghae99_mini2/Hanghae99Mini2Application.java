package com.example.hanghae99_mini2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// DB에 데이터가 저장되거나 수정될 때 언제, 누가 했는지를 자동으로 관리
// TimeStamped null값 오류 제거하기위해 포함
@EnableJpaAuditing
@SpringBootApplication
public class Hanghae99Mini2Application {

    public static void main(String[] args) {
        SpringApplication.run(Hanghae99Mini2Application.class, args);
    }

}
