package com.example.backend.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderResponse {
    String order_code;
    String sort_code;
    String trans_type;
    String ward_encode;
    String district_encode;
    int total_fee;
    String expected_delivery_time;

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getSort_code() {
        return sort_code;
    }

    public void setSort_code(String sort_code) {
        this.sort_code = sort_code;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getWard_encode() {
        return ward_encode;
    }

    public void setWard_encode(String ward_encode) {
        this.ward_encode = ward_encode;
    }

    public String getDistrict_encode() {
        return district_encode;
    }

    public void setDistrict_encode(String district_encode) {
        this.district_encode = district_encode;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getExpected_delivery_time() {
        return expected_delivery_time;
    }

    public void setExpected_delivery_time(String     expected_delivery_time) {
        this.expected_delivery_time = expected_delivery_time;
    }
}
