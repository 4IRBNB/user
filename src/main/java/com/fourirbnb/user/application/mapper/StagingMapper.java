package com.fourirbnb.user.application.mapper;

import com.fourirbnb.user.domain.entity.Staging;
import com.fourirbnb.user.presentation.dto.StagingResponseDto;

public class StagingMapper {

  public static StagingResponseDto toResponse(Staging staging) {
    return new StagingResponseDto(
        staging.getId(),
        staging.getEmail(),
        staging.getUsername(),
        staging.getNickname(),
        staging.getPhone(),
        staging.getSlackId(),
        staging.getRole(),
        staging.getStatus()
    );
  }
}
