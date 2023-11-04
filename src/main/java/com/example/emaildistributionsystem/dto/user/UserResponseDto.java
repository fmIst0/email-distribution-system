package com.example.emaildistributionsystem.dto.user;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponseDto {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime createdOn;
}
