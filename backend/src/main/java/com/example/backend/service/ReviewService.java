package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.OrderStatus;
import com.example.backend.model.DAO.ReivewDAO;
import com.example.backend.model.Review;
import org.jdbi.v3.core.Jdbi;

public class ReviewService {
    Jdbi jdbi;
    ReivewDAO reivewDAO;

    public ReviewService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.reivewDAO = jdbi.onDemand(ReivewDAO.class);
    }

    public boolean isOrderDelivered(int orderId, int userId) {
        OrderStatus orderStatus = reivewDAO.getOrderStatus(orderId, userId);
        return orderStatus != null && orderStatus == OrderStatus.DELIVERED;
    }


    public Boolean addReview(Review review) {
        int existing = reivewDAO.countExistingReview(
                review.getUserId(),
                review.getOrderId(),
                review.getProductId()
        );

        if (existing > 0) {
            System.out.println(" Review đã tồn tại");
            return false;
        }

        boolean success = reivewDAO.addReview(
                review.getUserId(),
                review.getProductId(),
                review.getOrderId(),
                review.getRating(),
                review.getDescription()
        );

        if (success) {
            reivewDAO.updateIsReviewed(review.getOrderId(), review.getProductId());
        }

        return success;
    }

    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService(DBConnection.getJdbi());
        Review review = new Review();
        review.setDescription("Test Review");
        review.setOrderId(39);
        review.setRating(5);
        review.setUserId(111);
        review.setProductId(1);

        System.out.println(reviewService.addReview(review));
    }
}
