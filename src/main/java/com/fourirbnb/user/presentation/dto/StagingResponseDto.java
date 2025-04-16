package com.fourirbnb.user.presentation.dto;

import com.fourirbnb.user.domain.entity.ApprovalStatus;
import com.fourirbnb.user.domain.entity.Role;
import java.util.UUID;

public record StagingResponseDto(
    UUID id,
    String email,
    String username,
    String nickname,
    String phone,
    String slackId,
    Role role,
    ApprovalStatus status
) {

}
