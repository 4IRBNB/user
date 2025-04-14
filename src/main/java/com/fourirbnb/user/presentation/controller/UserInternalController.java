package com.fourirbnb.user.presentation.controller;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.user.application.service.UserInternalService;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
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

  private final UserInternalService userInternalService;

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
    UserResponseDto response = userInternalService.getUserById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserResponseDto> findByUser(@PathVariable String email) {
    UserResponseDto dto = userInternalService.findByUser(email);
    return ResponseEntity.ok(dto);
  }

  //request 다시 검증이 필요할까?
  @PostMapping("/signUp")
  public ResponseEntity<?> userSignUp(
      @RequestBody CreateUserInternalRequest request) {
    try {
      userInternalService.createUser(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (InvalidParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
