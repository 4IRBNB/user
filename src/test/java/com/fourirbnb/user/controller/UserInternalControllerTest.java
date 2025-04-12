package com.fourirbnb.user.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fourirbnb.user.application.service.UserInternalService;
import com.fourirbnb.user.presentation.controller.UserInternalController;
import com.fourirbnb.user.presentation.dto.response.UserResponse;
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
  private UserInternalService userInternalService;

  @Test
  void 유저조회_성공() throws Exception {
    Long id = 1L;
    UserResponse response = UserResponse.builder()
        .email("test@example.com")
        .username("tester")
        .build();

    when(userInternalService.getUserById(id)).thenReturn(response);

    mockMvc.perform(get("/internal/users/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("test@example.com"))
        .andExpect(jsonPath("$.username").value("tester"));
  }


}