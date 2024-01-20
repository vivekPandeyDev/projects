package com.vivek.managment.repository;

import com.vivek.managment.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
