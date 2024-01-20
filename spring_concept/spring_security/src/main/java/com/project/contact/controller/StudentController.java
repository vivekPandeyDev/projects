package com.project.contact.controller;

import com.project.contact.dto.JsonResponse;
import com.project.contact.dto.StudentDto;
import com.project.contact.entity.Student;
import com.project.contact.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;
	private final ModelMapper mapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('WRITE')")
	public ResponseEntity<JsonResponse<Student>> saveStudent(@Valid @RequestBody StudentDto dto) {
		Student student =  mapper.map(dto, Student.class);
		student =  studentService.save(student);
		return ResponseEntity.ok(new JsonResponse<>(student, HttpStatus.FOUND, "successfully save the student"));
	}
	
	@GetMapping("{name}")
	public ResponseEntity<JsonResponse<StudentDto>> getStudentByName(@PathVariable String name  ){
		Student student =  studentService.get(name);
		StudentDto studentDto =  mapper.map(student, StudentDto.class);
		return ResponseEntity.ok(new JsonResponse<>(studentDto, HttpStatus.FOUND, "successfully found the student"));
	}
	
	@GetMapping
	public ResponseEntity<JsonResponse<List<StudentDto>>> getStudents(){
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
	public ResponseEntity<JsonResponse<Student>> updateStudent(@PathVariable Integer id,@Valid @RequestBody StudentDto dto) {
		Student student =  mapper.map(dto, Student.class);
		student.setId(id);
		student =  studentService.update(student);
		return ResponseEntity.ok(new JsonResponse<>(student, HttpStatus.FOUND, "successfully updated the student"));
	}
	
	
}
