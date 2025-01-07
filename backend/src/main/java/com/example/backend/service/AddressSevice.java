package com.example.backend.service;

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
}
