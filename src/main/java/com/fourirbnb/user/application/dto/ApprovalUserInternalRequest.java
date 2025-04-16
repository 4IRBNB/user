package com.fourirbnb.user.application.dto;

import com.fourirbnb.user.domain.entity.ApprovalStatus;
import com.fourirbnb.user.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalUserInternalRequest {

  private String email;
  private String password;
  private String nickname;
  private String username;
  private String slackId;
  private String phone;
  private Role role;
  private ApprovalStatus status;
}