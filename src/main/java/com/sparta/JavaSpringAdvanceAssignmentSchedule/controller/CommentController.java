package com.sparta.JavaSpringAdvanceAssignmentSchedule.controller;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto.CommentRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto.CommentResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    // 저장, 단건 조회, 전체 조회, 수정, 삭제
    @Autowired
    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDto create(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.create(commentRequestDto, commentRequestDto.getSchedule_id());
    }

    @GetMapping("/comments")
    public List<CommentResponseDto> getComments(){
        return commentService.getComments();
    }

    @GetMapping("/comments/{id}")
    public CommentResponseDto findComment(@PathVariable Long id){
        return commentService.findCommentWithDto(id);
    }

    @PutMapping("/comments/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.update(id, commentRequestDto);
    }

    @DeleteMapping("/comments/{id}")
    public Long delete(@PathVariable Long id){
        return commentService.delete(id);
    }
}
