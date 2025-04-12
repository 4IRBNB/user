package com.fourirbnb.user.application.service;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.user.application.mapper.UserMapper;
import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.domain.repository.UserRepository;
import com.fourirbnb.user.presentation.dto.request.CreateUserRequest;
import com.fourirbnb.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInternalService {

  private final UserRepository repository;
  private final UserMapper userMapper;

  public UserResponse getUserById(Long id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new InvalidParameterException("회원을 찾을수 없습니다."));
    return userMapper.toResponse(user);
  }


  public boolean createUser(CreateUserRequest request) {
    try {
      User user = userMapper.toEntity(request);
      repository.save(user);
      return true;
    } catch (DataIntegrityViolationException e) {
      return false;
    }
  }
}
