package com.example.emaildistributionsystem.service;

import com.example.emaildistributionsystem.dto.user.UserRequestDto;
import com.example.emaildistributionsystem.dto.user.UserResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface UserService {
    List<UserResponseDto> findAll(Pageable pageable);

    UserResponseDto findByEmail(String email);

    UserResponseDto findByUsername(String username);

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto update(Integer id, UserRequestDto userRequestDto);

    void delete(Integer id);
}
