package com.fourirbnb.user.application.mapper;

import com.fourirbnb.user.domain.entity.Admin;
import com.fourirbnb.user.domain.entity.ApprovalStatus;
import com.fourirbnb.user.domain.entity.Staging;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.UserResponseDto;

public class AdminMapper {

  public static Admin toEntity(CreateUserInternalRequest request) {
    return new Admin(
        request.getEmail(),
        request.getPassword(),
        request.getNickname(),
        request.getUsername(),
        request.getPhone(),
        request.getSlackId(),
        request.getRole(),
        ApprovalStatus.APPROVED
    );
  }

  public static Admin toEntity(Staging staging) {
    return new Admin(
        staging.getEmail(),
        staging.getPassword(),
        staging.getNickname(),
        staging.getUsername(),
        staging.getPhone(),
        staging.getSlackId(),
        staging.getRole(),
        ApprovalStatus.APPROVED
    );
  }

  public static UserResponseDto toResponse(Admin admin) {
    return new UserResponseDto(
        admin.getId(),
        admin.getEmail(),
        admin.getPassword(),
        admin.getNickname(),
        admin.getPhone(),
        admin.getSlackId(),
        admin.getUsername(),
        admin.getRole()
    );
  }
}