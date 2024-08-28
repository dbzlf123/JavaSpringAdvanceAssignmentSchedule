package com.sparta.JavaSpringAdvanceAssignmentSchedule.repository;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.ScheduleManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleManagerRepository  extends JpaRepository<ScheduleManager, Long> {
    List<ScheduleManager> findByScheduleId(Long scheduleId);
}
