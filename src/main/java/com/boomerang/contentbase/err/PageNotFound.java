package com.boomerang.contentbase.err;

public class PageNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public PageNotFound(String message) {
        super(message);
    }
}
