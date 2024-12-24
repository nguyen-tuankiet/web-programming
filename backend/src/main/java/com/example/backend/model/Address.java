package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Address {
    int id;
    int userId;
    String province;
    String district;
    String commune;
    String detail;
    String phone;
    String name;
    boolean isDefault;
    String type;


    public Address(@ColumnName("id") int id,
                   @ColumnName("userId") int userId,
                   @ColumnName("province") String province,
                   @ColumnName("district") String district,
                   @ColumnName("commune") String commune,
                   @ColumnName("detail") String detail,
                   @ColumnName("phone") String phone,
                   @ColumnName("name") String name,
                   @ColumnName("isDefault")boolean isDefault,
                   @ColumnName("type")String type) {
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.detail = detail;
        this.phone = phone;
        this.name = name;
        this.isDefault = isDefault;
        this.type = type;
    }










    public int getId() {
        return id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
