package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import java.time.LocalDate;

public class Banner {
    Integer id;
    String status;
    String imageId;
    LocalDate startDate;
    LocalDate endDate;

    public Banner(@ColumnName("id") Integer id,
                  @ColumnName("status") String status,
                  @ColumnName("imageId") String imageId,
                  @ColumnName("startDate") LocalDate startDate,
                  @ColumnName("endDate") LocalDate endDate) {
        this.id = id;
        this.status = status;
        this.imageId = imageId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() { return id; }
    public String getStatus() { return status; }
    public String getImageId() { return imageId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public void setId(Integer id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
    public void setImageId(String imageId) { this.imageId = imageId; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
