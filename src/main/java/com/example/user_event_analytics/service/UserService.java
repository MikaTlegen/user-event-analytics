package com.example.user_event_analytics.service;

import com.example.user_event_analytics.dto.request_dto.UserRequestDTO;
import com.example.user_event_analytics.dto.response_dto.UserResponseDTO;
import com.example.user_event_analytics.entity.User;
import com.example.user_event_analytics.mapper.UserMapper;
import com.example.user_event_analytics.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;
    private final UserMapper userMapper;

    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toResponseDto(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getAllUsersExceptAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUserRole() == null ||
                        !"ADMIN_ROLE".equalsIgnoreCase(user.getUserRole().name()))
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        User userEntity = userMapper.toEntity(user);
        User sevedUser = userRepository.save(userEntity);
        return userMapper.toResponseDto(sevedUser);
    }

    @Transactional
    public UserResponseDTO updateUser(UserRequestDTO userDetails, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setUserRole(userDetails.getUserRole());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDto(updatedUser);

    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }

    @Transactional
    public List<UserResponseDTO> getComplexUsersNative(String namePattern, String emailPattern) {
        StringBuilder sql = new StringBuilder("  SELECT * FROM users WHERE 1=1");
        if (namePattern != null) {
            sql.append(" AND name LIKE :namePattern ");
        }
        if (emailPattern != null) {
            sql.append(" AND email LIKE :emailPattern ");
        }

        sql.append(" ORDER BY LENGTH(name) DESC");

        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
        if (namePattern != null && !namePattern.trim().isEmpty()) {
            query.setParameter("namePattern", "%" + namePattern.trim() + "%");
        }
        if (emailPattern != null && !emailPattern.trim().isEmpty()) {
            query.setParameter("emailPattern", "%" + emailPattern.trim() + "%");
        }

        List<User> users = query.getResultList();
        return users.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UserResponseDTO> getComplexUsersCriteria(String name, String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(user.get("name"), "%" + name + "%"));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(cb.like(user.get("email"), "%" + email + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(user.get("name")));

        List<User> users = entityManager.createQuery(cq).getResultList();
        return users.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
