package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

public class User {
    String id;
    String fullName;
    String displayName;
    Date birth;
    String gender;
    String email;
    String phone;
    String password;
    Integer avatarId;


    public User(@ColumnName("id") String id,
                @ColumnName("fullName") String fullName,
                @ColumnName("displayName") String displayName,
                @ColumnName("birth") Date birth,
                @ColumnName("gender") String gender,
                @ColumnName("email") String email,
                @ColumnName("phone") String phone,
                @ColumnName("password") String password,
                @ColumnName("avatarId")Integer avatarId) {
        this.id = id;
        this.fullName = fullName;
        this.displayName = displayName;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.avatarId = avatarId;
    }
}
