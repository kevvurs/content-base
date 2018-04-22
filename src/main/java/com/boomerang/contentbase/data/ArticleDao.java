package com.boomerang.contentbase.data;

import com.boomerang.contentbase.props.ContentProperties;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.boomerang.contentbase.data.Article.*;

@Repository
public class ArticleDao {
    private final ContentProperties contentProperties;
    private final KeyFactory keyFactory;
    private final Datastore datastore;

    @Autowired
    public ArticleDao(ContentProperties contentProperties, KeyFactory keyFactory, Datastore datastore) {
        this.contentProperties = contentProperties;
        this.keyFactory = keyFactory;
        this.datastore = datastore;
    }

    @Cacheable("articles")
    public Article getArticle(String id) {
        Key key = keyFactory.newKey(id);
        Entity entity = datastore.get(key);
        return buildArticle(entity);
    }

    @Cacheable("pages")
    public Page getPage(String pageCursor) {
        Cursor cursor;
        if (pageCursor.isEmpty()) {
            cursor = null;
        } else {
            cursor = Cursor.fromUrlSafe(pageCursor);
        }
        String kind = contentProperties.getDatastore().getKind();
        Integer pageSize = contentProperties.getDatastore().getPageSize();
        EntityQuery.Builder queryBuilder = Query.newEntityQueryBuilder()
                .setKind(kind)
                .setLimit(pageSize);
        if (cursor != null) {
            queryBuilder.setStartCursor(cursor);
        }
        QueryResults<Entity> articles = datastore.run(queryBuilder.build());
        List<Article> articleList = new ArrayList<>();
        while (articles.hasNext()) {
            Entity entity = articles.next();
            Article article = buildArticle(entity);
            articleList.add(article);
        }
        return new Page.Builder()
                .setArticles(articleList)
                .setCursor(articles.getCursorAfter().toUrlSafe())
                .build();
    }

    private Article buildArticle(Entity entity) {
        return new Builder()
                .setId(entity.getString(FIELD_ID))
                .setSource(entity.getString(FIELD_SRC))
                .setAuthor(entity.getString(FIELD_AUTH))
                .setTitle(entity.getString(FIELD_TITL))
                .setDescription(entity.getString(FIELD_DESC))
                .setUrl(entity.getString(FIELD_URL))
                .setImage(entity.getString(FIELD_URLI))
                .setPublished(serializeTime(entity.getTimestamp(FIELD_PUBL)))
                .build();
    }

    private String serializeTime(Timestamp timestamp) {
        java.sql.Timestamp sqlTimestamp = timestamp.toSqlTimestamp();
        Date date = new Date();
        date.setTime(sqlTimestamp.getTime());
        DateFormat dateFormat = new SimpleDateFormat(contentProperties.getDateFormat());
        return dateFormat.format(date);
    }
}
