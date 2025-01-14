package com.example.backend.model.DAO;

import com.example.backend.model.Address;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
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

    @SqlUpdate("INSERT INTO address (userId, province, district, commune, detail, phone, name, isDefault, type) " +
            "VALUES (:userId, :province, :district, :commune, :detail, :phone, :name, :isDefault, :type)")
    @GetGeneratedKeys("id")
    int addAddress(@Bind("userId") Integer userId,
                   @Bind("province") String province,
                   @Bind("district") String district,
                   @Bind("commune") String commune,
                   @Bind("detail") String detail,
                   @Bind("phone") String phone,
                   @Bind("name") String name,
                   @Bind("isDefault") Boolean isDefault,
                   @Bind("type") String type);





}
