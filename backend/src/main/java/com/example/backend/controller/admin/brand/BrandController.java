package com.example.backend.controller.admin.brand;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Brand;
import com.example.backend.service.BrandService;
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
import java.util.stream.Collectors;

@WebServlet(name = "BrandController", urlPatterns = {"/admin/api/brand/*"})
public class BrandController extends HttpServlet {
    private final BrandService brandService = new BrandService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Brand> brands = brandService.getAllBrands();
                ResponseWrapper<List<Brand>> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Fetched brands successfully", brands);
                writeResponse(response, responseWrapper);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    Integer id = Integer.parseInt(pathParts[1]);
                    Brand brand = brandService.getBrandById(id);
                    if (brand != null) {
                        ResponseWrapper<Brand> responseWrapper = new ResponseWrapper<>(
                                200, "success", "Fetched brand successfully", brand);
                        writeResponse(response, responseWrapper);
                    } else {
                        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                                404, "error", "Brand not found", null);
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
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonData = objectMapper.readValue(jsonBuilder.toString(), new TypeReference<>() {});

            String name = (String) jsonData.get("name");
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên nhà sản xuất không được để trống");
            }

            boolean isActive = jsonData.get("isActive") != null && (Boolean) jsonData.get("isActive");

            Brand newBrand = brandService.createBrand(name, isActive);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    201, "success", "Brand created successfully", newBrand);
            writeResponse(response, responseWrapper);

        } catch (IllegalArgumentException e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(400, "error", e.getMessage(), null);
            writeResponse(response, responseWrapper);
        } catch (Exception e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(500, "error", "Internal server error: " + e.getMessage(), null);
            writeResponse(response, responseWrapper);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"ID is required\"}");
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Invalid path\"}");
            return;
        }

        Integer id = Integer.parseInt(pathParts[1]);
        BufferedReader reader = request.getReader();
        String json = reader.lines().collect(Collectors.joining());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonData = objectMapper.readValue(json, new TypeReference<>() {});

        if (jsonData.containsKey("isActive")) {
            boolean isActive = (Boolean) jsonData.get("isActive");
            brandService.toggleBrandStatus(id, isActive);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"success\"}");
            return;
        }

        if (jsonData.containsKey("name")) {
            String name = (String) jsonData.get("name");
            brandService.updateBrand(id, name);
            Brand updatedBrand = brandService.getBrandById(id);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(200, "success", "Updated brand", updatedBrand);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Missing data\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                throw new IllegalArgumentException("Brand ID is required");
            }

            String[] pathParts = pathInfo.split("/");
            if (pathParts.length != 2) {
                throw new IllegalArgumentException("Invalid request");
            }

            Integer id = Integer.parseInt(pathParts[1]);
            Brand brand = brandService.getBrandById(id);
            if (brand == null) {
                throw new IllegalArgumentException("Brand not found");
            }

            brandService.deleteBrand(id);

            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    200, "success", "Brand deleted successfully", brand);
            writeResponse(response, responseWrapper);
        } catch (IllegalArgumentException e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(400, "error", e.getMessage(), null);
            writeResponse(response, responseWrapper);
        } catch (Exception e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(500, "error", "Internal server error: " + e.getMessage(), null);
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
