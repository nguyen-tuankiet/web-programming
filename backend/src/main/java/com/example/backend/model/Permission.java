package com.example.backend.model;

import com.example.backend.contant.EPermission;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Permission {
    Integer id;
    EPermission name;

    @JdbiConstructor
    public Permission(
            @ColumnName("id") Integer id,
            @ColumnName("name")  EPermission name
    ) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EPermission getName() {
        return name;
    }

    public void setName(EPermission name) {
        this.name = name;
    }
}
