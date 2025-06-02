package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import java.time.LocalDate;

public class Banner {
    Integer id;
    String title;
    String imageId;
    LocalDate startDate;
    LocalDate endDate;
    Boolean isActive;
    String description;

    public Banner(@ColumnName("id") Integer id,
                  @ColumnName("title") String title,
                  @ColumnName("imageId") String imageId,
                  @ColumnName("startDate") LocalDate startDate,
                  @ColumnName("endDate") LocalDate endDate,
                  @ColumnName("isActive") Boolean isActive,
                  @ColumnName("description") String description
    ) {
        this.id = id;
        this.title = title;
        this.imageId = imageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.description = description;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getImageId() { return imageId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Boolean getIsActive() { return isActive; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public void setId(Integer id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setImageId(String imageId) { this.imageId = imageId; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
