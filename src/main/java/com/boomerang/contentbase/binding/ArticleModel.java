package com.boomerang.contentbase.binding;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Type("article")
public class ArticleModel {
    @Id
    private final String id;
    private final String source;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String image;
    private final String published;
    @Relationship("comments")
    private final List<CommentModel> comments;

    public ArticleModel() {
        this.id = "";
        this.source = "";
        this.author = "";
        this.title = "";
        this.description = "";
        this.url = "";
        this.image = "";
        this.published = "";
        this.comments = new ArrayList<>();
    }

    public ArticleModel(String id, String source, String author, String title, String description, String url, String image, String published, List<CommentModel> comments) {
        this.id = id;
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = image;
        this.published = published;
        this.comments = comments;
    }

    public ArticleModel(Builder builder) {
        this.id = builder.id;
        this.source = builder.source;
        this.author = builder.author;
        this.title = builder.title;
        this.description = builder.description;
        this.url = builder.url;
        this.image = builder.image;
        this.published = builder.published;
        this.comments = builder.comments;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPublished() {
        return published;
    }

    @Override
    public Object clone() {
        List<CommentModel> commentsClone = new ArrayList<>();
        Collections.copy(commentsClone, comments);
        return new ArticleModel(id, source, author, title, description, url, image, published, commentsClone);
    }

    public static class Builder {
        private String id;
        private String source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String image;
        private String published;
        private List<CommentModel> comments;

        public Builder() {
            this.id = "";
            this.source = "";
            this.author = "";
            this.title = "";
            this.description = "";
            this.url = "";
            this.image = "";
            this.published = "";
            this.comments = new ArrayList<>();
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setPublished(String published) {
            this.published = published;
            return this;
        }

        public Builder addAllComments(List<CommentModel> comments) {
            this.comments.addAll(comments);
            return this;
        }

        public ArticleModel build() {
            return new ArticleModel(this);
        }
    }
}
