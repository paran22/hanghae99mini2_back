package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.repository.BoardsRepository;
import com.example.hanghae99_mini2.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardsController {
    private final BoardsRepository boardsRepository;
    private final BoardsService boardsService;

    @GetMapping("/boards")
    public List<Study> getBoards() {
        return boardsRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/board/{id}")
    public Study getBoard(@PathVariable Long id) {
        return boardsRepository.findById(id);
    }

    @PutMapping("/board/{id}/register/{username}")
    public void recruitStudy(@PathVariable Long id, String username) {
        boardsService.recruitStudy(id, username);
    }
}
