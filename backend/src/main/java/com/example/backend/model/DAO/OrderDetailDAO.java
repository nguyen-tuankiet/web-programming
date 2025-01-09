package com.example.backend.model.DAO;

import com.example.backend.model.OrderDetail;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface OrderDetailDAO {

    @SqlUpdate(value ="INSERT  INTO order_detail ( orderId, productId, quantity, total, optionId)\n" +
            " VALUE (:orderId, :productId, :quantity, :total, :optionId)")
    @RegisterConstructorMapper(OrderDetail.class)
    Boolean addOrderDetail(
            @Bind("orderId") Integer orderId,
            @Bind("productId") Integer productId,
            @Bind("quantity") Integer quantity,
            @Bind("total") Integer total,
            @Bind("optionId") Integer optionId

    );

}
