package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.UserDTO;
import com.codesolutions.pmt.model.User;
import com.codesolutions.pmt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("test1@example.com");
        user1.setFirstName("Test1");
        user1.setLastName("User1");

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@example.com");
        user2.setFirstName("Test2");
        user2.setLastName("User2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("test1@example.com", result.get(0).getEmail());
        assertEquals("test2@example.com", result.get(1).getEmail());
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("Test");
        user.setLastName("User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void getUserById_WhenUserNotExists_ShouldThrowException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("new@example.com");
        userDTO.setFirstName("New");
        userDTO.setLastName("User");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail("new@example.com");
        savedUser.setFirstName("New");
        savedUser.setLastName("User");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("new@example.com", result.getEmail());
    }

    @Test
    void updateUser_WhenUserExists_ShouldReturnUpdatedUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("updated@example.com");
        userDTO.setFirstName("Updated");
        userDTO.setLastName("User");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setEmail("old@example.com");
        existingUser.setFirstName("Old");
        existingUser.setLastName("User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserDTO result = userService.updateUser(1L, userDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("updated@example.com", result.getEmail());
    }

    @Test
    void updateUser_WhenUserNotExists_ShouldThrowException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("updated@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, userDTO));
    }

    @Test
    void deleteUser_ShouldCallRepositoryDelete() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
