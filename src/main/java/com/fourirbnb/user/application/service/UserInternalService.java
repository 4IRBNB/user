package com.fourirbnb.user.application.service;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.common.exception.ResourceNotFoundException;
import com.fourirbnb.user.application.mapper.UserMapper;
import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.domain.repository.UserRepository;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInternalService {

  private final UserRepository repository;
  private final UserRepository userRepository;

  public UserResponseDto getUserById(Long id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을수 없습니다."));
    return UserMapper.toResponse(user);
  }

  public void createUser(CreateUserInternalRequest request) {
    //유저 이메일 검증
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new InvalidParameterException("이미 가입된 이메일입니다.");
    }
    userRepository.save(UserMapper.toEntity(request));
  }

  public UserResponseDto findByUser(String email) {
    User checkUser = userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
    return UserMapper.toResponse(checkUser);
  }
}
