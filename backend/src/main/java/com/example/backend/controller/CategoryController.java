package com.example.backend.controller;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Category;
import com.example.backend.service.CategoryService;
import com.example.backend.util.ResponseWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", urlPatterns = {"/api/categories/*"})
public class CategoryController extends HttpServlet {

    private final CategoryService categoryService = new CategoryService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Lấy danh sách tất cả danh mục
                List<Category> categories = categoryService.getAllCategories();
                ResponseWrapper<List<Category>> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Fetched categories successfully", categories);
                writeResponse(response, responseWrapper);
            } else {
                // Lấy danh mục theo ID
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    Integer id = Integer.parseInt(pathParts[1]);
                    Category category = categoryService.getCategoryById(id);
                    if (category != null) {
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
            String name = request.getParameter("name");
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }

            categoryService.createCategory(name);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    201, "success", "Category created successfully", null);
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String name = request.getParameter("name");

            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }

            categoryService.updateCategory(id, name);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    200, "success", "Category updated successfully", null);
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
            categoryService.deleteCategory(id);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    200, "success", "Category deleted successfully", null);
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
        response.getWriter().write(toJson(responseWrapper));
    }

    private String toJson(ResponseWrapper<?> responseWrapper) {
        StringBuilder jsonBuilder = new StringBuilder("{");
        jsonBuilder.append("\"statusCode\":").append(responseWrapper.getStatusCode()).append(",");
        jsonBuilder.append("\"status\":\"").append(responseWrapper.getStatus()).append("\",");
        jsonBuilder.append("\"message\":\"").append(responseWrapper.getMessage()).append("\",");
        jsonBuilder.append("\"data\":").append(responseWrapper.getData() != null ? responseWrapper.getData().toString() : "null");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
