package com.brd.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	private String username;
	private String email;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> authorities = new ArrayList<>();
	
}
