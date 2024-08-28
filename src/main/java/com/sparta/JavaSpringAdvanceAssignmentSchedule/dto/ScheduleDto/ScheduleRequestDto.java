package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleRequestDto {

    private String name;
    private String title;
    private String todo;
    private Long createUserId;
    private List<Long> modifyUserIds;

}
