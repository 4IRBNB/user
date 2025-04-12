package com.fourirbnb.user.presentation.controller;

import com.fourirbnb.user.application.service.UserInternalService;
import com.fourirbnb.user.presentation.dto.request.CreateUserRequest;
import com.fourirbnb.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/internal/users")
@RestController
@RequiredArgsConstructor
public class UserInternalController {

  private final UserInternalService userInternalService;

  @GetMapping("/{id}")
  public UserResponse getUserById(@PathVariable Long id) {
    return userInternalService.getUserById(id);
  }

  @PostMapping("/signUp")
  public boolean userSignUp(@RequestBody CreateUserRequest request) {
    return userInternalService.createUser(request);
  }
}
