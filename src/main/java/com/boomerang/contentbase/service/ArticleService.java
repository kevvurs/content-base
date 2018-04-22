package com.boomerang.contentbase.service;

import com.boomerang.contentbase.data.Article;
import com.boomerang.contentbase.data.ArticleDao;
import com.boomerang.contentbase.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleService {
    private final Logger LOG = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Article getArticle(@PathVariable("id") String articleId) {
        LOG.info(String.format("GET => ~/articles/%s", articleId));
        return this.articleDao.getArticle(articleId);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page getPage(@RequestParam(value = "page", required = false, defaultValue = "") String cursorString) {
        LOG.info(String.format("GET => ~/articles?page=%s", cursorString));
        return this.articleDao.getPage(cursorString);
    }
}
