package com.vanlife.manage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanlife.manage.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
