package com.project.contact.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.contact.dto.ErrorResponse;
import com.project.contact.dto.JsonResponse;
import com.project.contact.dto.StudentDto;
import com.project.contact.entity.Student;
import com.project.contact.exception.CustomUserException;
import com.project.contact.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;
	private final ModelMapper mapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('WRITE')")
	public ResponseEntity<JsonResponse<Student>> saveStudent(@Valid @RequestBody StudentDto dto,BindingResult errors) {
		if(errors.hasErrors()) {
			List<String> validationErrors = errors.getAllErrors().stream().map((error) -> error.getDefaultMessage()).toList();
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST)
					.message("not a valid student").errors(validationErrors).build();
			throw new CustomUserException(errorResponse);
			
		}
		Student student =  mapper.map(dto, Student.class);
		student =  studentService.save(student);
		return ResponseEntity.ok(new JsonResponse<>(student, HttpStatus.FOUND, "successfully save the student"));
	}
	
	@GetMapping("{name}")
	public ResponseEntity<JsonResponse<StudentDto>> getStudnetByName(@PathVariable String name  ){
		Student student =  studentService.get(name);
		StudentDto studentDto =  mapper.map(student, StudentDto.class);
		return ResponseEntity.ok(new JsonResponse<>(studentDto, HttpStatus.FOUND, "successfully found the student"));
	}
	
	@GetMapping
	public ResponseEntity<JsonResponse<List<StudentDto>>> getStudnets(){
		List<Student> studentList =  studentService.getAll();
		List<StudentDto> studentDto =  studentList.stream().map( student -> mapper.map(student, StudentDto.class)).toList();
		return ResponseEntity.ok(new JsonResponse<>(studentDto, HttpStatus.FOUND, "successfully found the student"));
	}
	
	@DeleteMapping("{name}")
	@PreAuthorize("hasAuthority('WRITE')")
	public ResponseEntity<JsonResponse<StudentDto>> deleteUserByName(@PathVariable String name  ){
		Student student =  studentService.delete(name);
		StudentDto studentDto =  mapper.map(student, StudentDto.class);
		return ResponseEntity.ok(new JsonResponse<>(studentDto, HttpStatus.ACCEPTED, "successfully delete the student"));
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('WRITE')")
	public ResponseEntity<JsonResponse<Student>> updateStudent(@PathVariable Integer id,@Valid @RequestBody StudentDto dto,BindingResult errors) {
		if(errors.hasErrors()) {
			List<String> validationErrors = errors.getAllErrors().stream().map((error) -> error.getDefaultMessage()).toList();
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST)
					.message("not a valid student").errors(validationErrors).build();
			throw new CustomUserException(errorResponse);
			
		}
		Student student =  mapper.map(dto, Student.class);
		student.setId(id);
		student =  studentService.update(student);
		return ResponseEntity.ok(new JsonResponse<>(student, HttpStatus.FOUND, "successfully updated the student"));
	}
	
	
}
