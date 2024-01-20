package com.webservices.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vivek_jpa_student")
public class Student {
	@Id
	private int studentId;
	private int studentName;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getStudentName() {
		return studentName;
	}
	public void setStudentName(int studentName) {
		this.studentName = studentName;
	}
	
}
