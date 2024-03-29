package com.vivek.service;

import java.util.List;

import com.vivek.entity.Article;

public interface IArticleService {
     List<Article> getAllArticles();
     Article getArticleById(long articleId);
     boolean addArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(Article article);
}
