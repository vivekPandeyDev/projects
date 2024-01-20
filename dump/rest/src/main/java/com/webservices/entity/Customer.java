package com.webservices.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webservices.formatter.LocalDateDeserializer;
import com.webservices.formatter.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@NamedQueries({
@NamedQuery(query = "Select customer.customerName, customer.monthlyIncome, customer.profession from Customer customer", name = "findDetails")
})

@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from vivek_jpa_customer where companyName=?", name = "findByCompany"),
@NamedNativeQuery(query = "select * from vivek_jpa_customer where dateOfBirth - CURDATE() < 21", name = "findByAge")
})

@Entity
@Table(name = "vivek_jpa_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateOfBirth;
	private double monthlyIncome;
	private String profession;
	private String designation;
	private String companyName;
	@OneToOne(cascade = CascadeType.ALL )
	private Address address;
	@OneToMany(cascade = CascadeType.ALL,mappedBy  ="customer")
	@JsonManagedReference
	List<LoanAgreement> loanAgreements;
}
