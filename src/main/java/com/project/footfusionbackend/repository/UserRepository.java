package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);
    Optional<User> findByEmailId(String email);

    Optional<User> findByContactNo(String contactNo);
}
