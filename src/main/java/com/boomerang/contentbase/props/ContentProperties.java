package com.boomerang.contentbase.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class ContentProperties {
    private DatastoreProperties datastore;
    private String dateFormat;
    private String host;

    public ContentProperties(){}

    public DatastoreProperties getDatastore() {
        return datastore;
    }

    public void setDatastore(DatastoreProperties datastore) {
        this.datastore = datastore;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static class DatastoreProperties {
        private String kind;
        private Integer pageSize;

        public DatastoreProperties(){}

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }
}
