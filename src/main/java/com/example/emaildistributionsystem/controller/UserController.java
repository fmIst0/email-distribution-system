package com.example.emaildistributionsystem.controller;

import com.example.emaildistributionsystem.dto.user.UserRequestDto;
import com.example.emaildistributionsystem.dto.user.UserResponseDto;
import com.example.emaildistributionsystem.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping(value = "/byEmail")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/byUsername")
    public UserResponseDto getByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto save(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    @PutMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseDto update(@RequestBody @Valid UserRequestDto userRequestDto,
                                  @PathVariable Integer userId) {
        return userService.update(userId, userRequestDto);
    }

    @DeleteMapping(value = "/{userId}")
    public void delete(@PathVariable Integer userId) {
        userService.delete(userId);
    }
}
