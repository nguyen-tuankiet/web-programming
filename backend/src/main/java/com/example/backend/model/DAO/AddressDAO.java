package com.example.backend.model.DAO;

import com.example.backend.model.Address;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Address.class)
public interface AddressDAO {

    @SqlQuery(value = "SELECT *" +
            " FROM address" +
            " WHERE userId = :userId;")
    List<Address> getAddressByUserId(@Bind("userId") Integer userId);


    @SqlQuery(value = "SELECT *" +
            " FROM address" +
            " WHERE id = :addressId;")
    Address getAddressById(@Bind("addressId") Integer addressId);



    @SqlQuery(value = "SELECT *" +
            " FROM address" +
            " WHERE userId = :userId and isDefault =1;")
    Address getAddressDefaultByUserId(@Bind("userId") Integer userId);


    @SqlUpdate("UPDATE address " +
            "SET isDefault = :defaultStatus " +
            "WHERE id = :id; ")
    Boolean updateDefaultById(@Bind("id") Integer id, @Bind("defaultStatus") boolean defaultStatus);




    @SqlUpdate("INSERT INTO address (userId, province, provinceId, district, districtId, commune, communeId, detail, phone, name, isDefault, type, status) " +
            "VALUES (:userId, :province, :provinceId, :district, :districtId , :commune, :communeId, :detail, :phone, :name, :isDefault, :type, :status)")
    int addAddress(    @BindBean Address address);
}
