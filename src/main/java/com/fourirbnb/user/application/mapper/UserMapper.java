package com.fourirbnb.user.application.mapper;

import com.fourirbnb.user.application.dto.ApprovalUserInternalRequest;
import com.fourirbnb.user.domain.entity.ApprovalStatus;
import com.fourirbnb.user.domain.entity.Staging;
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
        request.getRole(),
        ApprovalStatus.APPROVED
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

  public static ApprovalUserInternalRequest withStatus(CreateUserInternalRequest request,
      ApprovalStatus status) {
    return ApprovalUserInternalRequest.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .nickname(request.getNickname())
        .username(request.getUsername())
        .slackId(request.getSlackId())
        .phone(request.getPhone())
        .role(request.getRole())
        .status(status)
        .build();
  }

  public static Staging toStagingEntity(ApprovalUserInternalRequest request) {
    return new Staging(
        request.getEmail(),
        request.getPassword(),
        request.getUsername(),
        request.getNickname(),
        request.getSlackId(),
        request.getPhone(),
        request.getRole(),
        request.getStatus()
    );

  }

  public static User toEntity(Staging staging) {
    return new User(
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

}
