package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.repository.BoardsRepository;
import com.example.hanghae99_mini2.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
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
    public Optional<Study> getBoard(@PathVariable Long id) {
        return boardsRepository.findById(id);
    }

    @PutMapping("/board/{id}/register/{userid}")
    public void recruitStudy(@PathVariable Long id, @PathVariable Long userid) {
        boardsService.recruitStudy(id, userid);
    }

    @GetMapping("/boards/{category}")
    public List<Study> divideCategory(@PathVariable String category) throws UnsupportedEncodingException {
        return boardsRepository.findAllByCategoryOrderByCreatedAtDesc(category);
    }
}
