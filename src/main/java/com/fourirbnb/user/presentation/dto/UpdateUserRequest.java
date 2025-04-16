package com.fourirbnb.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

  String nickname;
  String phone;
  String slackId;
}
