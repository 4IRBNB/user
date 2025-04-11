package com.fouribnb.user.application.mapper;

import com.fouribnb.user.domain.entity.User;
import com.fouribnb.user.presentation.dto.response.UserResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface UserMapper {

  UserResponse toResponse(User user);
}
