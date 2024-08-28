package com.sparta.JavaSpringAdvanceAssignmentSchedule.repository;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
