package com.vivek.integrated.repos;

import com.vivek.integrated.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmailIgnoreCase(String email);

}
