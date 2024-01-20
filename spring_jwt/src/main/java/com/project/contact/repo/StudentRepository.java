package com.project.contact.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.contact.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	Optional<Student> findByName(String name);
}
