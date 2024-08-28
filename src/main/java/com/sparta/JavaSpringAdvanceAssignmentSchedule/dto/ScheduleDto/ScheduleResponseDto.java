package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto.CommentResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.UserResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Schedule;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String name;
    private String title;
    private String todo;
    private List<CommentResponseDto> commentResponseDtoList;
    private UserResponseDto createUser;
    private List<UserResponseDto> userResponseDtoList;
    private String weather;


    public ScheduleResponseDto(Schedule schedule, List<User> userList) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.todo = schedule.getTodo();
        //그냥 엔티니 보내주면 JSON 무한 루프 일어나므로 DTO 에 필요한것 담아서 보내준다.
        this.commentResponseDtoList = schedule.getCommentList().stream().map(CommentResponseDto::new).toList();
        this.createUser = new UserResponseDto(schedule.getCreateUser());
        this.userResponseDtoList = userList.stream().map(UserResponseDto::new).toList();
        this.weather = schedule.getWeather();
    }

}
