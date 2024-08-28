package com.sparta.JavaSpringAdvanceAssignmentSchedule.entity;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.ScheduleDto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Schedule extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "todo", nullable = true)
    private String todo;
    @Column(name = "weather")
    private String weather;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "create_user_id")
    private User createUser;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ScheduleManager> scheduleManagerList = new ArrayList<>();

    public Schedule(ScheduleRequestDto scheduleRequestDto){
        this.name = scheduleRequestDto.getName();
        this.title = scheduleRequestDto.getTitle();
        this.todo = scheduleRequestDto.getTodo();
    }

    public void update(ScheduleRequestDto scheduleRequestDto){
        this.name = scheduleRequestDto.getName();
        this.title = scheduleRequestDto.getTitle();
        this.todo = scheduleRequestDto.getTodo();
    }

    public Schedule(ScheduleRequestDto scheduleRequestDto, User createdUser, String weather){
        this.name = scheduleRequestDto.getName();
        this.title = scheduleRequestDto.getTitle();
        this.todo = scheduleRequestDto.getTodo();
        this.createUser = createdUser;
        this.weather = weather;
    }
}
