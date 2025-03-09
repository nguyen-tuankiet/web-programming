package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.OptionVariantValue;
import com.example.backend.service.OptionVariantValueService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/addOptionVariantValue")
public class OptionVariantValueController extends HttpServlet {

    private final OptionVariantValueService optionVariantValueService = new OptionVariantValueService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!"application/json".equals(request.getContentType())) {
                writeResponse(response, new ResponseWrapper<>(400, "error", "Invalid Content-Type, expected application/json", null));
                return;
            }

            StringBuilder payload = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    payload.append(line);
                }
            }
            System.out.println("Received payload: " + payload.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> requestDataList;
            try {
                requestDataList = objectMapper.readValue(payload.toString(), new TypeReference<>() {});
            } catch (Exception e) {
                writeResponse(response, new ResponseWrapper<>(400, "error", "Invalid JSON format", null));
                return;
            }

            List<OptionVariantValue> addedValues = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Map<String, Object> requestData : requestDataList) {
                try {
                    Integer optionId = getIntegerValue(requestData.get("optionId"));
                    Integer variantValueId = getIntegerValue(requestData.get("variantValueId"));

                    if (optionId == null || variantValueId == null) {
                        errors.add("Invalid optionId or variantValueId for item: " + requestData);
                        continue;
                    }

                    System.out.println("Inserting optionId: " + optionId + ", variantValueId: " + variantValueId);
                    int result = optionVariantValueService.addOptionVariantValue(optionId, variantValueId);

                    if (result > 0) {
                        OptionVariantValue newOptionVariantValue = optionVariantValueService.getOptionById(result);
                        if (newOptionVariantValue != null) {
                            addedValues.add(newOptionVariantValue);
                        }
                    } else {
                        errors.add("Failed to add OptionVariantValue for: " + requestData);
                    }
                } catch (Exception e) {
                    errors.add("Error processing item " + requestData + ": " + e.getMessage());
                }
            }

            ResponseWrapper responseWrapper;
            if (!addedValues.isEmpty()) {
                responseWrapper = new ResponseWrapper<>(200, "success",
                        errors.isEmpty() ? "All OptionVariantValues added successfully." : "Some OptionVariantValues added with errors: " + String.join(", ", errors),
                        addedValues);
            } else {
                responseWrapper = new ResponseWrapper<>(500, "error", "Failed to add any OptionVariantValues. Errors: " + String.join(", ", errors), null);
            }

            writeResponse(response, responseWrapper);

        } catch (Exception e) {
            writeResponse(response, new ResponseWrapper<>(500, "error", "An error occurred: " + e.getMessage(), null));
        }
    }

    private Integer getIntegerValue(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (value instanceof Long) {
            return ((Long) value).intValue();
        } else if (value instanceof Double) {
            return ((Double) value).intValue();
        }
        return null;
    }

    private void writeResponse(HttpServletResponse response, ResponseWrapper responseWrapper) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseWrapper.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
    }
}