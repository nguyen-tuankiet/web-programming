package com.example.backend.model.DAO;

import com.example.backend.contant.OrderStatus;
import com.example.backend.model.Order;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(Order.class)
public interface OrderDAO {

    @SqlUpdate(value = "INSERT INTO orders(createAt, paymentStatus, orderStatus, userId, addressId, cardId, isCOD, shippingFee)" +
            "VALUE (" +
            "  :createAt , :paymentStatus, :orderStatus , :userId, :addressId, :cardId, :isCOD, :shippingFee" +
            " )")

    @GetGeneratedKeys
    Integer createOrder(
            @Bind("createAt")LocalDate createAt,
            @Bind("paymentStatus") String paymentStatus,
            @Bind("orderStatus") OrderStatus orderStatus,
            @Bind("userId") Integer userId,
            @Bind("addressId") Integer addressId,
            @Bind("cardId") Integer cardId,
            @Bind("isCOD") Boolean isCOD,
            @Bind("shippingFee") Integer shippingFee
    );



    @SqlQuery(value ="select\n" +
            "    o.id , o.createAt, o.paymentStatus, o.orderStatus,\n" +
            "    o.userId, o.addressId, o.cardId, o.isCOD,  o.shippingFee as  shippingFee, " +
            "    sum(od.quantity) as quantity, (sum(od.total)+  o.shippingFee) as total,\n" +
            "    min(p.name) as productName,\n" +
            "    i.url as productImage\n" +
            "from orders as o inner join order_detail as od\n" +
            "        on o.id = od.orderId\n" +
            "    inner join products as p\n" +
            "        on p.id = od.productId\n" +
            "    inner join image as i\n" +
            "        on  i.id = p.primaryImage\n" +
            "where o.userId = :userId\n" +
            "group by\n" +
            "    o.id, o.createAt, o.paymentStatus, o.orderStatus,\n" +
            "    o.userId, o.addressId, o.cardId, o.isCOD, o.shippingFee " +
            "order by o.createAt desc  ;"

    )
    List<Order> getOrdersByUserId(@Bind("userId") Integer userId);


    @SqlQuery(value = "select\n" +
            "    o.id as id, o.createAt, o.paymentStatus, o.orderStatus,\n" +
            "    o.userId, o.addressId, o.cardId, o.isCOD,  o.shippingFee as  shippingFee, " +
            "    sum(od.total) as total\n" +
            "from orders as o inner join order_detail as od\n" +
            "                            on o.id = od.orderId\n" +
            "where o.userId = :userId and o.id = :orderId\n" +
            "group by\n" +
            "    o.id, o.createAt, o.paymentStatus, o.orderStatus,\n" +
            "    o.userId, o.addressId, o.cardId, o.isCOD, o.shippingFee " +
            " order by o.createAt DESC ")
        Order getOrderByIdAndUserId(@Bind("orderId") Integer orderId, @Bind("userId") Integer userId);


    @SqlQuery(value = "select\n" +
            "    o.id as id, o.createAt, o.paymentStatus, o.orderStatus,\n" +
            "    o.userId, o.addressId, o.cardId, o.isCOD,\n" +
            "    sum(od.total) as total\n" +
            "from orders as o inner join order_detail as od\n" +
            "                            on o.id = od.orderId\n" +
            "where o.id = :orderId\n" +
            "group by\n" +
            "    o.id, o.createAt, o.paymentStatus, o.orderStatus,\n" +
            "    o.userId, o.addressId, o.cardId, o.isCOD\n")
    Order getOrderById (@Bind("orderId") Integer orderId );






    @SqlQuery(value = "select\n" +
            "    o.id, o.createAt, o.paymentStatus,\n" +
            "    o.orderStatus, o.userId, o.addressId,\n" +
            "    o.cardId, o.isCOD,\n" +
            "    u.fullName as userName ,\n" +
            "    sum(od.total) as total\n" +
            "from orders as o\n" +
            "    inner join order_detail as od\n" +
            "        on o.id = od.orderId\n" +
            "    inner join user as u\n" +
            "        on u.id = o.userId\n" +
            "group by\n" +
            "    o.id, o.createAt, o.paymentStatus,\n" +
            "    o.orderStatus, o.userId, o.addressId,\n" +
            "    o.cardId, o.isCOD,\n" +
            "    u.fullName\n" +
            "order by o.createAt desc    ;\n" +
            "\n" +
            "\n")

    List<Order> getAllOrders();


}
