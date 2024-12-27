package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

public class Banner {
    Integer id;
    String status;
    String imageId;
    Date startDate;
    Date endDate;

    public Banner(@ColumnName("id") Integer id,
                  @ColumnName("status") String status,
                  @ColumnName("imageId") String imageId,
                  @ColumnName("startDate") Date startDate,
                  @ColumnName("endDate") Date endDate) {
        this.id = id;
        this.status = status;
        this.imageId = imageId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
