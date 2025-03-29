package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Tag;
import com.example.backend.service.TagService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/tag")
public class TagPageController extends HttpServlet {
    private final TagService tagService = new TagService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tag> tags = tagService.getAllTags();
        request.setAttribute("tags", tags);
        request.getRequestDispatcher("/admin/tags.jsp").forward(request, response);
    }
}
