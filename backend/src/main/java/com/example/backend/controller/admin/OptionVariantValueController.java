package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.OptionVariantValue;
import com.example.backend.service.OptionVariantValueService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
@WebServlet("/admin/addOptionVariantValue")
public class OptionVariantValueController extends HttpServlet {

    private final OptionVariantValueService optionVariantValueService = new OptionVariantValueService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Đọc payload JSON từ request
            StringBuilder payload = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    payload.append(line);
                }
            }

            // Chuyển đổi JSON thành đối tượng Java
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestData = objectMapper.readValue(payload.toString(), Map.class);

            // Lấy giá trị từ JSON
            Integer optionId = (Integer) requestData.get("optionId");
            Object variantValueIdObj = requestData.get("variantValueId");

            Integer variantValueId = null;

            // Kiểm tra xem variantValueId là String hay Integer và chuyển đổi
            if (variantValueIdObj instanceof String) {
                variantValueId = Integer.parseInt((String) variantValueIdObj);
            } else if (variantValueIdObj instanceof Integer) {
                variantValueId = (Integer) variantValueIdObj;
            }

            int result = optionVariantValueService.addOptionVariantValue(optionId, variantValueId);

            // Lấy thông tin OptionVariantValue mới
            OptionVariantValue newOptionVariantValue = optionVariantValueService.getOptionById(result);

            // Trả về kết quả thành công với đối tượng OptionVariantValue
            ResponseWrapper<OptionVariantValue> responseWrapper;
            if (result > 0) {
                responseWrapper = new ResponseWrapper<>(200, "success", "OptionVariantValue added successfully.", newOptionVariantValue);
            } else {
                responseWrapper = new ResponseWrapper<>(500, "error", "Failed to add OptionVariantValue.", null);
            }

            writeResponse(response, responseWrapper);

        } catch (Exception e) {
            ResponseWrapper<String> errorWrapper = new ResponseWrapper<>(500, "error", "An error occurred: " + e.getMessage(), null);
            writeResponse(response, errorWrapper);
        }
    }

    // Phương thức để ghi phản hồi vào response
    private void writeResponse(HttpServletResponse response, ResponseWrapper<?> responseWrapper) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseWrapper.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
    }
}
