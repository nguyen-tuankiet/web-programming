package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OrderDetailDAO;
import com.example.backend.model.Options;
import com.example.backend.model.OrderDetail;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class OrderDetailService {
    Jdbi jdbi;
    OrderDetailDAO orderDetailDAO;
    OptionService optionService = new OptionService(DBConnection.getJdbi());
    public OrderDetailService(Jdbi jdbi) {
        orderDetailDAO = jdbi.onDemand(OrderDetailDAO.class);
    }


    public Boolean addOrderDetail(OrderDetail orderDetail) {

        Options options = optionService.getOptionById(orderDetail.getOptionId());
        if (options == null || (options.getStock() < orderDetail.getQuantity())) {
            throw new RuntimeException("Bad request");
        }
        else {

            Boolean flag = orderDetailDAO.addOrderDetail(
                    orderDetail.getOrderId(),
                    orderDetail.getProductId(),
                    orderDetail.getQuantity(),
                    orderDetail.getTotal(),
                    orderDetail.getOptionId()
            );

            if (flag) {
                Integer newStock = options.getStock() - orderDetail.getQuantity() ;
                optionService.updateStock(orderDetail.getOptionId(), newStock);
            }

            return flag;
        }


    }
    public String getProductNameById(Integer productId) {
        return orderDetailDAO.getProductNameById(productId);
    }

    public Integer getQuantityByOrderDetailId(Integer orderDetailId) {
        return orderDetailDAO.getQuantityByOrderDetailId(orderDetailId);
    }

    public String getOrderStatusByOrderId(Integer orderId) {
        return orderDetailDAO.getOrderStatusByOrderId(orderId);
    }

    public OrderDetail getOrderDetailsByOrderId(Integer orderId) {
        return orderDetailDAO.getOrderDetailById(orderId);
    }



    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
//        System.out.println(orderDetailService.getOrderDetailsByOrderId(1));
    }

}