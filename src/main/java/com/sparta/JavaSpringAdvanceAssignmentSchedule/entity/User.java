package com.sparta.JavaSpringAdvanceAssignmentSchedule.entity;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column()
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ScheduleManager> scheduleManagerList = new ArrayList<>();

    public User(UserRequestDto userRequestDto) {
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
        this.password = userRequestDto.getPassword();
    }

    public User(String name, String email, String password, UserRoleEnum role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void update(UserRequestDto userRequestDto){
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
    }


}
