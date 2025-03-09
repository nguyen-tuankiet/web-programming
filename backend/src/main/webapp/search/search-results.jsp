<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 3/9/2025
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Kết quả tìm kiếm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search-results.css">
</head>
<body>

<div class="search_header">
    <jsp:include page="../home/header.jsp"/>
</div>

<div id="list_product">
    <%-- Hàng sản phẩm 1 --%>
    <div class="product-row">
        <div class="search_body">
            <div class="wrap_img">
                <img src="${pageContext.request.contextPath}/static/image/tulanh1.jpeg" alt="Tủ lạnh 1" />
            </div>
            <div class="infor col">
                <div id="name"><a href="#">636 L Tủ Lạnh Bespoke 4 Cửa - AI Family Hub™</a></div>
                <div id="price">
                    <span class="new-price">92.000.000 VND</span>
                    <span class="old-price">99.000.000 VND</span>
                </div>
                <ul class="list_descriptions">
                    <li>Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                    <li>Làm đá tự động nhanh chóng, tiện lợi</li>
                    <li>Công nghệ làm lạnh vòm All Around Cooling</li>
                </ul>
            </div>
            <div class="operation">
                <button class="buy-now">Mua ngay</button>
                <button class="btn add">Thêm vào giỏ hàng</button>
            </div>
        </div>

        <div class="search_body">
            <div class="wrap_img">
                <img src="${pageContext.request.contextPath}/static/image/tulanh2.jpeg" alt="Tủ lạnh 2" />
            </div>
            <div class="infor col">
                <div id="name1"><a href="#">Tủ lạnh LG French Door mặt gương 612L</a></div>
                <div id="price1">
                    <span class="new-price">36.000.000 VND</span>
                    <span class="old-price">40.000.000 VND</span>
                </div>
                <ul class="list_descriptions">
                    <li>Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                    <li>Làm đá tự động nhanh chóng, tiện lợi</li>
                    <li>Công nghệ làm lạnh vòm All Around Cooling</li>
                </ul>
            </div>
            <div class="operation">
                <button class="buy-now">Mua ngay</button>
                <button class="btn add">Thêm vào giỏ hàng</button>
            </div>
        </div>

        <div class="search_body">
            <div class="wrap_img">
                <img src="${pageContext.request.contextPath}/static/image/tulanh3.jpeg" alt="Tủ lạnh 3" />
            </div>
            <div class="infor col">
                <div id="name2"><a href="#">Tủ lạnh ngăn đá dưới 340L InstaView™</a></div>
                <div id="price2">
                    <span class="new-price">19.500.000 VND</span>
                    <span class="old-price">22.000.000 VND</span>
                </div>
                <ul class="list_descriptions">
                    <li>Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                    <li>Làm đá tự động nhanh chóng, tiện lợi</li>
                    <li>Công nghệ làm lạnh vòm All Around Cooling</li>
                </ul>
            </div>
            <div class="operation">
                <button class="buy-now">Mua ngay</button>
                <button class="btn add">Thêm vào giỏ hàng</button>
            </div>
        </div>
    </div>
</div>

</body>
