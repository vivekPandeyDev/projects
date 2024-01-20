package com.project.contact.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.contact.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo,String> {
	Optional<UserInfo> findByUserName(String userName);
}
