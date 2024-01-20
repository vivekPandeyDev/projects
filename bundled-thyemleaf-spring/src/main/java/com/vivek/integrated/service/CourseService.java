package com.vivek.integrated.service;

import com.vivek.integrated.domain.Course;
import com.vivek.integrated.domain.Student;
import com.vivek.integrated.model.CourseDTO;
import com.vivek.integrated.repos.CourseRepository;
import com.vivek.integrated.repos.StudentRepository;
import com.vivek.integrated.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(final CourseRepository courseRepository,
            final StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<CourseDTO> findAll() {
        final List<Course> courses = courseRepository.findAll(Sort.by("id"));
        return courses.stream()
                .map(course -> mapToDTO(course, new CourseDTO()))
                .toList();
    }

    public CourseDTO get(final Long id) {
        return courseRepository.findById(id)
                .map(course -> mapToDTO(course, new CourseDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CourseDTO courseDTO) {
        final Course course = new Course();
        mapToEntity(courseDTO, course);
        return courseRepository.save(course).getId();
    }

    public void update(final Long id, final CourseDTO courseDTO) {
        final Course course = courseRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(courseDTO, course);
        courseRepository.save(course);
    }

    public void delete(final Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO mapToDTO(final Course course, final CourseDTO courseDTO) {
        courseDTO.setId(course.getId());
        courseDTO.setCreatedBy(course.getCreatedBy());
        courseDTO.setUpdatedBy(course.getUpdatedBy());
        courseDTO.setName(course.getName());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setStudent(course.getStudent() == null ? null : course.getStudent().getId());
        return courseDTO;
    }

    private Course mapToEntity(final CourseDTO courseDTO, final Course course) {
        course.setCreatedBy(courseDTO.getCreatedBy());
        course.setUpdatedBy(courseDTO.getUpdatedBy());
        course.setName(courseDTO.getName());
        course.setPrice(courseDTO.getPrice());
        final Student student = courseDTO.getStudent() == null ? null : studentRepository.findById(courseDTO.getStudent())
                .orElseThrow(() -> new NotFoundException("student not found"));
        course.setStudent(student);
        return course;
    }

}
