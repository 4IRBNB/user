package com.fourirbnb.user.application.service;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.common.exception.ResourceNotFoundException;
import com.fourirbnb.user.application.mapper.UserMapper;
import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.domain.repository.UserRepository;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.UpdateUserRequest;
import com.fourirbnb.user.presentation.dto.UserInternalResponse;
import com.fourirbnb.user.presentation.dto.UserResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public UserResponseDto getUserById(Long id) {
    User user = findUserByIdOrThrow(id);
    return UserMapper.toResponse(user);
  }

  @Transactional(readOnly = true)
  public UserResponseDto getUserByEmail(String email) {
    User checkUser = userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
    return UserMapper.toResponse(checkUser);
  }

  @Transactional(readOnly = true)
  public List<UserResponseDto> getAllUsers() {
    List<User> user = userRepository.findAll();
    return user.stream()
        .map(UserMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Transactional
  public void createUser(CreateUserInternalRequest request) {
    //유저 이메일 검증
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new InvalidParameterException("이미 가입된 이메일입니다.");
    }
    userRepository.save(UserMapper.toEntity(request));
  }

  @Transactional
  public UserResponseDto updateUser(Long id, UpdateUserRequest request) {
    User user = findUserByIdOrThrow(id);
    user.update(request);
    return UserMapper.toResponse(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    User user = findUserByIdOrThrow(id);
    user.delete(id);
  }

  @Transactional(readOnly = true)
  public UserInternalResponse getEncryptedPassword(Long id) {
    User user = findUserByIdOrThrow(id);
    return UserMapper.toInternalResponse(user);

  }

  @Transactional
  public void updatePassword(Long id, String encodedPassword) {
    User user = findUserByIdOrThrow(id);
    user.updatePassword(encodedPassword);
  }

  private User findUserByIdOrThrow(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
  }
}
