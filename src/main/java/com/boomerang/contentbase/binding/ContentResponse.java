package com.boomerang.contentbase.binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentResponse {
    private final NavigationLinks links;
    private final List<JsonApiModel> data;
    private final Meta meta;

    public ContentResponse() {
        this.links = new NavigationLinks();
        this.data = Collections.unmodifiableList(new ArrayList<>());
        this.meta = new Meta();
    }

    public ContentResponse(NavigationLinks links, List<JsonApiModel> data, Meta meta) {
        this.links = links;
        this.data = Collections.unmodifiableList(data);
        this.meta = meta;
    }

    public ContentResponse(Builder builder) {
        this.links = builder.links;
        this.data = new ArrayList<>(builder.data);
        this.meta = new Meta(builder);
    }

    public NavigationLinks getLinks() {
        return links;
    }

    public List<JsonApiModel> getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

    public static class Meta {
        private final Integer nextPage;
        private final Integer lastPage;

        public Meta() {
            this.nextPage = 0;
            this.lastPage = 0;
        }

        public Meta(Integer nextPage, Integer lastPage) {
            this.nextPage = nextPage;
            this.lastPage = lastPage;
        }

        public Meta(Builder builder) {
            this.nextPage = builder.nextPage;
            this.lastPage = builder.lastPage;
        }

        public Integer getNextPage() {
            return nextPage;
        }

        public Integer getLastPage() {
            return lastPage;
        }
    }

    public static class Builder {
        private NavigationLinks links;
        private List<JsonApiModel> data;
        private Integer nextPage;
        private Integer lastPage;

        public Builder() {
            this.links = new NavigationLinks();
            this.data = new ArrayList<>();
            this.nextPage = 0;
            this.lastPage = 0;
        }

        public Builder setLinks(NavigationLinks links) {
            this.links = links;
            return this;
        }

        public Builder setData(List<? extends JsonApiModel> data) {
            this.data = new ArrayList<>(data);
            return this;
        }

        public Builder addData(List<? extends JsonApiModel> data) {
            this.data.addAll(data);
            return this;
        }

        public Builder setNextPage(Integer nextPage) {
            this.nextPage = nextPage;
            return this;
        }

        public Builder setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
            return this;
        }

        public ContentResponse build() {
            return new ContentResponse(this);
        }
    }
}
