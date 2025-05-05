package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Order;
import com.example.backend.service.ExcelExportService;
import com.example.backend.service.OrderSerivce;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrdersController", value = "/admin/orders")
public class AdminOrdersController extends HttpServlet {

    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());
    ExcelExportService excelExportService = new ExcelExportService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("export".equals(action)) {
            try {
                List<Order> orders = orderSerivce.getAllOrders();
                byte[] excelBytes = excelExportService.exportOrdersToExcel(orders);
                
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=orders.xlsx");
                response.getOutputStream().write(excelBytes);
                response.getOutputStream().flush();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error exporting orders to Excel");
                return;
            }
        }

        List<Order> orders = orderSerivce.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
