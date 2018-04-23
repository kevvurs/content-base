package com.boomerang.contentbase.binding;

public class NavigationLinks {
    private final String self;
    private final String next;
    private final String last;

    public NavigationLinks() {
        this.self = "";
        this.next = "";
        this.last = "";
    }

    public NavigationLinks(String self, String next, String last) {
        this.self = self;
        this.next = next;
        this.last = last;
    }

    public NavigationLinks(Builder builder) {
        this.self = builder.self;
        this.next = builder.next;
        this.last = builder.last;
    }

    public String getSelf() {
        return self;
    }

    public String getNext() {
        return next;
    }

    public String getLast() {
        return last;
    }

    public static class Builder {
        private String self;
        private String next;
        private String last;

        public Builder() {
            this.self = "";
            this.next = "";
            this.last = "";
        }

        public Builder setSelf(String self) {
            this.self = self;
            return this;
        }

        public Builder setNext(String next) {
            this.next = next;
            return this;
        }

        public Builder setLast(String last) {
            this.last = last;
            return this;
        }

        public NavigationLinks build() {
            return new NavigationLinks(this);
        }
    }
}
