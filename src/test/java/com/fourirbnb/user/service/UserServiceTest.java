package com.fourirbnb.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fourirbnb.user.application.mapper.UserMapper;
import com.fourirbnb.user.application.service.UserInternalService;
import com.fourirbnb.user.domain.entity.User;
import com.fourirbnb.user.domain.repository.UserRepository;
import com.fourirbnb.user.presentation.dto.response.UserResponse;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository repository;

  @Mock
  private UserMapper mapper;

  @InjectMocks
  private UserInternalService userInternalService;

  @Test
  void getUserById() {
    // given
    Long id = 1L;
    User user = new User("test@example.com", "test", "test", "UX516D12", "01044445555");
    UserResponse response = UserResponse.builder()
        .email("test@example.com")
        .username("test")
        .build();

    when(repository.findById(id)).thenReturn(Optional.of(user));
    when(mapper.toResponse(user)).thenReturn(response);

    // when
    UserResponse result = userInternalService.getUserById(id);

    // then
    assertThat(result.getEmail()).isEqualTo("test@example.com");
    assertThat(result.getUsername()).isEqualTo("test");
  }
}
