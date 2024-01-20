package com.microsoft.hackthon.dao;

import java.util.Optional;

import com.microsoft.hackthon.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserInfo,String> {
	Optional<UserInfo> findByUserName(String userName);
}
