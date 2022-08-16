package com.example.bibliosystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(" select u from User u " +
            " where u.email = ?1")
    Optional<User> findUserWithName(String username);
}
