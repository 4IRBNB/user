package com.fouribnb.user.presentation.controller;

import com.fouribnb.user.application.service.UserService;
import com.fouribnb.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/internal/users")
@RestController
@RequiredArgsConstructor
public class UserInternalController {

  private final UserService userService;

  @GetMapping("/{id}")
  public UserResponse getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/check-exists")
  public boolean checkEmailExists(@RequestParam String email) {
    return userService.existsByEmail(email);
  }
}
