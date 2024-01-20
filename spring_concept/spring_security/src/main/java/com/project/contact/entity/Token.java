package com.project.contact.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tokenId;
	private String tokenStr;
	private boolean expired;
	private boolean revoked;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo user;
}
