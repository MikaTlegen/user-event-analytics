package com.example.user_event_analytics.controller;

import com.example.user_event_analytics.entity.User;
import com.example.user_event_analytics.service.UserService;
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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/native")
    public ResponseEntity<List<User>> getUsersByNativeQuery(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userService.getComplexUsersNative(name, email));
    }

    @GetMapping("/criteria")
    public ResponseEntity<List<User>> getComplexUsersByCriteria(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userService.getComplexUsersCriteria(name, email));

    }


    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User update = userService.updateUser(user, id);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
