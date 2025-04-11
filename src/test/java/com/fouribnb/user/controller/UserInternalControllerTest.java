package com.fouribnb.user.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fouribnb.user.application.service.UserService;
import com.fouribnb.user.presentation.controller.UserInternalController;
import com.fouribnb.user.presentation.dto.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserInternalController.class)
class UserInternalControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void 유저조회_성공() throws Exception {
    Long id = 1L;
    UserResponse response = UserResponse.builder()
        .email("test@example.com")
        .username("tester")
        .build();

    when(userService.getUserById(id)).thenReturn(response);

    mockMvc.perform(get("/internal/users/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("test@example.com"))
        .andExpect(jsonPath("$.username").value("tester"));
  }

  @Test
  void 이메일존재_조회성공() throws Exception {
    String email = "test@example.com";

    when(userService.existsByEmail(email)).thenReturn(true);

    mockMvc.perform(get("/internal/users/check-exists")
            .param("email", email))
        .andExpect(status().isOk())
        .andExpect(content().string("true"));
  }
}