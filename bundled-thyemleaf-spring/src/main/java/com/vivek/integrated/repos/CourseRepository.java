package com.vivek.integrated.repos;

import com.vivek.integrated.domain.Course;
import com.vivek.integrated.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findFirstByStudent(Student student);

}
