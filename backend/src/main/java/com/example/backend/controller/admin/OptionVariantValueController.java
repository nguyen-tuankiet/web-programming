package com.example.backend.controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OptionVariantValueDAO;
import com.example.backend.model.OptionVariantValue;
import com.example.backend.service.OptionVariantValueService;
import com.example.backend.util.ResponseWrapper;
import org.jdbi.v3.core.Jdbi;

@WebServlet("/addOptionVariantValue")
public class OptionVariantValueController extends HttpServlet {

    private final OptionVariantValueService optionVariantValueService = new OptionVariantValueService(DBConnection.getJdbi());



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer optionId = Integer.parseInt(request.getParameter("optionId"));
            Integer variantValueId = Integer.parseInt(request.getParameter("variantValueId"));

            OptionVariantValue optionVariantValue = new OptionVariantValue(optionId, variantValueId);
            int result = optionVariantValueService.addOptionVariantValue(optionVariantValue);

            ResponseWrapper<String> responseWrapper;
            if (result > 0) {
                responseWrapper = new ResponseWrapper<>(200, "success", "OptionVariantValue added successfully.", null);
            } else {
                responseWrapper = new ResponseWrapper<>(500, "error", "Failed to add OptionVariantValue.", null);
            }

            response.setContentType("application/json");
            response.getWriter().write(responseWrapper.toJson());
        } catch (Exception e) {
            ResponseWrapper<String> errorWrapper = new ResponseWrapper<>(500, "error", "An error occurred: " + e.getMessage(), null);
            response.setContentType("application/json");
            response.getWriter().write(errorWrapper.toJson());
        }
    }
}
