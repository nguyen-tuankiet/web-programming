package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Member {
    public enum Status {
        PENDING, ACTIVE, BANNED, DEACTIVE
    }

    private int id;
    private String fullName;
    private String email;
    private String role;
    private Status status;
    private String avatarUrl;

    public Member(
            @ColumnName("id") int id,
            @ColumnName("fullName") String fullName,
            @ColumnName("email") String email,
            @ColumnName("role") String role,
            @ColumnName("status") String status,
            @ColumnName("avatarUrl") String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.avatarUrl = avatarUrl;
        try {
            this.status = (status != null) ? Status.valueOf(status.trim().toUpperCase()) : Status.PENDING;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi mapping status: " + status);
            this.status = Status.PENDING;
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}
