package com.brd.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RecordDetail {
	@Column(nullable = false)
	private LocalDate createDate;
	@Column(nullable = false,length = 30)
	private String createdBy;
	private LocalDate modifiedDate;
	@Column(length = 30)
	private String modifiedBy;
	private LocalDate authorizedDate;
	@Column(length = 30)
	private String authorizedBy;
}
