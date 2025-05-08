package com.example.backend.model;

import com.example.backend.contant.ERole;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.List;

public class Role {
    Integer id;
    ERole roleType;
    String name;
    String description;

    public Role(
            @ColumnName("id") Integer id,
            @ColumnName("roleType") ERole roleType,
            @ColumnName("name") String name,
            @ColumnName("description") @Nullable String description) {
        this.id = id;
        this.roleType = roleType;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRoleType() {
        return roleType;
    }

    public void setRoleType(ERole roleType) {
        this.roleType = roleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
