package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    Integer id;
    Integer userId;
    String province;
    Integer provinceId;
    String district;
    Integer districtId;
    String commune;
    Integer communeId;
    String detail;
    String phone;
    String name;
    Boolean isDefault;
    String type;
    String status = "ACTIVE";

    @JdbiConstructor
    public Address(@ColumnName("id") Integer id,
                   @ColumnName("userId") @Nullable Integer userId,
                   @ColumnName("province") @Nullable String province,
                   @ColumnName("provinceId") @Nullable Integer provinceId,
                   @ColumnName("district") @Nullable String district,
                   @ColumnName("districtId") @Nullable Integer districtId,
                   @ColumnName("commune") @Nullable String commune,
                   @ColumnName("communeId") @Nullable Integer communeId,
                   @ColumnName("detail") @Nullable String detail,
                   @ColumnName("phone") @Nullable String phone,
                   @ColumnName("name") @Nullable String name,
                   @ColumnName("isDefault")  @Nullable Boolean isDefault,
                   @ColumnName("type")  @Nullable String type,
                   @ColumnName("status")  @Nullable String status

    )

    {
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.provinceId = provinceId;
        this.district = district;
        this.districtId = districtId;
        this.commune = commune;
        this.communeId = communeId;
        this.detail = detail;
        this.phone = phone;
        this.name = name;
        this.isDefault = isDefault;
        this.type = type;
        if (status==null) {
            this.status = "ACTIVE";
        }
        else this.status = status;

    }

    public Address() {
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

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Integer communeId) {
        this.communeId = communeId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", province='" + province + '\'' +
                ", provinceId=" + provinceId +
                ", district='" + district + '\'' +
                ", districtId=" + districtId +
                ", commune='" + commune + '\'' +
                ", communeId=" + communeId +
                ", detail='" + detail + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", isDefault=" + isDefault +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}