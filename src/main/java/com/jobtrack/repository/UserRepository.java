package com.jobtrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtrack.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
