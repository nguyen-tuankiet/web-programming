package com.example.backend.controller.user.address;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.service.AddressSevice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException; 
@WebServlet(name = "AddAddressController", value = "/AddAddressController") 
public class AddAddressController extends HttpServlet { 

@Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            String province = request.getParameter("province");
            String district = request.getParameter("district");
            String commune = request.getParameter("commune");
            String detail = request.getParameter("detail");
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String type = request.getParameter("type");

            // Khởi tạo Address
            Address newAddress = new Address(
                    null, userId, province, district, commune, detail, phone, name, false, type
            );

            // Thêm vào cơ sở dữ liệu
            AddressSevice addressService = new AddressSevice(DBConnection.getJdbi());
            int resultId = addressService.addAddress(newAddress);

            if (resultId > 0) {
                response.sendRedirect("user-address"); // Điều hướng về trang danh sách địa chỉ
            } else {
                response.getWriter().println("Thêm địa chỉ thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi khi thêm địa chỉ.");
        }
    }


}