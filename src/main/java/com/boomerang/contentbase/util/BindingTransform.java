package com.boomerang.contentbase.util;

import com.boomerang.contentbase.binding.ArticleModel;
import com.boomerang.contentbase.binding.ContentResponse;
import com.boomerang.contentbase.binding.JsonApiModel;
import com.boomerang.contentbase.binding.NavigationLinks;
import com.boomerang.contentbase.data.ArticlePage;
import com.boomerang.contentbase.props.ContentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BindingTransform {
    private final ContentProperties contentProperties;

    @Autowired
    private BindingTransform(ContentProperties contentProperties) {
        this.contentProperties = contentProperties;
    }

    public ContentResponse createResponse(Integer pageNumber, ArticlePage page) {
        String host = contentProperties.getHost();
        int lastPageNumber;
        int nextPageNumber;
        String self;
        String last;
        String next;
        final boolean noArticles = page.getArticles().isEmpty();
        if (pageNumber <= 1) {
            // on front page
            lastPageNumber = 0;
            nextPageNumber = (noArticles) ? 0:2;
            self = host.concat("/articles");
            last = "";
            next = noArticles ? "":String.format("%s/articles?page=%s", host, nextPageNumber);
        } else {
            // on intermediary page
            lastPageNumber = pageNumber - 1;
            nextPageNumber = noArticles ? 0:pageNumber + 1;
            self = String.format("%s/articles?page=%s", host, pageNumber);
            last = String.format("%s/articles?page=%s", host, lastPageNumber);
            next = noArticles ? "":String.format("%s/articles?page=%s", host, nextPageNumber);
        }
        NavigationLinks links = new NavigationLinks.Builder()
                .setSelf(self)
                .setLast(last)
                .setNext(next)
                .build();
        List<JsonApiModel> articles = page.getArticles().stream()
                .map(art -> new ArticleModel.Builder()
                    .setId(art.getId())
                    .setType("articles")
                    .setSource(art.getSource())
                    .setAuthor(art.getAuthor())
                    .setTitle(art.getTitle())
                    .setDescription(art.getDescription())
                    .setUrl(art.getUrl())
                    .setImage(art.getImage())
                    .setPublished(art.getPublished())
                    .build())
                .collect(Collectors.toList());
        return new ContentResponse.Builder()
                .setLinks(links)
                .addData(articles)
                .setNextPage(nextPageNumber)
                .setLastPage(lastPageNumber)
                .build();
    }
}
