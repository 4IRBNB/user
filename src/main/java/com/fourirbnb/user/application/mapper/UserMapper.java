package com.fourirbnb.user.application.mapper;

import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.UserInternalResponse;
import com.fourirbnb.user.presentation.dto.UserResponseDto;


public class UserMapper {

  public static User toEntity(CreateUserInternalRequest request) {
    return new User(
        request.getEmail(),
        request.getPassword(),
        request.getNickname(),
        request.getUsername(),
        request.getPhone(),
        request.getSlackId(),
        request.getRole()
    );
  }

  public static UserResponseDto toResponse(User user) {
    return new UserResponseDto(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        user.getUsername(),
        user.getNickname(),
        user.getPhone(),
        user.getSlackId(),
        user.getRole()
    );
  }

  public static UserInternalResponse toInternalResponse(User user) {
    return new UserInternalResponse(
        user.getId(),
        user.getPassword()
    );
  }


}
