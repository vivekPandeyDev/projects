package com.webservices.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vivek_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	private int addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private int zip;
}
