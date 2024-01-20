package com.microsoft.hackthon.dao;

import java.util.List;
import java.util.Optional;

import com.microsoft.hackthon.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface TokenRepository extends JpaRepository<Token, Integer> {
	Optional<Token> findByTokenStr(String tokenStr);
	@Query("select t from Token t inner join UserInfo u on t.user=u.userId where u.userName= :userName")
	List<Token> findTokenByUserName(String userName);
}
