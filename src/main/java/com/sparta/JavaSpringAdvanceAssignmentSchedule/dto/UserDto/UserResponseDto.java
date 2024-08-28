package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
