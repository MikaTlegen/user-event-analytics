package com.example.user_event_analytics.service;

import com.example.user_event_analytics.entity.User;
import com.example.user_event_analytics.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public List<User> getComplexUsersNative(String namePattern, String emailPattern) {
        StringBuilder sql = new StringBuilder("  SELECT * FROM users WHERE 1=1");
        if (namePattern != null) {
            sql.append(" AND user_name LIKE :namePattern ");
        }
        if (emailPattern != null) {
            sql.append(" AND email LIKE :emailPattern ");
        }

        sql.append("ORDER BY LENGTH(user_name) DESC");

        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
        if (namePattern != null && !namePattern.trim().isEmpty()) {
            query.setParameter("namePattern", "%" + namePattern.trim() + "%");
        }
        if (emailPattern != null && !emailPattern.trim().isEmpty()) {
            query.setParameter("emailPattern", "%" + emailPattern.trim() + "%");
        }

        return query.getResultList();
    }

    public List<User> getComplexUsersCriteria(String name, String email) {
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

        return entityManager.createQuery(cq).getResultList();
    }

}
