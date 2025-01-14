package com.example.backend.model.DAO;

import com.example.backend.model.OrderDetail;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(OrderDetail.class)
public interface OrderDetailDAO {

    @SqlUpdate(value = "INSERT INTO order_detail (orderId, productId, quantity, total, optionId) " +
            "VALUES (:orderId, :productId, :quantity, :total, :optionId)")
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

    @SqlQuery(value = "SELECT " +
            "od.id AS id, " +
            "od.orderId AS orderId, " +
            "od.productId AS productId, " +
            "od.quantity AS quantity, " +
            "od.total AS total, " +
            "p.name AS productName, " +
            "i.url AS imageUrl " + // Lấy thêm URL hình ảnh
            "FROM order_detail od " +
            "JOIN products p ON od.productId = p.id " +
            "LEFT JOIN image i ON p.primaryImage = i.id " +
            "WHERE od.orderId = :orderId")
    OrderDetail getOrderDetailById(@Bind("orderId") Integer orderId);

    @SqlQuery(value ="\n" +
            "select\n" +
            "    od.id, od.orderId, od.productId, od.quantity,\n" +
            "    od.total,\n" +
            "    p.name as productName,\n" +
            "    i.url as imageUrl\n" +
            "from order_detail as od\n" +
            "    inner join products as p\n" +
            "        on p.id = od.productId\n" +
            "    inner join options as ops\n" +
            "        on od.optionId = ops.id\n" +
            "    inner join image as i\n" +
            "        on i.id = p.primaryImage\n" +
            "\n" +
            "where od.orderId = :orderId \n"
    )
    List<OrderDetail> getOrderDetailByOrderId(@Bind("orderId") Integer orderId );
}
