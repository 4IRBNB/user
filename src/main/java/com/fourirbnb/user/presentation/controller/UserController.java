package com.fourirbnb.user.presentation.controller;

import com.fourirbnb.common.response.BaseResponse;
import com.fourirbnb.common.security.AuthenticatedUser;
import com.fourirbnb.common.security.RoleCheck;
import com.fourirbnb.common.security.UserInfo;
import com.fourirbnb.user.application.service.UserService;
import com.fourirbnb.user.presentation.dto.UpdateUserRequest;
import com.fourirbnb.user.presentation.dto.UserResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  //단건조회
  @RoleCheck("CUSTOMER")
  @GetMapping("/my-page")
  public BaseResponse<UserResponseDto> getUserById(@AuthenticatedUser UserInfo user) {
    Long userId = user.getUserId();
    UserResponseDto response = userService.getUserById(userId);
    return BaseResponse.SUCCESS(response, "회원 조회 성공", HttpStatus.OK.value());
  }

  //전체 조회
  @RoleCheck("MASTER")
  public BaseResponse<List<UserResponseDto>> getAllUsers() {
    List<UserResponseDto> response = userService.getAllUsers();
    return BaseResponse.SUCCESS(response, "회원 전체조회 성공", HttpStatus.OK.value());
  }

  //수정
  @RoleCheck("CUSTOMER")
  public BaseResponse<UserResponseDto> updateUser(@AuthenticatedUser UserInfo user,
      @RequestBody UpdateUserRequest request) {
    Long userId = user.getUserId();
    UserResponseDto response = userService.updateUser(userId, request);
    return BaseResponse.SUCCESS(response, "회원정보 수정 성공", HttpStatus.OK.value());
  }

  //회원탈퇴(삭제) - 본인 관리자 하나 만들어야할듯
  @RoleCheck("CUSTOMER")
  public void deleteUser(@AuthenticatedUser UserInfo user) {
    Long id = user.getUserId();
    userService.deleteUser(id);
  }

}
