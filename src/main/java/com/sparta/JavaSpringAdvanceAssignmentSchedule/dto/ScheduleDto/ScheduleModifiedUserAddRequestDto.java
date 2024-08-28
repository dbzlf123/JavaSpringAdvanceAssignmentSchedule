package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleModifiedUserAddRequestDto {

    private Long id;
    private List<Long> modifyUserIds;
}
