package com.boomerang.contentbase.binding;

public class Article implements JsonApiModel {
    private String id;
    private String type;
    private Attributes attributes;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    private class Attributes {
        private String title;

    }
}
