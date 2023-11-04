package com.example.emaildistributionsystem.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    @Email(message = "Email is not valid.")
    private String email;
}
