package com.boomerang.contentbase.binding;

public class ArticleModel implements JsonApiModel {
    private final String id;
    private final String type;
    private final Attributes attributes;

    public ArticleModel() {
        this.id = "";
        this.type = "";
        this.attributes = new Attributes();
    }

    public ArticleModel(String id, String type, Attributes attributes) {
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    public ArticleModel(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.attributes = new Attributes(builder);
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public static class Attributes {
        private final String source;
        private final String author;
        private final String title;
        private final String description;
        private final String url;
        private final String image;
        private final String published;

        public Attributes() {
            this.source = "";
            this.author = "";
            this.title = "";
            this.description = "";
            this.url = "";
            this.image = "";
            this.published = "";
        }

        public Attributes(String source, String author, String title, String description, String url, String image, String published) {
            this.source = source;
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.image = image;
            this.published = published;
        }

        public Attributes(Builder builder) {
            this.source = builder.source;
            this.author = builder.author;
            this.title = builder.title;
            this.description = builder.description;
            this.url = builder.url;
            this.image = builder.image;
            this.published = builder.published;
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
    }

    public static class Builder {
        private String id;
        private String type;
        private String source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String image;
        private String published;

        public Builder() {
            this.id = "";
            this.type = "";
            this.source = "";
            this.author = "";
            this.title = "";
            this.description = "";
            this.url = "";
            this.image = "";
            this.published = "";
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
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

        public ArticleModel build() {
            return new ArticleModel(this);
        }
    }
}
