package com.project.contact.service;

import java.util.List;

import com.project.contact.entity.Student;

public interface StudentService {
	Student save(Student student);
	Student delete(String name);
	Student update(Student student); 
	Student get(String name);
	List<Student> getAll();
}
