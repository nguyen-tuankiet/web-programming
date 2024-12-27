package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Image {
    String id;
    String url;

    public Image(@ColumnName("id") String id,@ColumnName("url") String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
