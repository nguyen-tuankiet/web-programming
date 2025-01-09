package com.example.backend.model.DAO;

import com.example.backend.model.Order;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;

public interface OrderDAO {

    @SqlUpdate(value = "INSERT INTO orders(createAt, paymentStatus, orderStatus, userId, addressId, cardId, isCOD)\n" +
            "VALUE (\n" +
            "  :createAt , :paymentStatus, :orderStatus , :userId, :addressId, :cardId, :isCOD" +
            " )")

    @RegisterConstructorMapper(Order.class)
    @GetGeneratedKeys
    Integer createOrder(
            @Bind("createAt")LocalDate createAt,
            @Bind("paymentStatus") String paymentStatus,
            @Bind("orderStatus") String orderStatus,
            @Bind("userId") Integer userId,
            @Bind("addressId") Integer addressId,
            @Bind("cardId") Integer cardId,
            @Bind("isCOD") Boolean isCOD
    );

}
