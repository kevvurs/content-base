package com.boomerang.contentbase.data;

public class ArticleEntity {
    protected static final String FIELD_AUTH = "author";
    protected static final String FIELD_DESC = "description";
    protected static final String FIELD_ID   = "id";
    protected static final String FIELD_PUBL = "publishedAt";
    protected static final String FIELD_SRC  = "source";
    protected static final String FIELD_TITL = "title";
    protected static final String FIELD_URL  = "url";
    protected static final String FIELD_URLI = "urlToImage";

    private final String id;
    private final String source;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String image;
    private final String published;

    public ArticleEntity() {
        this.id = "";
        this.source = "";
        this.author = "";
        this.title = "";
        this.description = "";
        this.url = "";
        this.image = "";
        this.published = "";
    }

    public ArticleEntity(String id, String source, String author, String title, String description, String url, String image, String published) {
        this.id = id;
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = image;
        this.published = published;
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

    public static class Builder {
        private String id;
        private String source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String image;
        private String published;

        public Builder() {
            this.id = "";
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

        public ArticleEntity build() {
            return new ArticleEntity(id, source, author, title, description, url, image, published);
        }
    }
}
