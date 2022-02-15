package com.example.hanghae99_mini2.repository;

import com.example.hanghae99_mini2.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardsRepository extends JpaRepository<Study, Long> {
    List<Study> findAllByOrderByCreatedAtDesc();
    List<Study> findAllByCategoryOrderByCreatedAtDesc(String category);
}
