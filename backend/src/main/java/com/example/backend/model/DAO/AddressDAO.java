package com.example.backend.model.DAO;

import com.example.backend.model.Address;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterConstructorMapper(Address.class)
public interface AddressDAO {

    @SqlQuery(value = "SELECT *" +
            " FROM address" +
            " WHERE userId = :id;")
    Address getAddressByUserId(@Bind("id") Integer id);

}
