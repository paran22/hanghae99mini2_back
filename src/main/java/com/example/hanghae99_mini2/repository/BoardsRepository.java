package com.example.hanghae99_mini2.repository;

public interface BoardsRepository extends JpaRepository<Study, Long> {
    List<Study> findAllByOrderByCreatedAtDesc();
}
