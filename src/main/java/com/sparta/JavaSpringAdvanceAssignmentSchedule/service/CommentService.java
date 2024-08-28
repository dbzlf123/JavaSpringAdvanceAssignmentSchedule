package com.sparta.JavaSpringAdvanceAssignmentSchedule.service;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto.CommentRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto.CommentResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Comment;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Schedule;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.repository.CommentRepository;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto create(CommentRequestDto commentRequestDto, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->new IllegalArgumentException("해당하는 ID 의 일정이 존재하지 않습니다."));
        Comment comment = new Comment(commentRequestDto, schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getComments(){
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    public Long update(Long id, CommentRequestDto commentRequestDto){
        Comment comment = findComment(id);
        comment.update(commentRequestDto);
        return id;
    }

    public Long delete(Long id){
        Comment comment = findComment(id);
        commentRepository.delete(comment);
        return id;
    }

    public CommentResponseDto findCommentWithDto(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 댓글은 없습니다."));
        return new CommentResponseDto(comment);
    }

    public Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 댓글은 없습니다."));
    }
}
