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

    public ContentResponse createResponse(String currentCursor, ArticlePage page) {
        String host = contentProperties.getHost();
        String self = (currentCursor.isEmpty()) ?
                host.concat("/articles"):
                String.format("%s/articles?page=%s", host, currentCursor);
        String next = String.format("%s/articles?page=%s", host, page.getCursor());
        NavigationLinks links = new NavigationLinks.Builder()
                .setSelf(self)
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
                .setNextPage(page.getCursor())
                .build();
    }
}
