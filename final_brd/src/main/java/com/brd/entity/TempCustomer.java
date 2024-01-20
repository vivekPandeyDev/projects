package com.brd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brd.enums.CustomerFlag;
import com.brd.enums.RecordStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "brd_temp_customer")
public class TempCustomer {
	private UUID customerId = UUID.randomUUID();
	@Id
	@Column(name = "customer_code",length = 10,nullable = false)
	private String customerCode;
	@Column(name = "customer_name",length = 30,nullable = false)
	private String customerName;
	@Embedded
	private Address address;
	@Column(name = "email_address",length = 100)
	private String customerEmail;
	@Column(name = "contact",nullable = true,length = 20)
	private String contactNumber;
	@Column(name = "primary_contact_person",length = 100,nullable = false)
	private String primaryContactNumber;
	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	private CustomerFlag flag;
	@Enumerated(EnumType.STRING)
	private RecordStatus recordStatus;
	@Embedded
	private RecordDetail recordDetail;

}
