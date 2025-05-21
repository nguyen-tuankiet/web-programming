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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <style>
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
        }


        /* Utility classes */
        .mid_align {
            display: flex;
            align-items: center;
            text-align: center;
        }

        .row {
            display: flex;
            flex-direction: row;
        }

        .col {
            display: flex;
            flex-direction: column;
        }

        .menu_item button {
            background: none;
            border: none;
            color: #4b5563;
            font-size: 16px;
            font-family: inherit;
            cursor: pointer;
            padding: 0;
            text-align: left;
        }

        .menu_item button:hover {
            color: #2563eb;
        }


        .rec_horizontal {
            width: 100%;
            height: 1px;
            background-color: #e0e0e0;
            margin-top: 20px;
        }

        .rec_vertical {
            height: 70%;
            width: 1px;
            background-color: #e0e0e0;
            margin: 0 10px;
            min-height: 20px;
        }

        i {
            color: #9ca3af; /* Default gray for icons */
        }

        a {
            text-decoration: none;
            color: #111827; /* Dark text color for links */
        }

        .bold {
            font-weight: 500;
        }


        #body {
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            background-color: #f9fafb;
        }

        #sidebar {
            width: 270px;
            height: 100%;
            background-color: #ffffff; /* Thay màu trắng */
            border-right: 1px solid #e5e7eb;

            padding-bottom: 250px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.05); /* Hiệu ứng shadow nhẹ */
            position: fixed;
        }

        #sidebar ul {
            list-style-type: none;

            margin: 5px;
        }

        .menu_item {

            align-items: center;
            padding: 12px 20px;
            color: #4b5563;
            font-weight: 500;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
            position: relative;
            border-radius: 5px;
            gap: 15px;
        }

        .menu_item:hover {
            background-color: #f3f4f6;
            color: #267cff;
        }


        .menu_item:hover .submenu li {
            background-color: transparent; /* Không thay đổi nền của submenu */
            color: #4b5563; /* Giữ nguyên màu chữ của submenu */
        }


        .menu_item .wrap_menu_item {
            display: flex;
        }

        .menu_item i {
            font-size: 18px;
            margin-right: 20px;
            color: #9ca3af;
            transition: color 0.3s;
            height: 20px;
            width: 20px;
            align-items: center;
            text-align: center;
        }


        .menu_item:hover i {
            color: #2563eb;
        }

        .menu_item:hover a {
            color: #2563eb;
        }


        .menu_item .toggle-arrow {
            font-size: 14px;
            transition: transform 0.3s, color 0.3s;
            color: #9ca3af;
            margin-left: auto;

        }

        .menu_item.active .toggle-arrow {
            transform: rotate(180deg);
            color: #2563eb;
        }


        .submenu {
            display: none;
            flex-direction: column;
            padding-left: 25px; /* Đẩy submenu vào trong một chút */
            margin-top: 5px;
        }

        .menu_item.active .submenu {
            display: flex;
            color: #4b5563;
        }

        .submenu li {
            padding: 10px;
            font-size: 13px;
        }

        #header > div.menu.row.mid_align > i.fa-regular.fa-envelope {
            padding-right: 15px;
        }

        .submenu a {
            color: #4b5563;
            text-decoration: none;
            font-weight: 400;
        }

        .menu_item > .submenu > li:hover {
            color: #2563eb;
            background-color: #e1e1e1;
            border-radius: 3px;

        }

        .menu_item.active {
            /*background-color: #2563eb; !* Màu xanh lam *!*/
            color: #2563eb;
        }

        .menu_item.active i {
            color: #2563eb;
        }

        .menu_item.active span {
            color: #2563eb;
        }

        .submenu li.active {
            /*background-color: #e5e7eb; !* Màu xanh đậm hơn *!*/
            color: #2563eb;
            border-radius: 5px;
        }
        .logo {
            display: flex;
            align-items: center;
            margin: 0px 12px;
            padding-bottom: 30px;
        }
         .logo img {
            object-fit: contain;
            width: 95%;
            height: 80%;
            cursor: pointer;
        }
    </style>
</head>


<body>
<div id="body" class="row">
    <nav id="sidebar" class="col">

        <div class="logo">
            <img class="logo" src="${pageContext.request.contextPath}/static/image/logo_web.png" alt="Logo">
        </div>

        <ul>
            <!-- Bảng điều khiển -->
            <li class="menu_item ">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-house"></i>
                    <a href="dashboard">Tổng quan</a>
                </div>
            </li>


            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-box"></i>
                    <span>Sản phẩm</span>
                    <i class="fa-solid fa-chevron-down toggle-arrow"></i>
                </div>

                <ul class="submenu">
                    <li class="submenu_item">
                        <button onclick="location.href='${pageContext.request.contextPath}/admin/list-product'">
                            Danh sách sản phẩm
                        </button>
                    </li>
                    <li class="submenu_item">
                        <button onclick="location.href='${pageContext.request.contextPath}/admin/add-product'">
                            Thêm sản phẩm
                        </button>
                    </li>
                </ul>
            </li>

            <!-- Đơn hàng -->
            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-cart-shopping"></i>
<%--                    <a href="orders">Đơn hàng</a>--%>
                    <button onclick="location.href='${pageContext.request.contextPath}/admin/orders'">
                        Đơn hàng
                    </button>
                </div>

            </li>

            <!-- Khách hàng -->
            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-users"></i>
<%--                    <a href="customers">Khách hàng</a>--%>
                    <button onclick="location.href='${pageContext.request.contextPath}/admin/customers'">
                        Khách hàng
                    </button>
                </div>

            </li>

            <%-- Thành viên--%>
            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-user"></i>
                    <button onclick="location.href='${pageContext.request.contextPath}/admin/team-member'">
                        Thành viên
                    </button>
                </div>
            </li>

            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fas fa-star"></i>
                    <a href="${pageContext.request.contextPath}/admin/review">Xem Đánh giá </a>
                </div>
            </li>

            <!-- Danh mục -->
            <li class="menu_item">
                <div class="wrap_menu_item">
                        <i class="fa-solid fa-warehouse"></i>
                    <button onclick="location.href='${pageContext.request.contextPath}/admin/category'">
                        Danh mục
                    </button>
                </div>
            </li>

            <!-- Thẻ tag     -->

<%--            <li class="menu_item">--%>
<%--                <div class="wrap_menu_item">--%>
<%--                    <i class="fa-solid fa-tags"></i>--%>
<%--                    <button onclick="location.href='${pageContext.request.contextPath}/admin/tag'">--%>
<%--                        Thẻ (Tag)--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </li>--%>

            <%--Nhà sản xuất--%>
            <li class="menu_item">
                <div class="wrap_menu_item">
                        <i class="fa-solid fa-warehouse"></i>
                        <a href="brand">Nhà sản xuất</a>
                </div>
            </li>

<%--            MananageRole--%>


            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-user-shield"></i>
                    <a href="${pageContext.request.contextPath}/admin/manage-role">Quản lý Vai Trò</a>
                </div>
            </li>

        <%--Banner--%>

            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-regular fa-image"></i>
                    <button onclick="location.href='${pageContext.request.contextPath}/admin/banner'">
                        Quản lý Banner
                    </button>
                </div>
            </li>


            <li class="menu_item">
                <div class="wrap_menu_item">
                    <i class="fa-solid fa-gear"></i>
                    <a href="account_settings.jsp"> Cài đặt</a>
                    <i class="fa-solid fa-chevron-down toggle-arrow"></i>
                </div>
                <ul class="submenu">
                    <li class="submenu_item">
                        <a href="">Hồ sơ</a>
                    </li>
                    <li class="submenu_item">
                        <a href="">Quên mật khẩu</a>
                    </li>
                    <li class="submenu_item">
                        <a href="">Thông báo</a>
                    </li>
                    <li class="submenu_item">
                        <a href="">Kết nối</a>
                    </li>
                    <li class="submenu_item">
                        <a href="">Hủy tài khoản</a>
                    </li>

                </ul>
            </li>
        </ul>
    </nav>

</div>
<script src="${pageContext.request.contextPath}/static/style-page/admin/Admin.js"></script>

</body>
</html>
