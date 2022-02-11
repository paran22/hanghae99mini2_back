package com.example.hanghae99_mini2.repository;

import com.example.hanghae99_mini2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    //username 중복확인
    boolean existsByUsername(String username);

    //email 중복확인
    boolean existsByEmail(String email);
}
