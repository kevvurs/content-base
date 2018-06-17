package com.boomerang.contentbase;

import com.google.cloud.TransportOptions;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.KeyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ContentApp {
    public static void main(String[] args) {
        SpringApplication.run(ContentApp.class, args);
    }

    @Bean
    public Datastore datastore() {
        return DatastoreOptions.getDefaultInstance().getService();
    }

    @Bean
    public KeyFactory keyFactory(Datastore datastore) {
        return datastore.newKeyFactory().setKind("ArticleEntity");
    }
}
