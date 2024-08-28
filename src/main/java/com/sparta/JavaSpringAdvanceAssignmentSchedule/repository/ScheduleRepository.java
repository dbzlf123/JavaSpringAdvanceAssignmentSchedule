package com.sparta.JavaSpringAdvanceAssignmentSchedule.repository;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
