package com.vivek.soap.endpoints;

import com.vivek.soap.dto.*;
import com.vivek.soap.entity.Article;
import com.vivek.soap.entity.RespStatus;
import com.vivek.soap.service.IArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
@Log4j2
public class ArticleEndpoint {
    private static final String NAMESPACE_URI = "http://www.vivek.com/article-ws";
    @Autowired
    private IArticleService articleService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByIdRequest")
    @ResponsePayload
    public GetArticleByIdResponse getArticle(@RequestPayload GetArticleByIdRequest request) {
        log.info("Getting article with id: {}", request.getArticleId());
        // getting article with given id from the db
        ArticleInfo articleInfo = articleService.getArticleById(request.getArticleId());
        GetArticleByIdResponse getArticleByIdResponse = new GetArticleByIdResponse();
        getArticleByIdResponse.setArticleInfo(articleInfo);
        return getArticleByIdResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllArticlesRequest")
    @ResponsePayload
    public GetAllArticlesResponse getAllArticles() {
        log.info("Getting all the articles");
        // fetching all the articles from the db
        List<ArticleInfo> articleInfoList = articleService.getAllArticles();
        GetAllArticlesResponse getAllArticlesResponse = new GetAllArticlesResponse();
        getAllArticlesResponse.getArticleInfo().addAll(articleInfoList);
        log.warn("Total article found: {}", getAllArticlesResponse.getArticleInfo().size());
        return getAllArticlesResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addArticleRequest")
    @ResponsePayload
    public AddArticleResponse addArticle(@RequestPayload AddArticleRequest request) {
        //creating request body
        AddArticleResponse response = new AddArticleResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setCategory(request.getCategory());
        //saving article to db
        articleService.addArticle(article);
        // creating response body
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(article, articleInfo);
        response.setArticleInfo(articleInfo);
        serviceStatus.setStatusCode(RespStatus.SUCCESS.toString());
        serviceStatus.setMessage("Article is added successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateArticleRequest")
    @ResponsePayload
    public UpdateArticleResponse updateArticle(@RequestPayload UpdateArticleRequest request) {
        Article article = new Article();
        BeanUtils.copyProperties(request.getArticleInfo(), article);
        // updating article to the db
        articleService.updateArticle(article);
        //creating response body
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode(RespStatus.SUCCESS.toString());
        serviceStatus.setMessage("Article updated successfully");
        UpdateArticleResponse response = new UpdateArticleResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteArticleRequest")
    @ResponsePayload
    public DeleteArticleResponse deleteArticle(@RequestPayload DeleteArticleRequest request) {
        // removing article from db
        articleService.deleteArticle(request.getArticleId());
        //creating response body
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode(RespStatus.SUCCESS.toString());
        serviceStatus.setMessage("Content Deleted Successfully");
        DeleteArticleResponse response = new DeleteArticleResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
