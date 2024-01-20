package com.project.contact.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.contact.dto.ErrorResponse;
import com.project.contact.entity.Student;
import com.project.contact.exception.CustomUserException;
import com.project.contact.repo.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private static final String STUDENT_NOT_FOUND = "no student found with given  name : {0}";
	@Override
	public Student save(Student student) {
		Optional<Student> dbStudent = studentRepository.findByName(student.getName());
		if (dbStudent.isPresent()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.FOUND).message(
					MessageFormat.format("student is already present with given user name: {0}", student.getName()))
					.build();
			throw new CustomUserException(errorResponse);
		}
		return studentRepository.save(student);

	}

	@Override
	public Student delete(String name) {
		Optional<Student> studentInfoOptional = studentRepository.findByName(name);
		if (studentInfoOptional.isEmpty()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.NOT_FOUND)
					.message(MessageFormat.format(STUDENT_NOT_FOUND, name)).build();
			throw new CustomUserException(errorResponse);
		}
		studentRepository.delete(studentInfoOptional.get());
		return studentInfoOptional.get();
	}

	@Override
	public Student update(Student student) {
		Optional<Student> dbStudent = studentRepository.findById(student.getId());
		if (dbStudent.isEmpty()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.FOUND).message(
					MessageFormat.format(STUDENT_NOT_FOUND, student.getName()))
					.build();
			throw new CustomUserException(errorResponse);
		}
		return studentRepository.save(student);
	}

	@Override
	public Student get(String name) {
		Optional<Student> studentInfoOptional = studentRepository.findByName(name);
		if (studentInfoOptional.isEmpty()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.NOT_FOUND)
					.message(MessageFormat.format(STUDENT_NOT_FOUND, name)).build();
			throw new CustomUserException(errorResponse);
		}
		return studentInfoOptional.get();
	}

	@Override
	public List<Student> getAll() {
		return studentRepository.findAll();
	}
	
	

}
