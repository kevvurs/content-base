package com.boomerang.contentbase.service;

import com.boomerang.contentbase.binding.ContentResponse;
import com.boomerang.contentbase.data.ArticleEntity;
import com.boomerang.contentbase.data.ArticleDao;
import com.boomerang.contentbase.util.BindingTransform;
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
    private final BindingTransform bindingTransform;

    @Autowired
    public ArticleService(ArticleDao articleDao, BindingTransform bindingTransform) {
        this.articleDao = articleDao;
        this.bindingTransform = bindingTransform;
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleEntity getArticle(@PathVariable("id") String articleId) {
        LOG.info(String.format("GET => ~/articles/%s", articleId));
        return this.articleDao.getArticle(articleId);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ContentResponse getPage(@RequestParam(value = "page", required = false, defaultValue = "") String cursorString) {
        if (cursorString.isEmpty()) {
            LOG.info("GET => ~/articles");
        } else {
            LOG.info(String.format("GET => ~/articles?page=%s", cursorString));
        }
        return bindingTransform.createResponse(cursorString,
                articleDao.getPage(cursorString));
    }
}
