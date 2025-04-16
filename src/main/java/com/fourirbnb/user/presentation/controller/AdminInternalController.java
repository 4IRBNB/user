package com.fourirbnb.user.presentation.controller;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.user.application.service.UserService;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.StagingResponseDto;
import com.fourirbnb.user.presentation.dto.UserResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/internal/users/admin")
@RestController
@RequiredArgsConstructor
public class AdminInternalController {

  private final UserService userService;

  @PostMapping("/signUp")
  public ResponseEntity<?> adminSignUp(@RequestBody CreateUserInternalRequest request) {
    try {
      System.out.println("어드민 들어옴");
      System.out.println(request.getRole() + "권한");
      userService.createUser(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (InvalidParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserResponseDto> findByAdmin(@PathVariable String email) {
    UserResponseDto dto = userService.getAdminByEmail(email);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/approve")
  public ResponseEntity<List<StagingResponseDto>> findByAdminApprove() {
    List<StagingResponseDto> pendingUsers = userService.findPendingStagingUsers();
    return ResponseEntity.ok(pendingUsers);
  }

  @PatchMapping("/approve/{stagingId}")
  public ResponseEntity<?> approve(@PathVariable UUID stagingId) {
    userService.approveStagingUser(stagingId);
    return ResponseEntity.ok().build();
  }
}
