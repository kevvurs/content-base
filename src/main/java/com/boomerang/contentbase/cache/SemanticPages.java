package com.boomerang.contentbase.cache;

import com.boomerang.contentbase.data.ArticleDao;
import com.boomerang.contentbase.data.ArticlePage;
import com.boomerang.contentbase.err.PageNotFound;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SemanticPages {
    private static final Logger LOG = LoggerFactory.getLogger(SemanticPages.class);

    private final ArticleDao articleDao;
    private final Cache<Integer, String> cursors;

    @Autowired
    public SemanticPages(ArticleDao articleDao) {
        this.articleDao = articleDao;
        cursors = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    public String findCursor(final int pageNumber) throws PageNotFound {
        if (pageNumber > 100) {
            throw new PageNotFound(String.format("Invalid page number entered <%s>", pageNumber));
        } else if (pageNumber <= 1) {
            return "";
        } else {
            return cursors.get(pageNumber, k -> fetchCursor(pageNumber));
        }
    }

    public void defineNextCursor(final int pageNumber, final String cursor) {
        int nextPage = pageNumber + 1;
        cursors.put(nextPage, cursor);
    }

    private String fetchCursor(final int pageNumber) throws PageNotFound {
        int nextPage = 1;
        String cursor = "";
        ArticlePage page;
        do {
            page = articleDao.getPage(cursor);
            cursor = page.getCursor();
            nextPage++;
        } while (pageNumber > nextPage && !page.getArticles().isEmpty());
        if (pageNumber > nextPage) {
            throw new PageNotFound(String.format("Page number exceeded search range <%s>", pageNumber));
        }
        return cursor;
    }
}