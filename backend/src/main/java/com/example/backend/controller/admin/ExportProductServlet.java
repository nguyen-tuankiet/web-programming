package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/export-products")
public class ExportProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService(DBConnection.getJdbi());

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Get all products
            List<Product> products = productService.getAllProducts();

            // Create Excel workbook
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Danh sách sản phẩm");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = {
                        "ID", "Tên sản phẩm", "SKU", "Mô tả",
                        "Danh mục", "Giá", "Số lượng",
                        "Lượt xem", "Lượt bán", "Trạng thái"
                };

                // Create header style
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);

                // Create header cells
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(headerStyle);
                }

                // Create data rows
                int rowNum = 1;
                for (Product product : products) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(product.getId());
                    row.createCell(1).setCellValue(product.getName());
                    row.createCell(2).setCellValue(product.getSku());
                    row.createCell(3).setCellValue(product.getDescription());
                    row.createCell(4).setCellValue(product.getCategoryName());
                    row.createCell(5).setCellValue(product.getPrice());
                    row.createCell(6).setCellValue(product.getStock());
                    row.createCell(7).setCellValue(product.getNoOfViews());
                    row.createCell(8).setCellValue(product.getNoOfSold());
                    row.createCell(9).setCellValue(product.getActive() ? "Hoạt động" : "Không hoạt động");
                }

                // Auto-size columns
                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                // Set response headers
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=danh_sach_san_pham.xlsx");

                // Write workbook to response
                workbook.write(response.getOutputStream());
            }
        }
}