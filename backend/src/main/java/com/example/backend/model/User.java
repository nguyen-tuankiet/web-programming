package com.example.backend.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.LocalDate;
import java.util.Date;

public class User {
    String id;
    String fullName;
    String displayName;
    LocalDate birth;
    String gender;
    String email;
    String phone;
    String password;
    Integer avatarId;
    String role;
    String salt;
    String avatarUrl;

    public User(@ColumnName("id") String id,
                @ColumnName("fullName") @Nullable  String fullName,
                @ColumnName("displayName") @Nullable String displayName,
                @ColumnName("birth") @Nullable LocalDate  birth,
                @ColumnName("gender") @Nullable String gender,
                @ColumnName("email")@Nullable String email,
                @ColumnName("phone") @Nullable  String phone,
                @ColumnName("password") @Nullable String password,
                @ColumnName("avatarId") @Nullable Integer avatarId,
                @ColumnName("role") @Nullable String role,
                @ColumnName("salt")  @Nullable String salt,
                @ColumnName("avatarUrl") @Nullable String avatarUrl )

    {
        this.id = id;
        this.fullName = fullName;
        this.displayName = displayName;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.avatarId = avatarId;
        this.role = role;
        this.salt = salt;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public LocalDate  getBirth() {
        return birth;
    }

    public void setBirth(LocalDate  birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() { // Getter cho salt
        return salt;
    }

    public void setSalt(String salt) { // Setter cho salt
        this.salt = salt;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", birth=" + birth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", avatarId=" + avatarId +
                ", role='" + role + '\'' +
                ", salt='" + salt + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
