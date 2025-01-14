package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.ReivewDAO;
import com.example.backend.model.Review;
import org.jdbi.v3.core.Jdbi;

public class ReviewService {
    Jdbi jdbi;
    ReivewDAO reivewDAO;


    public ReviewService(Jdbi jdbi) {
        reivewDAO = jdbi.onDemand(ReivewDAO.class);
    }



    public Boolean addReview(Review review) {
        return reivewDAO.addReview(review.getUserId(),
                                review.getProductId(),
                                review.getOrderId(),
                                review.getRating(),
                                review.getDescription()
        );
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
