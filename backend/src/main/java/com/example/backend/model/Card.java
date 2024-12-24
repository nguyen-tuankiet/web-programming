package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

public class Card {
    int id;
    int userId;
    Date duration;
    String type;
    boolean isDefault;

    public Card(
            @ColumnName("id") int id,
            @ColumnName("userId") int userId,
            @ColumnName("duration") Date duration,
            @ColumnName("type") String type,
            @ColumnName("isDefault") boolean isDefault) {
        this.id = id;
        this.userId = userId;
        this.duration = duration;
        this.type = type;
        this.isDefault = isDefault;
    }
}
