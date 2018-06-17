package com.boomerang.contentbase;

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
    public void testArticleEndpoint() {
        boolean passing;
        String id = "00163d053ee1759b91b789bada26f4fd";  // FIXME
        try {
            byte[] response = articleService.getArticle(id, "");
            String jsonResponse = new String(response);
            LOG.info(jsonResponse);
            passing = true;
        } catch (Exception e) {
            LOG.error("Article endpoint test failed", e);
            passing = false;
        }
        Assert.assertEquals(true, passing);
    }

    @Test
    public void testFrontpageEndpoint() {
        byte[] response = articleService.getPage("");
        String jsonResponse = new String(response);
        LOG.info(jsonResponse);
    }
}
