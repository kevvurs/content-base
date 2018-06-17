package com.boomerang.contentbase.util;

import com.boomerang.contentbase.binding.*;
import com.boomerang.contentbase.data.ArticlePage;
import com.boomerang.contentbase.props.ContentProperties;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BindingTransform {
    private final ContentProperties contentProperties;
    private static final String ARTICLE_PAGE_TEMPLATE = "%s/articles?page=%s";
    private static final String LINK_PREV = "prev";
    private static final String LINK_SELF = "self";
    private static final String LINK_NEXT = "next";

    @Autowired
    private BindingTransform(ContentProperties contentProperties) {
        this.contentProperties = contentProperties;
    }

    public byte[] createArticleResponse(Integer pageNumber, ArticlePage page) {
        List<ArticleModel> articles = page.getArticles().stream()
                .map(art -> new ArticleModel.Builder()
                    .setId(art.getId())
                    .setSource(art.getSource())
                    .setAuthor(art.getAuthor())
                    .setTitle(art.getTitle())
                    .setDescription(art.getDescription())
                    .setUrl(art.getUrl())
                    .setImage(art.getImage())
                    .setPublished(art.getPublished())
                    .build())
                .collect(Collectors.toList());
        JSONAPIDocument<List<ArticleModel>> document = new JSONAPIDocument<>(articles);
        document.setLinks(createArticleLinks(pageNumber, page));
        ResourceConverter converter = new ResourceConverter(ArticleModel.class, CommentModel.class);
        byte[] response;
        try {
            response = converter.writeDocumentCollection(document);
        } catch (DocumentSerializationException e) {
            response = new byte[]{};
        }
        return response;
    }

    private Links createArticleLinks(Integer pageNumber, ArticlePage page) {
        String host = contentProperties.getHost();
        int prevPageNumber;
        int nextPageNumber;
        Link self;
        Link prev;
        Link next;
        final boolean noArticles = page.getArticles().isEmpty();
        if (pageNumber <= 1) {
            // on front page
            prevPageNumber = 0;
            nextPageNumber = (noArticles) ? 0:2;
            self = new Link(host.concat("/articles"));
            prev = new Link("");
            next = new Link(noArticles ? "":String.format(ARTICLE_PAGE_TEMPLATE, host, nextPageNumber));
        } else {
            // on intermediary page
            prevPageNumber = pageNumber - 1;
            nextPageNumber = noArticles ? 0:pageNumber + 1;
            self = new Link(String.format(ARTICLE_PAGE_TEMPLATE, host, pageNumber));
            prev = new Link(String.format(ARTICLE_PAGE_TEMPLATE, host, prevPageNumber));
            next = new Link(noArticles ? "":String.format(ARTICLE_PAGE_TEMPLATE, host, nextPageNumber));
        }
        Map<String, Link> navLinks = new HashMap<>();
        navLinks.put(LINK_PREV, prev);
        navLinks.put(LINK_SELF, self);
        navLinks.put(LINK_NEXT, next);
        return new Links(navLinks);
    }
}
