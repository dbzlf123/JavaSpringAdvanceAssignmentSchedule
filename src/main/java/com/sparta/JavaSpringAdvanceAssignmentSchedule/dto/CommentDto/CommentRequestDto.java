package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String name;
    private String todo;
    private Long schedule_id;
}
