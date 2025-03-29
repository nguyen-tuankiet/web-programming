package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.DAO.AddressDAO;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class AddressSevice {
    AddressDAO addressDAO;


    public AddressSevice(Jdbi jdbi) {
        this.addressDAO = jdbi.onDemand(AddressDAO.class);
    }

    public List<Address> findByUserId(Integer userId) {
        return addressDAO.getAddressByUserId(userId);
    }

    public Address findById(Integer id) {
        return addressDAO.getAddressById(id);
    }

    public Address findDefautlByUserId(Integer id) {
        return addressDAO.getAddressDefaultByUserId(id);
    }

    public int addAddress(Address address) {
        // Đảm bảo isDefault mặc định là false nếu không được chỉ định
        if (address.getIsDefault() == null) {
            address.setIsDefault(false);
        }
        return addressDAO.addAddress(
                address.getUserId(),
                address.getProvince(),
                address.getDistrict(),
                address.getCommune(),
                address.getDetail(),
                address.getPhone(),
                address.getName(),
                address.getIsDefault(),
                address.getType(),
                address.getStatus()
        );
    }

    public Boolean updateDefautlById(Integer id, boolean defaultStatus) {
        return addressDAO.updateDefaultById(id, defaultStatus);
    }


    public static void main(String[] args) {
        AddressSevice addressSevice = new AddressSevice(DBConnection.getJdbi());
        System.out.println(addressSevice.findById(1));
    }





}
