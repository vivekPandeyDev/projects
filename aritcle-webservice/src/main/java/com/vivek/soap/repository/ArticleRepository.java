package com.vivek.soap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.soap.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleAndCategory(String title, String category);
}
