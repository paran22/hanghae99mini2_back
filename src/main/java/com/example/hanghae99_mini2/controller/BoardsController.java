package com.example.hanghae99_mini2.controller;

import com.example.hanghae99_mini2.dto.BoardResponseDto;
import com.example.hanghae99_mini2.model.Study;
import com.example.hanghae99_mini2.repository.BoardsRepository;
import com.example.hanghae99_mini2.security.UserDetailsImpl;
import com.example.hanghae99_mini2.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardsService.getBoard(id);
    }

    @PutMapping("/board/{id}/register")
    public void recruitStudy(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardsService.recruitStudy(id, userDetails);
    }

    @GetMapping("/boards/{category}")
    public List<Study> divideCategory(@PathVariable String category) throws UnsupportedEncodingException {
        return boardsRepository.findAllByCategoryOrderByCreatedAtDesc(category);
    }
}
