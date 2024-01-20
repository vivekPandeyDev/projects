package com.vivek.soap.service;

import java.util.List;

import com.vivek.soap.dto.ArticleInfo;
import com.vivek.soap.entity.Article;

public interface IArticleService {
     List<ArticleInfo> getAllArticles();
     ArticleInfo getArticleById(long articleId);
     void addArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(long id);
}
