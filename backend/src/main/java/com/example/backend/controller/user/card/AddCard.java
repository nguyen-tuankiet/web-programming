package com.example.backend.controller.user.card;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Card;
import com.example.backend.service.CardService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AddCard", value = "/add-card")
public class AddCard extends HttpServlet {

    CardService cardService = new CardService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // Parse JSON
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(sb.toString(), JsonObject.class);

        // Lấy dữ liệu
        String name = json.get("name").getAsString();
        String cardNumber = json.get("cardNumber").getAsString();
        String last4Digits = cardNumber.substring(cardNumber.length() - 4);
        Integer last4 = Integer.parseInt(last4Digits);
        String expiry = json.get("expiry").getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate expiryDate = LocalDate.parse("01/" + expiry, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String cvv = json.get("cvv").getAsString();
        boolean primaryCard = json.get("primaryCard").getAsBoolean();

        HttpSession session = request.getSession();
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());



        Card card = new Card();
        card.setDuration(expiryDate);
        card.setType("Visa");
        card.setUserId(userId);
        if (primaryCard) {
            card.setDefault(true);
        }
        else {
            card.setDefault(false);
        }
        card.setLast4(last4);

        cardService.addCard(card);



        // Trả về JSON phản hồi
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", true);
        responseJson.addProperty("message", "Thẻ đã được thêm thành công!");

        response.getWriter().write(responseJson.toString());
    }

}
