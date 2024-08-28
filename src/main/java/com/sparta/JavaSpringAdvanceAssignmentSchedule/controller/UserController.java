package com.sparta.JavaSpringAdvanceAssignmentSchedule.controller;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.LoginRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.UserRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.UserResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.jwt.JwtUtil;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/users/create")
    public UserResponseDto create(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }

    @PostMapping("/users/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        return userService.login(loginRequestDto, httpServletResponse);
    }

    @GetMapping("/users")
    public List<UserResponseDto> getUsers( HttpServletResponse httpServletResponse){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponseDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.update(id, userRequestDto);
    }



    @DeleteMapping("/users/{id}")
    public Long delete(@PathVariable Long id){
        return userService.delete(id);
    }
}
