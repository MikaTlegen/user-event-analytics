package com.example.user_event_analytics.controller;

import com.example.user_event_analytics.dto.request_dto.UserRequestDTO;
import com.example.user_event_analytics.dto.response_dto.UserResponseDTO;
import com.example.user_event_analytics.entity.User;
import com.example.user_event_analytics.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/native")
    public ResponseEntity<List<UserResponseDTO>> getUsersByNativeQuery(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userService.getComplexUsersNative(name, email));
    }

    @GetMapping("/criteria")
    public ResponseEntity<List<UserResponseDTO>> getComplexUsersByCriteria(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userService.getComplexUsersCriteria(name, email));

    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {

        UserResponseDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO user) {
        UserResponseDTO update = userService.updateUser(user, id);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/non-admins")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersExceptAdmins() {
        return ResponseEntity.ok(userService.getAllUsersExceptAdmins());
    }

}
