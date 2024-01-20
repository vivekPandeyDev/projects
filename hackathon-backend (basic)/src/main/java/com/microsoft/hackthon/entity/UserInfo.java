package com.microsoft.hackthon.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String role;
	private LocalDate dob;
	@OneToMany(mappedBy = "user")
	List<Token> token;
}
