package com.vivek.managment.repository;

import com.vivek.managment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
