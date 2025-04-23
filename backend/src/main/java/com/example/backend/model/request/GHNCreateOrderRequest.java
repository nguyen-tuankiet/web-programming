package com.example.backend.model.request;

import com.example.backend.model.Address;
import com.example.backend.model.User;

import java.util.List;

public class GHNCreateOrderRequest {
    public int payment_type_id = 1;
    public String required_note= "KHONGCHOXEMHANG";


    public String to_name; // Required
    public String to_phone; // Required
    public String to_address; // Required
    public String to_ward_name; // Required
    public String to_district_name; // Required
    public String to_province_name; // Required

    int length=1;
    int weight=1;
    int width=1;
    int height=1;

    public int cod_amount;
    public String content;

    public int insurance_value;
    public int service_type_id = 5;
    public String coupon;
    public List<GHNItem> items;


    public GHNCreateOrderRequest(Address address, User user, String content, int codAmount, List<GHNItem> items) {
        this.to_name= user.getFullName();
        this.to_address= address.getDetail();
        this.to_ward_name = address.getCommune();
        this.to_district_name= address.getDistrict();
        this.to_province_name= address.getProvince();
        this.to_phone= address.getPhone();

        this.content = content;
        this.cod_amount = codAmount;
//        this.insurance_value= codAmount;
        this.items = items;
    }
    
}
