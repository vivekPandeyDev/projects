package com.brd.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecordDetailDto {
	private LocalDate createDate;
	private String createdBy;
	private LocalDate modifiedDate;
	private String modifiedBy;
	private LocalDate authorizedDate;
	private String authorizedBy;
}
