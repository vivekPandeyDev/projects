package com.brd.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MyFile {
	@Id
	private String fileName;
	private LocalDate uploadDate;
	private String uploadBy;
	private String rejectionLevel;
	private String isFileRead;
}
