package com.example.backend.model;

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


    public Address(@ColumnName("id")  Integer id,
                   @ColumnName("userId") Integer userId,
                   @ColumnName("province") String province,
                   @ColumnName("district") String district,
                   @ColumnName("commune") String commune,
                   @ColumnName("detail") String detail,
                   @ColumnName("phone") String phone,
                   @ColumnName("name") String name,
                   @ColumnName("isDefault") Boolean isDefault,
                   @ColumnName("type") String type) {
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


}
