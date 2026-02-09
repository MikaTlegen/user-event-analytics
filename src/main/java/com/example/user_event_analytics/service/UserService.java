package com.example.user_event_analytics.service;

import com.example.user_event_analytics.entity.User;
import com.example.user_event_analytics.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(name -> !name.getName().equals("John Doe"))
                .toList();
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        User sevedUser = userRepository.save(user);
        return sevedUser;
    }

    @Transactional
    public User updateUser(User userDetails, Long id) {
        User user = getUser(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setUserRole(userDetails.getUserRole());
        return userRepository.save(user);

    }

    @Transactional
    public void deleteUser(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }
}
