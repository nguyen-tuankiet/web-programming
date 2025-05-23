package com.example.backend.controller.admin.product;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Variant;
import com.example.backend.model.VariantValue;
import com.example.backend.service.VariantService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VariantController", urlPatterns = {"/admin/api/variants/*"})
public class VariantController extends HttpServlet {

    private final VariantService variantService = new VariantService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Lấy danh sách tất cả các variants
                String categoryIdParam = request.getParameter("categoryId");
                Integer categoryId = categoryIdParam != null ? Integer.valueOf(categoryIdParam) : null;

                // Gọi service để lấy danh sách variants
                List<Variant> variants = variantService.getVariantsByCategory(categoryId);
                ResponseWrapper<List<Variant>> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Fetched variants successfully", variants);
                writeResponse(response, responseWrapper);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2 && isNumeric(pathParts[1])) {
                    // Lấy danh sách các variant-values dựa trên variantId
                    Integer variantId = Integer.parseInt(pathParts[1]);
                    List<VariantValue> variantValues = variantService.getVariantValuesByVariantId(variantId);
                    if (variantValues != null && !variantValues.isEmpty()) {
                        ResponseWrapper<List<VariantValue>> responseWrapper = new ResponseWrapper<>(
                                200, "success", "Fetched variant values successfully", variantValues);
                        writeResponse(response, responseWrapper);
                    } else {
                        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                                404, "error", "No variant values found for the given variant ID", null);
                        writeResponse(response, responseWrapper);
                    }
                } else {
                    ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                            400, "error", "Invalid request format", null);
                    writeResponse(response, responseWrapper);
                }
            }
        } catch (Exception e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    500, "error", "Internal server error: " + e.getMessage(), null);
            writeResponse(response, responseWrapper);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }
            String jsonString = jsonBuilder.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonData = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});

            String name = (String) jsonData.get("name");
            Integer categoryId = (Integer) jsonData.get("categoryId");

            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }

            Variant newVariant = variantService.createVariant(name, categoryId);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    201, "success", "Variant created successfully", newVariant);
            writeResponse(response, responseWrapper);
        } catch (IllegalArgumentException e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    400, "error", e.getMessage(), null);
            writeResponse(response, responseWrapper);
        } catch (Exception e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    500, "error", "Internal server error: " + e.getMessage(), null);
            writeResponse(response, responseWrapper);
        }
    }

    private void writeResponse(HttpServletResponse response, ResponseWrapper<?> responseWrapper) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseWrapper.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
