<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/12/25
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_order/UserOrder.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-user_order/UserOrder.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_order/OrderHistoryItem.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-user_order/OrderHistoryItem.js"></script>


</head>
<body>

<div class="header">
    <jsp:include page="/home/header.jsp"/>
</div>

<div class="container">
    <div class="sidebar">
        <jsp:include page="user-sidebar.jsp"/>
    </div>


    <%--    Order History Item--%>
    <div class="content">

        <div id="order_header" class="mid_align row">
            <div class="no_of_order col mid_align">
                <span class="num">39</span>
                <span>Đơn hàng</span>
            </div>

            <div class="rec_vertical"></div>

            <div class="total mid align col">
                <span class="num">198 Tr</span>
                <span>Tổng tiền tích lũy</span>
            </div>

        </div>


        <div id="order_menu" class="mid_align row">
            <div class="menu_item">Tất cả</div>
            <div class="menu_item">Chờ xác nhận</div>
            <div class="menu_item">Đã xác nhận</div>
            <div class="menu_item">Đang vận chuyển</div>
            <div class="menu_item">Đã giao hàng</div>
            <div class="menu_item">Đã hủy</div>
        </div>


        <div id="order_body">


            <%--            <div id="order_container" class="mid_align row">--%>


            <%--                <div class="image">--%>
            <%--                    <img src="${pageContext.request.contextPath}/static/image/tivi.png"/>--%>
            <%--                </div>--%>


            <%--                <div class="description mid_align col  ">--%>
            <%--                    <div class="title bold">98 Inch Crystal UHD DU9000 4K Tizen OS Smart TV (2024)</div>--%>

            <%--                    <div class="color">--%>
            <%--                        <span class="color_name">Màu Sắc: <span>Đen</span></span>--%>
            <%--                    </div>--%>

            <%--                    <div class="quantity">--%>
            <%--                        <span class="color_name">Số lượng: <span>1</span></span>--%>
            <%--                    </div>--%>


            <%--                    <div class="status">--%>
            <%--                        <span>Đã giao hàng</span>--%>
            <%--                    </div>--%>

            <%--                </div>--%>


            <%--                <div class="section_price mid_align col  ">--%>
            <%--                    <div class="date">--%>
            <%--                        <span>22/10/2024</span>--%>
            <%--                    </div>--%>

            <%--                    <div class="wrap_price col">--%>
            <%--                        <span class="title">Tổng thanh toán: </span>--%>
            <%--                        <span class="price">79,000,000 VND</span>--%>
            <%--                    </div>--%>

            <%--                    <div class="btn col">--%>
            <%--                        <button class="btn_detail" data-src="../component/user_order/OrderHistory.html">Xem chi tiết</button>--%>
            <%--                        <button class="btn_support">Hỗ trợ</button>--%>
            <%--                    </div>--%>


            <%--                </div>--%>


            <%--            </div>--%>

            <%--        </div>--%>
                <c:if test="${empty orderDetails}">
                    <div class="no_order">Hiện tại không có đơn hàng nào.</div>
                </c:if>
                <c:if test="${not empty orderDetails}">
                    <c:forEach var="order_detail" items="${orderDetails}">
                        <div id="order_container" class="mid_align row">
                            <!-- Product Image -->
                            <div class="image">
<%--                                <img src="${orderDetails.imageUrl}" alt="Hình ảnh sản phẩm" />--%>
                                <p>Ảnh</p>
                            </div>

                            <!-- Product Info -->
                            <div class="description mid_align col">
<%--                                <div class="title bold">${orderDetails.productName}</div>--%>
                                <div class="title bold">Name</div>
                                <div class="color">
                                    <span class="color_name">Màu Sắc: <span>Fix color</span></span>
                                </div>
                                <div class="quantity">
<%--                                    <span class="color_name">Số lượng: <span>${orderDetails.quantity}</span></span>--%>
                                    <span class="color_name">Số lượng: <span>1231232321</span></span>
                                </div>
                                <div class="status">
                                    <span>Fix status</span>
                                </div>
                            </div>

                            <!-- Price Info -->
                            <div class="section_price mid_align col">
                                <div class="date">
                                    <span>Fix date</span>
                                </div>
                                <div class="wrap_price col">
                                    <span class="title">Tổng thanh toán: </span>
<%--                                    <span class="price">${orderDetails.total} VND</span>--%>
                                    <span class="price">43134234 VND</span>
                                </div>
                                <div class="btn col">
                                    <button class="btn_detail">Xem chi tiết</button>
                                    <button class="btn_support">Hỗ trợ</button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

        </div>


    </div>


</body>
</html>