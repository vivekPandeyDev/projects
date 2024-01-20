package com.brd.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	@Column(length = 100,name="customer_address_1",nullable = false)
	private String address1;
	@Column(length = 100,name="customer_address_2")
	private String address2;
	@Column(length = 6,name="customer_pincode",nullable = false)
	private Integer pincode;
}
