<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 3/9/2025
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<%
    // Lấy từ khóa tìm kiếm từ URL
    String query = request.getParameter("query") != null ? request.getParameter("query") : "Tất cả sản phẩm";

    // Danh sách sản phẩm mẫu
    List<Map<String, String>> products = new ArrayList<>();

    // Thêm sản phẩm vào danh sách (Dữ liệu mẫu)
    String[][] sampleProducts = {
            {"1", "iPhone 16 Pro Max 256GB | Chính hãng VN/A", "31290000", "34990000", "11", "https://via.placeholder.com/150"},
            {"2", "iPhone 15 128GB | Chính hãng VN/A", "15990000", "22990000", "30", "https://via.placeholder.com/150"},
            {"3", "iPhone 16e 128GB | Chính hãng VN/A", "16490000", "16990000", "3", "https://via.placeholder.com/150"},
            {"4", "iPhone 13 128GB | Chính hãng VN/A", "11890000", "17290000", "31", "https://via.placeholder.com/150"},
            {"5", "iPhone 16 Pro 128GB | Chính hãng VN/A", "27990000", "31990000", "12", "https://via.placeholder.com/150"},
            {"6", "iPhone 16 128GB | Chính hãng VN/A", "21490000", "25490000", "16", "https://via.placeholder.com/150"},
            {"7", "iPhone 15 Plus 128GB | Chính hãng VN/A", "18990000", "24990000", "24", "https://via.placeholder.com/150"},
            {"8", "iPhone 14 128GB | Chính hãng VN/A", "13990000", "19990000", "30", "https://via.placeholder.com/150"}
    };

    for (String[] product : sampleProducts) {
        Map<String, String> prod = new HashMap<>();
        prod.put("id", product[0]);
        prod.put("name", product[1]);
        prod.put("price", product[2]);
        prod.put("oldPrice", product[3]);
        prod.put("discount", product[4]);
        prod.put("imageUrl", product[5]);
        products.add(prod);
    }
%>

<head>
    <title>Kết quả tìm kiếm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search-results.css">
</head>
<body>
<h2>Kết quả tìm kiếm cho: <%= query %></h2>

<div class="product-grid">
    <% for (Map<String, String> product : products) { %>
    <div class="product">
        <!-- Gắn nhãn giảm giá -->
        <div class="discount-label">Giảm <%= product.get("discount") %>%</div>

        <!-- Hình ảnh sản phẩm -->
        <img src="<%= product.get("imageUrl") %>" alt="<%= product.get("name") %>">

        <!-- Tên sản phẩm -->
        <p><%= product.get("name") %></p>

        <!-- Giá -->
        <div class="price-container">
            <span class="new-price"><%= product.get("price") %>đ</span>
            <span class="old-price"><%= product.get("oldPrice") %>đ</span>
        </div>

        <!-- Thông tin thêm -->
        <div class="extra-info">
            <button class="installment-btn">Trả góp 0%</button>
            <span class="favorite">❤️ 8  3</span>
        </div>
    </div>
    <% } %>
</div>
</body>
