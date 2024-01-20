package com.vivek.soap.service;

import com.vivek.soap.dto.ArticleInfo;
import com.vivek.soap.dto.ServiceStatus;
import com.vivek.soap.entity.Article;
import com.vivek.soap.exception.CustomException;
import com.vivek.soap.repository.ArticleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Log4j2
public class ArticleService implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleInfo getArticleById(long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatusCode(HttpStatus.NOT_FOUND.toString());
            serviceStatus.setMessage("No article found with id: " + articleId);
            return new CustomException("CANNOT GET ARTICLE", serviceStatus);
        });
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(article, articleInfo);
        log.info("article is found with id -> {}", articleId);
        return articleInfo;
    }

    @Override
    public List<ArticleInfo> getAllArticles() {
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        List<Article> articleList = articleRepository.findAll();
        for (Article article : articleList) {
            ArticleInfo ob = new ArticleInfo();
            BeanUtils.copyProperties(article, ob);
            articleInfoList.add(ob);
        }
        return articleInfoList;
    }

    @Override
    public void addArticle(Article article) {
        List<Article> list = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory());
        if (!list.isEmpty()) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("Article already found");
            serviceStatus.setStatusCode(HttpStatus.FOUND.toString());
            throw new CustomException("Cannot add new article".toUpperCase(Locale.ROOT), serviceStatus);
        }
        //saving new article to the db
        article = articleRepository.save(article);
        log.info("article with is saved with id -> {}", article.getArticleId());
    }

    @Override
    public void updateArticle(Article article) {
        // no article found with given id
        if (articleRepository.findById(article.getArticleId()).isEmpty()) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("No article found with given id: " + article.getArticleId());
            serviceStatus.setStatusCode(HttpStatus.NOT_FOUND.toString());
            throw new CustomException("Cannot update the article".toUpperCase(Locale.ROOT), serviceStatus);

        }
        // updating the article
        articleRepository.save(article);
        log.info("article with is updated with id -> {}", article.getArticleId());
    }

    @Override
    public void deleteArticle(long id) {
        // no article found with given id
        final Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("No article with with given id: " + id);
            serviceStatus.setStatusCode(HttpStatus.NOT_FOUND.toString());
            throw new CustomException("Cannot delete the article".toUpperCase(Locale.ROOT), serviceStatus);
        }
        articleRepository.delete(article.get());
        log.info("article with is deleted with id -> {}", id);
    }
}
