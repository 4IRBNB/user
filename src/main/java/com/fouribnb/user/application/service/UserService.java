package com.fouribnb.user.application.service;

import com.fouribnb.user.application.mapper.UserMapper;
import com.fouribnb.user.domain.entity.User;
import com.fouribnb.user.domain.repository.UserRepository;
import com.fouribnb.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final UserMapper mapper;

  public UserResponse getUserById(Long id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("회원을 찾을수 없습니다."));
    return mapper.toResponse(user);
  }

  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }
}
