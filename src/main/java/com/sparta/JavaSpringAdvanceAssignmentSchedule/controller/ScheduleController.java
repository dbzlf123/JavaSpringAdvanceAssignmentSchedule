package com.sparta.JavaSpringAdvanceAssignmentSchedule.controller;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleResponseWithPageableDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleModifiedUserAddRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.jwt.JwtUtil;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.service.ScheduleService;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    @Autowired
    private final ScheduleService scheduleService;
    @Autowired
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/schedules")
    public ScheduleResponseDto create(@RequestBody ScheduleRequestDto scheduleRequestDto){
        User createUser = userService.findUser(scheduleRequestDto.getCreateUserId());
        List<Long> modifyUserIds = scheduleRequestDto.getModifyUserIds();

        return scheduleService.create(scheduleRequestDto, createUser, modifyUserIds);
    }

    @GetMapping("/schedules")
    public Page<ScheduleResponseWithPageableDto> getSchedules(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize){
        return scheduleService.getSchedules(pageNumber, pageSize);
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto findSchedule(@PathVariable Long id){
        return scheduleService.findScheduleWithDto(id);
    }

    @PutMapping("/schedules/{id}")
    public Long update(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue, @PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto){
        jwtUtil.checkAuth(tokenValue);
        return scheduleService.update(id, scheduleRequestDto);
    }

    @PutMapping("/schedules/addUsers/{scheduleId}")
    public Long addUser(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue, @PathVariable Long scheduleId, @RequestBody ScheduleModifiedUserAddRequestDto scheduleModifiedUserAddRequestDto){
        jwtUtil.checkAuth(tokenValue);
        return scheduleService.addUser(scheduleId, scheduleModifiedUserAddRequestDto);
    }

    @DeleteMapping("/schedules/{id}")
    public Long delete(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue, @PathVariable Long id){
        jwtUtil.checkAuth(tokenValue);
        return scheduleService.delete(id);
    }
}
