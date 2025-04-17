package com.fourirbnb.user.application.service;

import com.fourirbnb.common.exception.InvalidParameterException;
import com.fourirbnb.common.exception.ResourceNotFoundException;
import com.fourirbnb.user.application.mapper.AdminMapper;
import com.fourirbnb.user.application.mapper.StagingMapper;
import com.fourirbnb.user.application.mapper.UserMapper;
import com.fourirbnb.user.domain.entity.Admin;
import com.fourirbnb.user.domain.entity.ApprovalStatus;
import com.fourirbnb.user.domain.entity.Role;
import com.fourirbnb.user.domain.entity.Staging;
import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.domain.repository.AdminRepository;
import com.fourirbnb.user.domain.repository.StagingRepository;
import com.fourirbnb.user.domain.repository.UserRepository;
import com.fourirbnb.user.presentation.dto.CreateUserInternalRequest;
import com.fourirbnb.user.presentation.dto.StagingResponseDto;
import com.fourirbnb.user.presentation.dto.UpdateUserRequest;
import com.fourirbnb.user.presentation.dto.UserInternalResponse;
import com.fourirbnb.user.presentation.dto.UserResponseDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


  private final UserRepository userRepository;
  private final StagingRepository stagingRepository;
  private final AdminRepository adminRepository;

  /*
      현재 로그아웃시 - 캐쉬 만료 처리
      로그아웃이 아닌 사용자가 브라우저를 강제종료를 한다면? - 로그아웃처리 X 캐쉬 유지
      비정상 상황가정시 캐싱 만료처리가 필요
      결론적으로 TTL 도 필요할것으로 예상
      생각 좀 해봐야겟다
   */
  @Cacheable(value = "userInfo", key = "#id")
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

  /*
   실무적으로 생각을 해보았을때 admin 과 user 의 도메인을 분리하고
   각기 다른 DTO/서비스로 나누는 것이 이상적이지만
   구현 복잡도와 프로젝트 리소스를 고려해
   현재는 동일한 필드를 기준으로 하나의 흐름으로 처리하였습니다.
   또한 프로젝트 범위에서는 회원가입 로직만 분리 구현하였고
   수정/삭제 등 나머지 기능은 공통 흐름으로 처리하거나 생략했습니다.

   추후 확장 시 AdminService 에 CRUD 를 분리 구현할 수 있습니다.
 */
  @Transactional
  public void createUser(CreateUserInternalRequest request) {
    System.out.println(request.getRole() + "서비스 권한");
    if (request.getRole() == Role.HOST || request.getRole() == Role.MANAGER) {
      //승인 대기소
      if (stagingRepository.existsByEmailAndStatusIn(
          request.getEmail(),
          List.of(ApprovalStatus.PENDING, ApprovalStatus.APPROVED)
      )) {
        throw new InvalidParameterException("이미 가입되었거나 승인 대기 중인 이메일입니다.");
      }
      //Staging 저장
      stagingRepository.save(
          UserMapper.toStagingEntity(UserMapper.withStatus(request, ApprovalStatus.PENDING)));
    } else if (request.getRole() == Role.CUSTOMER) {
      //CUSTOMER
      if (userRepository.existsByEmail(request.getEmail())) {
        throw new InvalidParameterException("이미 가입된 이메일입니다.");
      }
      userRepository.save(UserMapper.toEntity(request));
    } else if (request.getRole() == Role.MASTER) {
      //MASTER
      if (adminRepository.existsByEmail(request.getEmail())) {
        throw new InvalidParameterException("이미 가입된 이메일입니다.");
      }
      adminRepository.save(AdminMapper.toEntity(request));
    } else {
      throw new InvalidParameterException("지원하지 않는 역할입니다.");
    }
  }

  @CacheEvict(value = "userInfo", key = "#id")
  @Transactional
  public UserResponseDto updateUser(Long id, UpdateUserRequest request) {
    User user = findUserByIdOrThrow(id);
    user.update(request);
    return UserMapper.toResponse(user);
  }

  @CacheEvict(value = "userInfo", key = "#id")
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

  @Transactional(readOnly = true)
  public UserResponseDto getAdminByEmail(String email) {
    Admin checkUser = adminRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
    return AdminMapper.toResponse(checkUser);
  }


  private User findUserByIdOrThrow(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
  }

  @Transactional(readOnly = true)
  public List<StagingResponseDto> findPendingStagingUsers() {
    List<Staging> pendingList = stagingRepository.findAllByStatus(ApprovalStatus.PENDING);
    return pendingList.stream()
        .map(StagingMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Transactional
  public void approveStagingUser(UUID stagingId) {
    Staging staging = stagingRepository.findById(stagingId)
        .orElseThrow(() -> new ResourceNotFoundException("승인 대기 유저를 찾을 수 없습니다."));
    if (staging.getStatus() != ApprovalStatus.PENDING) {
      throw new InvalidParameterException("이미 승인된 사용자입니다.");
    }

    //이관 작업
    if (staging.getRole() == Role.HOST) {
      userRepository.save(UserMapper.toEntity(staging));
    } else if (staging.getRole() == Role.MANAGER) {
      adminRepository.save(AdminMapper.toEntity(staging));
    } else {
      throw new InvalidParameterException("승인할 수 없는 역할입니다.");
    }
    //승인 업데이트
    staging.updateStatus(ApprovalStatus.APPROVED);
  }

}
