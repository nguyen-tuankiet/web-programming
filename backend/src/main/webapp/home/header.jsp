<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/25/24
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.backend.model.DAO.cart.Cart" %>
<% Cart cart = (Cart) session.getAttribute("cart");
   int initialCartCount = (cart != null) ? cart.getProducts().size() : 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-page/home/Home.css"/>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search.css">--%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        const initialCartCount = <%= ((com.example.backend.model.DAO.cart.Cart) session.getAttribute("cart") != null) ? ((com.example.backend.model.DAO.cart.Cart) session.getAttribute("cart")).getProducts().size() : 0 %>;

        document.addEventListener("DOMContentLoaded", function() {
            // Gọi hàm updateCartCount từ home.js (đảm bảo home.js được load trước script này)
            if (typeof updateCartCount === 'function') {
                 updateCartCount(initialCartCount);
            } else {
                 console.error("Hàm updateCartCount chưa được định nghĩa.");
            }
        });
    </script>
</head>
<body>


<div id="main_header">


    <a class="logo nav_item" id="logo" href="home">
        <img class="logo" src="${pageContext.request.contextPath}/static/image/logo_web.png"/>
    </a>


    <nav class="navbar">
        <ul>
            <li class="active">
                <a href="#">Tủ Lạnh

                </a>
                <div class="submenu submenu-tu-lanh">
                    <div class="submenu-column">
                        <%--                        <a class="nav_item" href="search-refrigerator" >Tất cả Tủ Lạnh</a>--%>
                        <a class="nav_item" href="search-category?categoryId=1">Tất cả Tủ Lạnh</a>


                        <a class="nav_item" href="#">Tủ Lạnh BESPOKE <span class="highlight">HẤP DẪN</span></a>
                        <a href="">Tủ Lạnh Multidoor</a>
                        <a href="#">Tủ Lạnh Family Hub</a>
                        <a href="#">Tủ Lạnh Side by Side</a>
                        <a href="#">Tủ Lạnh 2 Cửa Ngăn Đông Dưới</a>
                        <a href="#"
                        >Tủ Lạnh 2 Cửa Ngăn Đông Trên
                            <span class="highlight">HẤP DẪN</span></a>
                        <a href="#">Phụ Kiện BESPOKE</a>
                        <a href="#">Khám phá Tủ Lạnh</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="#">Máy Giặt</a>
                <div class="submenu submenu-may-giat">
                    <div class="submenu-column">
                        <%--                        <a class="nav_item" href="search-washing-machine" >Tất cả Máy Giặt</a>--%>
                        <a class="nav_item" href="search-category?categoryId=2">Tất cả Máy Giặt</a>

                        <a href="#">Tiết kiệm sống xanh với BESPOKE AI</a>
                        <a href="#"
                        >Máy giặt thông minh Bespoke AI
                            <span class="new">MỚI</span></a
                        >
                        <a href="#"
                        >Máy giặt cửa trên Ecobubble<span class="highlight"
                        >HẤP DẪN</span
                        ></a
                        >
                        <a href="#">Máy giặt cửa trước</a>
                        <a href="#">Máy giặt cửa trên</a>
                        <a href="#">Máy giặt kèm sấy</a>
                        <a href="#">Máy sấy</a>
                        <a href="#">Tủ chăm sóc quần áo thông minh</a>
                        <a href="#">Phụ kiện máy giặt & máy sấy</a>
                        <a href="#">Khám phá máy giặt</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="#">Máy Hút Bụi</a>
                <div class="submenu submenu-hut-bui">
                    <div class="submenu-column">
                        <%--                        <a--%>
                        <%--                                class="nav_item"--%>
                        <%--                                data-src="/web-programming/frontEnd/src/component/product_component/Search_VacuumCleaner.html"--%>
                        <%--                                href="#"--%>
                        <%--                        >Tất cả Máy Hút Bụi</a--%>
                        <%--                        >--%>
                        <%--                        <a class="nav_item" href="search-vacuum-cleaner" >Tất cả Máy Hút Bụi</a>--%>
                        <a class="nav_item" href="search-category?categoryId=3">Tất cả Hút Bụi</a>


                        <a href="#">Máy Hút Bụi Không Dây</a>
                        <a href="#">Robot Hút Bụi <span class="new">MỚI</span></a>
                        <a href="#">Máy Hút Bụi Dạng Hộp</a>
                        <a href="#">Khám phá Máy Hút Bụi</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="#">Thiết Bị Nhà Bếp</a>
                <div class="submenu submenu-nha-bep">
                    <div class="submenu-column">
                        <%--                        <a class="nav_item" href="search-kitchen-equipment" >Tất cả Thiết Bị Nhà Bếp</a>--%>
                        <a class="nav_item" href="search-category?categoryId=6">Tất cả Thiết Bị Nhà Bếp</a>
                        <a href="#">Lò Vi Sóng <span class="new">MỚI</span></a>
                        <a href="#">Lò Nướng </a>
                        <a href="#">Bếp Từ </a>
                        <a href="#">Máy Hút Mùi <span class="new">MỚI</span></a>
                        <a href="#">Máy Rửa Bát<span class="new">MỚI</span></a>
                        <a href="#">Khám phá Thiết Bị Bếp</a>
                    </div>
                </div>
            </li>
            <li class="active">
                <a href="#">Máy Lọc Không Khí</a>
                <div class="submenu submenu-may-loc">
                    <div class="submenu-column">
                        <%--                        <a class="nav_item" href="search-air-purifier" >Máy Lọc Không Khí</a>--%>
                        <a class="nav_item" href="search-category?categoryId=4">Máy Lọc Không Khí</a>
                        <a href="#">Bộ Sưu Tập Máy Lọc Không Khí</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="#">Điều Hòa Không Khí</a>
                <div class="submenu submenu-dieu-hoa">
                    <div class="submenu-column">
                        <%--                        <a class="nav_item" href="search-air-conditioner" >Tất cả Máy Điều Hòa</a>--%>
                        <a class="nav_item" href="search-category?categoryId=5">Tất cả Máy Điều Hòa</a>

                        <a href="#">Điều Hòa Treo Tường</a>
                        <a href="#">Điều Hòa Cục Bộ Thương Mại</a>
                        <a href="#">Khám phá Điều hòa WindFree</a>
                    </div>
                </div>
            </li>
            <li><a href="#">Phụ kiện</a></li>
            <li>
                <%--                <a href="#">SmartThings</a>--%>
                <%--                <a class="nav_item" href="Search_SmartThings" >SmartThings</a>--%>
                <a class="nav_item" href="search-category?categoryId=5">SmartThings</a>

            </li>
            <li>
                <a href="#">Hỗ Trợ</a>
                <div class="submenu submenu-ho-tro">
                    <div class="submenu-column">
                        <a href="#">Trung Tâm Hỗ Trợ</a>
                        <a href="#">Bảo Hành</a>
                        <a href="#">Trợ Giúp Kỹ Thuật</a>
                        <a href="#">Câu Hỏi Thường Gặp</a>
                    </div>
                </div>
            </li>
        </ul>
        <!-- User Login Icon and Popup -->
        <div class="icons">
            <a href="#" class="icon" id="search-icon" onclick="showSearchOverlay()">
                <i class="fas fa-search"></i>
            </a>


            <a class="nav_item icon"
               href="cart" id="cart-link"><i class="fas fa-shopping-cart"></i>
<%--            <span class="cart-count" id="cart-count">0</span>--%>
            </a>

            <div class="icon user-login" target="_top">
                <i class="fas fa-user"></i>
                <div class="user-popup">
                    <a class="nav_item" href="user-profile" id="my-page-link">Trang của tôi</a>
                    <a href="login" id="login-link">Đăng nhập/Đăng ký</a>
                </div>
            </div>
        </div>



        <div id="search-overlay" class="layer">


            <div class="search-container">
                <h2 class="search-container-title">Chúng tôi có thể giúp bạn tìm kiếm?</h2>
                <div class="search-bar">
                    <input type="text" id="search-input" placeholder="Nhập từ khoá ..."/>
                    <button class="search-icon" onclick="performSearch()">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </div>

                <!-- Dropdown gợi ý -->
                <div class="suggestion-box" id="suggestion-box">
                    <ul id="suggestion-list"></ul>


                    <h3 class="suggestion-title">Gợi ý dành cho bạn</h3>
                    <div class="product-suggestions">


                        <div class="product">

                        </div>
                    </div>
                </div>


                <div id="search-content"></div>


                <button id="close-search-overlay" class="close-btn">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        </div>


    </nav>

</div>


<script src="${pageContext.request.contextPath}/static/style-page/home/home.js"></script>
<script src="${pageContext.request.contextPath}/static/style-component/style-home/search.js"></script>


</body>
</html>