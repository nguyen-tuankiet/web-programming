package com.example.backend.controller.admin.category;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Category;
import com.example.backend.model.CategoryWithStock;
import com.example.backend.service.CategoryService;
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

@WebServlet(name = "CategoryController", urlPatterns = {"/admin/api/categories/*"})
public class CategoryController extends HttpServlet {

    private final CategoryService categoryService = new CategoryService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<CategoryWithStock> categories = categoryService.getCategoriesWithStock();
                ResponseWrapper<List<CategoryWithStock>> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Fetched categories with stock successfully", categories);
                writeResponse(response, responseWrapper);
            } else {
                // Lấy danh mục theo ID
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    Integer id = Integer.parseInt(pathParts[1]);
                    Category category = categoryService.getCategoryById(id);
                    if (category != null ) {
                            ResponseWrapper<Category> responseWrapper = new ResponseWrapper<>(
                                    200, "success", "Fetched category successfully", category);
                            writeResponse(response, responseWrapper);
                    } else {
                        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                                404, "error", "Category not found", null);
                        writeResponse(response, responseWrapper);
                    }
                } else {
                    ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                            400, "error", "Invalid request", null);
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
            // Đọc nội dung JSON từ body request
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }
            String jsonString = jsonBuilder.toString();

            // Parse JSON để lấy dữ liệu
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> jsonData = objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});

            // Lấy giá trị từ JSON
            String name = jsonData.get("name");



            // Kiểm tra giá trị của name
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }

            // Thêm category mới
            Category newCategory = categoryService.createCategory(name, true);

            // Phản hồi thành công
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    201, "success", "Category created successfully", newCategory);
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


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.split("/").length < 2) {
                throw new IllegalArgumentException("Invalid category ID");
            }

            Integer id = Integer.parseInt(pathInfo.split("/")[1]);

            // Đọc body để lấy trạng thái mới
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> body = mapper.readValue(sb.toString(), new TypeReference<>() {});

            Boolean isActive = (Boolean) body.get("isActive");
            if (isActive == null) {
                throw new IllegalArgumentException("Missing 'isActive'");
            }

            categoryService.updateCategoryStatus(id, isActive);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                throw new IllegalArgumentException("Category ID is required");
            }

            String[] pathParts = pathInfo.split("/");
            if (pathParts.length != 2) {
                throw new IllegalArgumentException("Invalid request");
            }

            Integer id = Integer.parseInt(pathParts[1]);

            // Lấy thông tin category trước khi xóa
            Category categoryToDelete = categoryService.getCategoryById(id);

            if (categoryToDelete == null) {
                throw new IllegalArgumentException("Category not found");
            }

            // Xóa category
            categoryService.deleteCategory(id);

            // Phản hồi thành công với thông tin của category đã xóa
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    200, "success", "Category deleted successfully", categoryToDelete);
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



}
