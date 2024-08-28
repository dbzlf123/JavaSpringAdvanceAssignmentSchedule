package com.sparta.JavaSpringAdvanceAssignmentSchedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "scheduleManagers")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ScheduleManager extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public ScheduleManager(User user, Schedule schedule) {
        this.user = user;
        this.schedule = schedule;
    }
}
