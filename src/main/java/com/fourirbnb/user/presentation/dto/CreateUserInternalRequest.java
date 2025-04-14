package com.fourirbnb.user.presentation.dto;

import com.fourirbnb.user.domain.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserInternalRequest {
  
  private String email;
  private String password;
  private String nickname;
  private String username;
  private String phone;
  private String slackId;
  private Role role;

}
