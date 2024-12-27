package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

public class Card {
    Integer id;
    Integer userId;
    Date duration;
    String type;
    Boolean isDefault;

    public Card(
            @ColumnName("id") Integer id,
            @ColumnName("userId") Integer userId,
            @ColumnName("duration") Date duration,
            @ColumnName("type") String type,
            @ColumnName("isDefault") Boolean isDefault) {
        this.id = id;
        this.userId = userId;
        this.duration = duration;
        this.type = type;
        this.isDefault = isDefault;
    }
}
