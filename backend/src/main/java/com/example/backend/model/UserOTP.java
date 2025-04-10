package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.sql.Timestamp;

public class UserOTP {
    private String email;
    private String otpCode;
    private Timestamp createdAt;
    private Timestamp expiresAt;

    @JdbiConstructor
    public UserOTP(
        @ColumnName("email") String email,
        @ColumnName("otpCode") String otpCode,
        @ColumnName("createdAt") Timestamp createdAt,
        @ColumnName("expiresAt") Timestamp expiresAt
    ) {
        this.email = email;
        this.otpCode = otpCode;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // Getters & setters
    public String getEmail() {
        return email;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "UserOTP{" +
                "email='" + email + '\'' +
                ", otpCode='" + otpCode + '\'' +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
