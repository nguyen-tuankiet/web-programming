package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Logs {
    @ColumnName("id")
    Integer id;
    @ColumnName("type")
    String type;
    @ColumnName("createdAt")
    Long createdAt;
    @ColumnName("oldData")
    String oldData;
    @ColumnName("newData")
    String newData;


    public Logs(Integer id,String type, String oldData,String newData) {
        this.id = id;
        this.type = type;
        this.createdAt = System.currentTimeMillis();
        this.oldData = oldData;
        this.newData = newData;
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }
}
