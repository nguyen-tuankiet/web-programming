package com.example.backend.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Address {
    Integer id;
    Integer userId;
    String province;
    String district;
    String commune;
    String detail;
    String phone;
    String name;
    Boolean isDefault;
    String type;


    public Address(@ColumnName("id") Integer id,
                   @ColumnName("userId") @Nullable Integer userId,
                   @ColumnName("province") @Nullable String province,
                   @ColumnName("district") @Nullable String district,
                   @ColumnName("commune") @Nullable String commune,
                   @ColumnName("detail") @Nullable String detail,
                   @ColumnName("phone") @Nullable String phone,
                   @ColumnName("name") @Nullable String name,
                   @ColumnName("isDefault")  @Nullable Boolean isDefault,
                   @ColumnName("type")  @Nullable String type) {
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}