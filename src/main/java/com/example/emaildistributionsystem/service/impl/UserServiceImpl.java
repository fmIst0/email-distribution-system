package com.example.emaildistributionsystem.service.impl;

import com.example.emaildistributionsystem.exception.EntityNotFoundException;
import com.example.emaildistributionsystem.dto.user.UserRequestDto;
import com.example.emaildistributionsystem.dto.user.UserResponseDto;
import com.example.emaildistributionsystem.mapper.UserMapper;
import com.example.emaildistributionsystem.model.User;
import com.example.emaildistributionsystem.repository.user.UserRepository;
import com.example.emaildistributionsystem.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final int DEFAULT_PAGE_SIZE = 5;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> findAll(Pageable pageable) {
        return userRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                        DEFAULT_PAGE_SIZE,
                        Sort.by(Sort.Order.desc("createdOn"))))
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return userMapper.toUserResponse(userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email: " + email + " does not exist."
                )));
    }

    @Override
    public UserResponseDto findByUsername(String username) {
        return userMapper.toUserResponse(userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email: " + username + " does not exist."
                )));
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Integer id, UserRequestDto userRequestDto) {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Update error! User with id: " + id + " does not exist."
                ));
        userFromDB.setEmail(userRequestDto.getEmail());
        userFromDB.setUsername(userRequestDto.getUsername());
        return userMapper.toUserResponse(userRepository.save(userFromDB));
    }

    @Override
    public void delete(Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(
                    "Update error! User with id: " + id + " does not exist."
            );
        }
        userRepository.deleteById(id);
    }
}
