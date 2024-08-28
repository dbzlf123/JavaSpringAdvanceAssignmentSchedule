package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleManagerDto;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Schedule;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.ScheduleManager;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import lombok.Getter;

@Getter
public class ScheduleManagerResponseDto {

    private User user;
    private Schedule schedule;

    public ScheduleManagerResponseDto(ScheduleManager scheduleManager) {
        this.user = scheduleManager.getUser();
        this.schedule = scheduleManager.getSchedule();
    }
}
