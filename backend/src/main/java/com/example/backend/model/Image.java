package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Image {
    Integer id;
    String url;

    public Image(@ColumnName("id") Integer id,@ColumnName("url") String url) {
        this.id = id;
        this.url = url;
    }
}
