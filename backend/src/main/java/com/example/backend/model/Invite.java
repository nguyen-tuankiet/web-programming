package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Invite {
    String email;
    String name;
    int roleId;
    String token;
    String status;
    Long expiresAt;
    Long createdAt;

    public Invite(
            @ColumnName("email") String email    ,
            @ColumnName("name") String name,
            @ColumnName("roleId") int roleId,
            @ColumnName("token") String token,
            @ColumnName("status") String status,
            @ColumnName("expiresAt") Long expiresAt,
            @ColumnName("createdAt") Long createdAt
    ) {
        this.email = email;
        this.name = name;
        this.roleId = roleId;
        this.token = token;
        this.status = status;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
