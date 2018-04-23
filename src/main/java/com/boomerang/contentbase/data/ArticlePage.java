package com.boomerang.contentbase.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticlePage {
    private final List<ArticleEntity> articles;
    private final String cursor;

    public ArticlePage() {
        this.articles = Collections.unmodifiableList(new ArrayList<>());
        this.cursor = "";
    }

    public ArticlePage(List<ArticleEntity> articles, String cursor) {
        this.articles = Collections.unmodifiableList(articles);
        this.cursor = cursor;
    }

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public String getCursor() {
        return cursor;
    }

    public static class Builder {
        private List<ArticleEntity> articles;
        private String cursor;

        public Builder() {
            this.articles = new ArrayList<>();
            this.cursor = "";
        }

        public Builder setArticles(List<ArticleEntity> articles) {
            this.articles = new ArrayList<>(articles);
            return this;
        }

        public Builder addArticles(List<ArticleEntity> articles) {
            this.articles.addAll(articles);
            return this;
        }

        public Builder setCursor(String cursor) {
            this.cursor = cursor;
            return this;
        }

        public ArticlePage build() {
            return new ArticlePage(articles, cursor);
        }
    }
}
