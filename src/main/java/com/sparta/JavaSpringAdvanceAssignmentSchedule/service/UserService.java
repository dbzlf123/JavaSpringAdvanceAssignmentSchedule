package com.sparta.JavaSpringAdvanceAssignmentSchedule.service;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.config.PasswordEncoder;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.LoginRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.UserRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.UserDto.UserResponseDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.User;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.entity.UserRoleEnum;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.jwt.JwtUtil;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponseDto create(UserRequestDto userRequestDto){
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        String email = userRequestDto.getEmail();;
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()) throw new IllegalArgumentException("중복된 Email 입니다.");

        UserRoleEnum role = UserRoleEnum.USER;
        // 사용자 ROLE 확인
        if(userRequestDto.getRole() != null && userRequestDto.getRole().equals("ADMIN")) role = UserRoleEnum.ADMIN;

        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), password, role);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public String login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user =  userRepository.findByEmail(email).orElseThrow(()->
                new IllegalArgumentException("해당 사용자가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        jwtUtil.addJwtToCookie(token, httpServletResponse);

        return "로그인 완료";
    }

    public List<UserResponseDto> getUsers(){
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto getUser(Long id){
        return new UserResponseDto(findUser(id));
    }

    public User findUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 유저는 없습니다."));
    }

    @Transactional
    public Long update(Long id, UserRequestDto userRequestDto){
        User user = findUser(id);
        user.update(userRequestDto);
        return id;
    }

    public Long delete(Long id){
        User user = findUser(id);
        userRepository.delete(user);
        return id;
    }
}
