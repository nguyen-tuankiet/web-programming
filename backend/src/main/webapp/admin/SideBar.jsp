<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/11/25
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="body" class="row">
    <nav id="sidebar" class="col">
        <ul>
            <!-- Bảng điều khiển -->
            <li class="menu_item " onclick="loadPage('dashboard')">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-house"></i>
                    <span>Tổng quan</span>
                </div>
            </li>


            <!--Sản phẩm-->
            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-box"></i>
                    <span>Sản phẩm</span>
                    <i class="fa-solid fa-chevron-down toggle-arrow"></i>
                </div>

                <ul class="submenu">
                    <li class="submenu_item" onclick="loadPage('list_products')"> Danh sách sản phẩm</li>
                    <!--                        <li onclick="loadPage('product_details')" ><a href="#" >Chi tiết sản phẩm</a></li>-->
                    <li class="submenu_item" onclick="loadPage('add_product')"> Thêm sản phẩm</li>
                </ul>
            </li>

            <!-- Đơn hàng -->
            <li class="menu_item" onclick="loadPage('orders')">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <span>Đơn hàng</span>
                </div>

            </li>

            <!-- Khách hàng -->
            <li class="menu_item" onclick="loadPage('customers')">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-users"></i>
                    <span>Khách hàng</span>
                </div>

            </li>


            <!-- Hồ sơ -->
            <li class="menu_item" onclick="loadPage('my_profile')">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-user"></i>
                    <span>Hồ sơ</span>
                </div>
            </li>


            <!-- Cài đặt -->
            <li class="menu_item" onclick="loadPage('setting')">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-gear"></i>
                    <span>  Cài đặt </span>
                </div>
            </li>


        </ul>
    </nav>

    <div id="content">
        <iframe src="${pageContext.request.contextPath}/admin/Dashboard.jsp" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>
