package com.sparta.JavaSpringAdvanceAssignmentSchedule.repository;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.ScheduleManager;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
