package com.example.user_event_analytics.repository;

import com.example.user_event_analytics.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "userProfile")
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userProfile")
    List<User> findAll();

    Optional<User> findUserById(Long id);

    boolean existsByEmail(String email);

    @Query("SELECT u.userRole, COUNT(u) FROM User u GROUP BY u.userRole")
    List<Object[]> countUsersByRole();
}
