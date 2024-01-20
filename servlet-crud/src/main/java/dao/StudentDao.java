package dao;

import java.util.Set;

import entity.Student;

public interface StudentDao {
	boolean addStudent(Student student);
	boolean removeStudent(int studentId);
	Set<Student> getStudents();
	boolean updateStudent(int studentId, Student student);
	int getStudentId(String email);
	Student getStudent(int studentId);
}
