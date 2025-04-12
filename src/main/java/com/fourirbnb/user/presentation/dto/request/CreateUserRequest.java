package com.fourirbnb.user.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

  private String email;
  private String nickname;
  private String username;
  private String phone;
  private String slackId;
  private String role;

}
