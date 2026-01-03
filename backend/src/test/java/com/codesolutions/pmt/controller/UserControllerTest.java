package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.UserDTO;
import com.codesolutions.pmt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers_ShouldReturnUserList() throws Exception {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setEmail("test1@example.com");
        user1.setFirstName("Test1");
        user1.setLastName("User1");

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setEmail("test2@example.com");
        user2.setFirstName("Test2");
        user2.setLastName("User2");

        List<UserDTO> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("test1@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].email").value("test2@example.com"));
    }

    @Test
    void getUserById_ShouldReturnUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("Test");
        user.setLastName("User");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("new@example.com");
        userDTO.setFirstName("New");
        userDTO.setLastName("User");

        UserDTO createdUser = new UserDTO();
        createdUser.setId(1L);
        createdUser.setEmail("new@example.com");
        createdUser.setFirstName("New");
        createdUser.setLastName("User");

        when(userService.createUser(any(UserDTO.class))).thenReturn(createdUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("new@example.com"));
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("updated@example.com");
        userDTO.setFirstName("Updated");
        userDTO.setLastName("User");

        UserDTO updatedUser = new UserDTO();
        updatedUser.setId(1L);
        updatedUser.setEmail("updated@example.com");
        updatedUser.setFirstName("Updated");
        updatedUser.setLastName("User");

        when(userService.updateUser(any(Long.class), any(UserDTO.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void deleteUser_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}
