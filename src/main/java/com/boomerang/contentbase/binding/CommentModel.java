package com.boomerang.contentbase.binding;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("comment")
public class CommentModel {
    @Id
    private final String id;
    private final String user;
    private final Long timestamp;
    private final String articleId;
    private final String content;

    public CommentModel() {
        this.id = "";
        this.user = "";
        this.timestamp = 0l;
        this.articleId = "";
        this.content = "";
    }

    public CommentModel(String id, String user, Long timestamp, String articleId, String content) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
        this.articleId = articleId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public Object clone() {
        return new CommentModel(id, user, timestamp, articleId, content);
    }

    // TODO: builder
    public static class Builder {
        private String id;
        private String user;
        private Long timestamp;
        private String articleId;
        private String content;

        public Builder() {
            this.id = "";
            this.user = "";
            this.timestamp = 0l;
            this.articleId = "";
            this.content = "";
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setArticleId(String articleId) {
            this.articleId = articleId;
            return this;
        }
    }
}
