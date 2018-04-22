package com.boomerang.contentbase.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page {
    private final List<Article> articles;
    private final String cursor;

    public Page() {
        this.articles = Collections.unmodifiableList(new ArrayList<>());
        this.cursor = "";
    }

    public Page(List<Article> articles, String cursor) {
        this.articles = Collections.unmodifiableList(articles);
        this.cursor = cursor;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getCursor() {
        return cursor;
    }

    public static class Builder {
        private List<Article> articles;
        private String cursor;

        public Builder() {
            this.articles = new ArrayList<>();
            this.cursor = "";
        }

        public Builder setArticles(List<Article> articles) {
            this.articles.addAll(articles);
            return this;
        }

        public Builder setCursor(String cursor) {
            this.cursor = cursor;
            return this;
        }

        public Page build() {
            return new Page(articles, cursor);
        }
    }
}
