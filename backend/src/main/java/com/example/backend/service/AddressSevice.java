package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.DAO.AddressDAO;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;

import java.util.List;

@RegisterBeanMapper(Address.class)
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
        return addressDAO.addAddress(address);
    }

    public Boolean updateDefautlById(Integer id, boolean defaultStatus) {
        return addressDAO.updateDefaultById(id, defaultStatus);
    }

    public Boolean updateStatus(Integer id, String status) {
        return addressDAO.updateStatus(id, status);
    }

    public static void main(String[] args) {
        AddressSevice addressSevice = new AddressSevice(DBConnection.getJdbi());
//        Address address = new Address(null, 42, "HCM", 202, "ThuDuc"
//                , 203, "LinhTrung", 204, "detail"
//                ,"0299993222", "Name", false, "HOME", "ACTIVE"
//                );
//        addressSevice.addAddress(address);
        System.out.println(addressSevice.findByUserId(42));
    }





}
