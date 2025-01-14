package com.example.backend.controller.review;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Review;
import com.example.backend.service.ReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "AddReviewController", value = "/add-review")
public class AddReviewController extends HttpServlet {

    ReviewService reviewService = new ReviewService(DBConnection.getJdbi());



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        Boolean flag = true;
        HttpSession session = request.getSession();

        Integer user_id = (Integer) session.getAttribute("userId");


        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);

        }

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());

        Integer order_id = (Integer) jsonObject.get("order_id");
        Integer rating = (Integer) jsonObject.getInt("rating");
        String description = (String) jsonObject.get("description");


        Review review = new Review();
        review.setDescription(description);
        review.setRating(rating);
        review.setOrderId(order_id);
        review.setUserId(user_id);

        JSONArray productIds = (JSONArray) jsonObject.get("productIds");

        for (int i = 0; i < productIds.length(); i++) {
             Integer id =(Integer) productIds.getInt(i);
             review.setProductId(id);
             if (!reviewService.addReview(review)){
                 flag = false;
             }

        }

        JSONObject responseJson = new JSONObject();
        if (flag) {
            responseJson.put("status", "success");
            responseJson.put("message", "All reviews added successfully.");
        } else {
            responseJson.put("status", "error");
            responseJson.put("message", "Failed to add all reviews.");
        }

        // Thiết lập kiểu nội dung và trả về phản hồi
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseJson.toString());


    }
}
