package com.fourirbnb.user.presentation.dto;

import com.fourirbnb.user.domain.entity.Role;

public record UserResponseDto(
    Long id,
    String email,
    String password,
    String username,
    String nickname,
    String phone,
    String slackId,
    Role role) {

}
