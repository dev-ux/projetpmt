package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.UserDTO;
import com.codesolutions.pmt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve all users in the system")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.debug("GET /users - Getting all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/active")
    @Operation(summary = "Get active users", description = "Retrieve all active users")
    public ResponseEntity<List<UserDTO>> getActiveUsers() {
        log.debug("GET /users/active - Getting active users");
        List<UserDTO> users = userService.getAllActiveUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their ID")
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /users/{} - Getting user by id", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Retrieve a specific user by their email")
    public ResponseEntity<UserDTO> getUserByEmail(
            @Parameter(description = "User email", required = true)
            @PathVariable String email) {
        log.debug("GET /users/email/{} - Getting user by email", email);
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Create a new user")
    public ResponseEntity<UserDTO> createUser(
            @Parameter(description = "User data", required = true)
            @Valid @RequestBody UserDTO userDTO) {
        log.debug("POST /users - Creating user: {}", userDTO.getEmail());
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update an existing user")
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated user data", required = true)
            @Valid @RequestBody UserDTO userDTO) {
        log.debug("PUT /users/{} - Updating user", id);
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete a user (soft delete)")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id) {
        log.debug("DELETE /users/{} - Deleting user", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate user", description = "Deactivate a user account")
    public ResponseEntity<Void> deactivateUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id) {
        log.debug("PATCH /users/{}/deactivate - Deactivating user", id);
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/activate")
    @Operation(summary = "Activate user", description = "Activate a user account")
    public ResponseEntity<Void> activateUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id) {
        log.debug("PATCH /users/{}/activate - Activating user", id);
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search users", description = "Search users by name or email")
    public ResponseEntity<List<UserDTO>> searchUsers(
            @Parameter(description = "Search query", required = true)
            @RequestParam String query) {
        log.debug("GET /users/search?query={} - Searching users", query);
        List<UserDTO> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/check-email")
    @Operation(summary = "Check email availability", description = "Check if an email is available for registration")
    public ResponseEntity<Boolean> checkEmailAvailability(
            @Parameter(description = "Email to check", required = true)
            @RequestParam String email) {
        log.debug("GET /users/check-email?email={} - Checking email availability", email);
        boolean available = !userService.existsByEmail(email);
        return ResponseEntity.ok(available);
    }
}
