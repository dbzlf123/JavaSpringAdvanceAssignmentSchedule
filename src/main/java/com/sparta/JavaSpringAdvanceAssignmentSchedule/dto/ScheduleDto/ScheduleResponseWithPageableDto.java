package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseWithPageableDto {

    private String name;
    private String title;
    private String todo;
    private String weather;
    private int numberOfComments;

    public ScheduleResponseWithPageableDto(Schedule schedule) {
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.todo = schedule.getTodo();
        this.weather = schedule.getWeather();
        this.numberOfComments = schedule.getCommentList().size();
    }
}
