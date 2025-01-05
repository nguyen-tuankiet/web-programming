package com.example.backend.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.LocalDate;
import java.util.Date;

public class Card {
    Integer id;
    Integer userId;
    LocalDate duration;
    String type;
    Boolean isDefault;

    public Card(
            @ColumnName("id") @Nullable Integer id,
            @ColumnName("userId")  @Nullable Integer userId,
            @ColumnName("duration")  @Nullable LocalDate duration,
            @ColumnName("type")  @Nullable String type,
            @ColumnName("isDefault")  @Nullable Boolean isDefault) {
        this.id = id;
        this.userId = userId;
        this.duration = duration;
        this.type = type;
        this.isDefault = isDefault;
    }
}
