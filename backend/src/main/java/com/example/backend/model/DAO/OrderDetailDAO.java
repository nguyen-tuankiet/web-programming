package com.example.backend.model.DAO;

import com.example.backend.model.OrderDetail;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;import java.util.List;
@RegisterConstructorMapper(OrderDetail.class)
public interface OrderDetailDAO {

    @SqlUpdate(value ="INSERT  INTO order_detail ( orderId, productId, quantity, total, optionId)\n" +
            " VALUE (:orderId, :productId, :quantity, :total, :optionId)")
    Boolean addOrderDetail(
            @Bind("orderId") Integer orderId,
            @Bind("productId") Integer productId,
            @Bind("quantity") Integer quantity,
            @Bind("total") Integer total,
            @Bind("optionId") Integer optionId

    );



    @SqlQuery("SELECT name FROM products WHERE id = :productId")
    String getProductNameById(@Bind("productId") Integer productId);


    @SqlQuery("SELECT quantity FROM order_detail WHERE id = :orderDetailId")
    Integer getQuantityByOrderDetailId(@Bind("orderDetailId") Integer orderDetailId);

    @SqlQuery("SELECT orderStatus FROM orders WHERE id = :orderId")
    String getOrderStatusByOrderId(@Bind("orderId") Integer orderId);

//    @SqlQuery(value = " SELECT\n" +
//            "        od.id AS id,\n" +
//            "        od.orderId AS orderId,\n" +
//            "        od.productId AS productId,\n" +
//            "        od.quantity AS quantity,\n" +
//            "        od.total AS total,\n" +
//            "        p.name AS productName\n" +
//            "    FROM order_detail od\n" +
//            "    JOIN products p ON od.productId = p.id\n" +
//            "    WHERE od.orderId = :orderId")
//    @RegisterConstructorMapper(OrderDetail.class)
//    List<OrderDetail> getOrderDetailsByOrderId(@Bind("orderId") Integer orderId);
    @SqlQuery(value = " SELECT\n" +
        "        od.id AS id,\n" +
        "        od.orderId AS orderId,\n" +
        "        od.productId AS productId,\n" +
        "        od.quantity AS quantity,\n" +
        "        od.total AS total,\n" +
        "        p.name AS productName,\n" +
        "        i.url AS imageUrl\n" + // Lấy thêm URL hình ảnh
        "    FROM order_detail od\n" +
        "    JOIN products p ON od.productId = p.id\n" +
        "    LEFT JOIN image i ON p.primaryImage = i.id\n" +
        "    WHERE od.orderId = :orderId")
@RegisterConstructorMapper(OrderDetail.class)
List<OrderDetail> getOrderDetailsByOrderId(@Bind("orderId") Integer orderId);


}