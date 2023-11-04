package com.example.emaildistributionsystem.mapper;

import com.example.emaildistributionsystem.config.MapperConfig;
import com.example.emaildistributionsystem.dto.user.UserRequestDto;
import com.example.emaildistributionsystem.dto.user.UserResponseDto;
import com.example.emaildistributionsystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", expression = "java(java.time.LocalDateTime.now())")
    User toModel(UserRequestDto userRequestDto);
}
