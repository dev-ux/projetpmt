package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.UserDTO;
import com.codesolutions.pmt.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    List<UserDTO> getAllActiveUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    void deactivateUser(Long id);

    void activateUser(Long id);

    List<UserDTO> searchUsers(String search);

    boolean existsByEmail(String email);
}
