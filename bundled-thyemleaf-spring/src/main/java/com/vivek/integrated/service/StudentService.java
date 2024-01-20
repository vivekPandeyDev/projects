package com.vivek.integrated.service;

import com.vivek.integrated.domain.Course;
import com.vivek.integrated.domain.Student;
import com.vivek.integrated.model.StudentDTO;
import com.vivek.integrated.repos.CourseRepository;
import com.vivek.integrated.repos.StudentRepository;
import com.vivek.integrated.util.NotFoundException;
import com.vivek.integrated.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(final StudentRepository studentRepository,
            final CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentDTO> findAll() {
        final List<Student> students = studentRepository.findAll(Sort.by("id"));
        return students.stream()
                .map(student -> mapToDTO(student, new StudentDTO()))
                .toList();
    }

    public StudentDTO get(final Long id) {
        return studentRepository.findById(id)
                .map(student -> mapToDTO(student, new StudentDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final StudentDTO studentDTO) {
        final Student student = new Student();
        mapToEntity(studentDTO, student);
        return studentRepository.save(student).getId();
    }

    public void update(final Long id, final StudentDTO studentDTO) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(studentDTO, student);
        studentRepository.save(student);
    }

    public void delete(final Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(final Student student, final StudentDTO studentDTO) {
        studentDTO.setId(student.getId());
        studentDTO.setCreatedBy(student.getCreatedBy());
        studentDTO.setUpdatedBy(student.getUpdatedBy());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
        student.setCreatedBy(studentDTO.getCreatedBy());
        student.setUpdatedBy(studentDTO.getUpdatedBy());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public boolean emailExists(final String email) {
        return studentRepository.existsByEmailIgnoreCase(email);
    }

    public String getReferencedWarning(final Long id) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Course studentCourse = courseRepository.findFirstByStudent(student);
        if (studentCourse != null) {
            return WebUtils.getMessage("student.course.student.referenced", studentCourse.getId());
        }
        return null;
    }

}
