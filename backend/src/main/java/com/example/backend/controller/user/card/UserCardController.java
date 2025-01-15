package com.example.backend.controller.user.card;
import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Card;
import com.example.backend.model.User;
import com.example.backend.service.CardService;
import com.example.backend.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserCardController", value = "/user-card")
public class UserCardController extends HttpServlet {

    CardService cardService = new CardService(DBConnection.getJdbi());
    UserService userService = new UserService(DBConnection.getJdbi());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        List<Card> cards= cardService.getCartByUserId(userId);
        request.setAttribute("cards", cards);
        request.setAttribute("user", user);

        request.getRequestDispatcher("user/user-card.jsp").forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
