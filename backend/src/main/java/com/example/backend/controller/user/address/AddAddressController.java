package com.example.backend.controller.user.address;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.service.AddressSevice;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddAddressController", value = "/add-address")
public class AddAddressController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddAddressController.class);
    AddressSevice addressService = new AddressSevice(DBConnection.getJdbi());


    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StringBuilder jsonBuffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
            String jsonData = jsonBuffer.toString();
            JSONObject jsonObject = new JSONObject(jsonData);


            Integer userId = jsonObject.has("userId") ? jsonObject.getInt("userId") : null;
            String province = jsonObject.has("province") ? jsonObject.getString("province") : null;
            Integer provinceId = jsonObject.has("provinceId") ? jsonObject.getInt("provinceId") : null;
            String district = jsonObject.has("district") ? jsonObject.getString("district") : null;
            Integer districtId = jsonObject.has("districtId") ? jsonObject.getInt("districtId") : null;
            String commune = jsonObject.has("commune") ? jsonObject.getString("commune") : null;
            Integer communeId = jsonObject.has("communeId") ? jsonObject.getInt("communeId") : null;
            String detail = jsonObject.has("detail") ? jsonObject.getString("detail") : null;
            String phone = jsonObject.has("phone") ? jsonObject.getString("phone") : null;
            String name = jsonObject.has("name") ? jsonObject.getString("name") : null;
            boolean isDefault = jsonObject.has("isDefault") ? jsonObject.getBoolean("isDefault") : false;
            String type = jsonObject.has("type") ? jsonObject.getString("type") : null;


            Address newAddress = new Address(null, userId, province, provinceId, district, districtId, commune,communeId, detail, phone, name, isDefault, type, null);
            if (isDefault) {
               Address defautl = addressService.findDefautlByUserId(userId);
               if (defautl != null) {
                   defautl.setIsDefault(false);
                   addressService.updateDefautlById(defautl.getId(), false);
               }

            }

            log.info("New address : {}", newAddress);


            int resultId = addressService.addAddress(newAddress);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (resultId > 0) {
                newAddress.setId(resultId);

                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Thêm địa chỉ thành công!");

                JSONObject addressJson = new JSONObject();
                addressJson.put("id", newAddress.getId());
                addressJson.put("userId", newAddress.getUserId());
                addressJson.put("province", newAddress.getProvince());
                addressJson.put("district", newAddress.getDistrict());
                addressJson.put("commune", newAddress.getCommune());
                addressJson.put("detail", newAddress.getDetail());
                addressJson.put("phone", newAddress.getPhone());
                addressJson.put("name", newAddress.getName());
                addressJson.put("isDefault", newAddress.getIsDefault());
                addressJson.put("type", newAddress.getType());

                jsonResponse.put("address", addressJson);

                response.getWriter().write(jsonResponse.toString());
             } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Thêm địa chỉ thất bại.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            response.getWriter().println("Lỗi khi thêm địa chỉ.");
        }
    }


}