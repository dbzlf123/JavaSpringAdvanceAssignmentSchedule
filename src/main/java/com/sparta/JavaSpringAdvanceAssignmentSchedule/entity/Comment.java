package com.sparta.JavaSpringAdvanceAssignmentSchedule.entity;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.CommentDto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "todo", nullable = true)
    private String todo;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(String name, String todo, Schedule schedule) {
        this.name = name;
        this.todo = todo;
        this.schedule = schedule;
    }

    public Comment(CommentRequestDto commentRequestDto){
        this.name = commentRequestDto.getName();
        this.todo = commentRequestDto.getTodo();
    }

    public Comment(CommentRequestDto commentRequestDto, Schedule schedule) {
        this.name = commentRequestDto.getName();
        this.todo = commentRequestDto.getTodo();
        this.schedule = schedule;
    }

    public void update(CommentRequestDto commentRequestDto){
        this.name = commentRequestDto.getName();
        this.todo = commentRequestDto.getTodo();
    }
}
