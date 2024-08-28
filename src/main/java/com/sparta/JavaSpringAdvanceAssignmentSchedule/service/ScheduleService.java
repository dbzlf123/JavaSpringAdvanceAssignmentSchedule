package com.sparta.JavaSpringAdvanceAssignmentSchedule.service;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleModifiedUserAddRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleResponseWithPageableDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Schedule;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.ScheduleManager;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.repository.ScheduleManagerRepository;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.repository.ScheduleRepository;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    @Autowired
    private final ScheduleRepository scheduleRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ScheduleManagerRepository scheduleManagerRepository;
    @Autowired
    private final RestTemplateApiService restTemplateApiService;

    @Transactional
    public ScheduleResponseDto create(ScheduleRequestDto scheduleRequestDto, User createUser, List<Long> modifyUserIdList){
        String weather = restTemplateApiService.getWeather();
        Schedule schedule = new Schedule(scheduleRequestDto, createUser, weather);
        List<User> modifyUserIds = new ArrayList<>();

        for(Long id : modifyUserIdList){
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("해당하는 사람을 찾을 수 없습니다."));
            modifyUserIds.add(user);
            ScheduleManager userSchedule = new ScheduleManager(user, schedule);
            scheduleManagerRepository.save(userSchedule);
        }

        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule, modifyUserIds);
    }

    public Page<ScheduleResponseWithPageableDto> getSchedules(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        return schedulePage.map(ScheduleResponseWithPageableDto::new);
    }

    @Transactional
    public Long update(Long id, ScheduleRequestDto scheduleRequestDto){
        Schedule schedule = findSchedule(id);
        schedule.update(scheduleRequestDto);
        return id;
    }

    public Long delete(Long id){
        Schedule schedule = findSchedule(id);
        scheduleRepository.delete(schedule);
        return id;
    }

    public ScheduleResponseDto findScheduleWithDto(Long id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 일정은 없습니다."));
        List<ScheduleManager> scheduleManagerList = scheduleManagerRepository.findByScheduleId(id);
        List<User> users= scheduleManagerList.stream()
                .map(ScheduleManager::getUser).toList();

        return new ScheduleResponseDto(schedule, users);
    }

    @Transactional
    public Long addUser(Long scheduleId, ScheduleModifiedUserAddRequestDto scheduleModifiedUserAddRequestDto){
        Schedule schedule = findSchedule(scheduleId);

        for(Long id : scheduleModifiedUserAddRequestDto.getModifyUserIds()){
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("해당하는 사람을 찾을 수 없습니다."));
            ScheduleManager userSchedule = new ScheduleManager(user, schedule);
            scheduleManagerRepository.save(userSchedule);
        }

        return scheduleModifiedUserAddRequestDto.getId();
    }

    public Schedule findSchedule(Long id){
        return scheduleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 일정은 없습니다."));
    }
}
