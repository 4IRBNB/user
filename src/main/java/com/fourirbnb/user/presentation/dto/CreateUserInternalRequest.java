package com.fourirbnb.user.presentation.dto;

import com.fourirbnb.user.domain.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserInternalRequest {

  @NotBlank(message = "아이디 필수")
  @Email(message = "이메일 형식으로 입력해주세요.")
  private String email;

  @NotBlank(message = "비밀번호 필수")
  private String password;

  @NotBlank(message = "닉네임 필수")
  private String nickname;

  @NotBlank(message = "사용자 이름 필수")
  private String username;

  private String slackId;

  @Pattern(regexp = "^01[0-9]-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
  private String phone;
  private Role role;
}
