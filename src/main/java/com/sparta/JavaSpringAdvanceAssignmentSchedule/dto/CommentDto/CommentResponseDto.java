package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String name;
    private String todo;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.name = comment.getName();
        this.todo = comment.getTodo();
    }

}
