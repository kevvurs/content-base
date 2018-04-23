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
        private final String nextPage;

        public Meta() {
            this.nextPage = "";
        }

        public Meta(String nextPage) {
            this.nextPage = nextPage;
        }

        public Meta(Builder builder) {
            this.nextPage = builder.nextPage;
        }

        public String getNextPage() {
            return nextPage;
        }
    }

    public static class Builder {
        private NavigationLinks links;
        private List<JsonApiModel> data;
        private String nextPage;

        public Builder() {
            this.links = new NavigationLinks();
            this.data = new ArrayList<>();
            this.nextPage = "";
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

        public Builder setNextPage(String nextPage) {
            this.nextPage = nextPage;
            return this;
        }

        public ContentResponse build() {
            return new ContentResponse(this);
        }
    }
}
