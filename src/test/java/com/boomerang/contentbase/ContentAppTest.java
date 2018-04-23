package com.boomerang.contentbase;

import com.boomerang.contentbase.binding.ContentResponse;
import com.boomerang.contentbase.data.ArticleDao;
import com.boomerang.contentbase.data.ArticleEntity;
import com.boomerang.contentbase.data.ArticlePage;
import com.boomerang.contentbase.service.ArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentAppTest {
    private static final Logger LOG = LoggerFactory.getLogger(ContentAppTest.class);
    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    @Autowired ArticleDao articleDao;
    @Autowired ArticleService articleService;

    @Test
    public void testGetPage() {
        ArticlePage page = articleDao.getPage("");
        Assert.assertNotEquals(new ArrayList<ArticleEntity>(), page.getArticles());
    }

    @Test
    public void testFrontpageEndpoint() {
        ContentResponse response = articleService.getPage("");
        Assert.assertNotEquals(new ArrayList<>(), response.getData());
        String jsonResponse = GSON.toJson(response);
        LOG.debug(jsonResponse);
    }
}