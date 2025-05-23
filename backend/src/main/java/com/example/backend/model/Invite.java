package com.example.backend.model;

import com.example.backend.contant.Status;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Invite {
    Integer id;
    String email;
    String name;
    int roleId;
    Status status;
    Long expiresAt;
    Long createdAt;

    @JdbiConstructor
    public Invite(
            @ColumnName("id") Integer id,
            @ColumnName("email") String email    ,
            @ColumnName("name") String name,
            @ColumnName("roleId") int roleId,
            @ColumnName("status") Status status,
            @ColumnName("expiresAt") Long expiresAt,
            @ColumnName("createdAt") Long createdAt
    ) {
        this.email = email;
        this.name = name;
        this.roleId = roleId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    @Override
    public String toString() {
        return "Invite{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", status=" + status +
                ", expiresAt=" + expiresAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
