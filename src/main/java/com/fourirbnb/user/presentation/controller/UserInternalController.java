package com.fourirbnb.user.presentation.controller;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.user.application.service.UserService;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.UpdatePasswordRequest;
import com.fourirbnb.user.presentation.dto.UserInternalResponse;
import com.fourirbnb.user.presentation.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/internal/users")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserInternalController {

  private final UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
    UserResponseDto response = userService.getUserById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserResponseDto> findByUser(@PathVariable String email) {
    UserResponseDto dto = userService.getUserByEmail(email);
    return ResponseEntity.ok(dto);
  }


  @PostMapping("/signUp")
  public ResponseEntity<?> userSignUp(
      @RequestBody CreateUserInternalRequest request) {
    try {
      userService.createUser(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (InvalidParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("/saveStaging")
  public ResponseEntity<?> saveStagingRequest(@RequestBody CreateUserInternalRequest feignRequest) {
    try {
      System.out.println("feign 넘어옴 시작");
      System.out.println(feignRequest.getRole() + "요청값");
      userService.createUser(feignRequest);
      System.out.println("끝남");
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (InvalidParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<UserInternalResponse> getEncryptedPassword(
      Long id) {
    UserInternalResponse response = userService.getEncryptedPassword(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/updatePassword")
  public ResponseEntity<Void> updatePassword(
      @RequestBody UpdatePasswordRequest request) {
    userService.updatePassword(request.getId(), request.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
