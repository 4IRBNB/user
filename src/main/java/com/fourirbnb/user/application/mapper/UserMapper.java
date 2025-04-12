package com.fourirbnb.user.application.mapper;

import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.presentation.dto.request.CreateUserRequest;
import com.fourirbnb.user.presentation.dto.response.UserResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface UserMapper {

  UserResponse toResponse(User user);

  User toEntity(CreateUserRequest request);
}
