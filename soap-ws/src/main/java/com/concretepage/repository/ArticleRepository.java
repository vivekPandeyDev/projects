package com.vivek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Article findByArticleId(long articleId);
    List<Article> findByTitleAndCategory(String title, String category);
}
