<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/12/25
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
                <span class="num">
                    ${count}
                </span>
                <span>Đơn hàng</span>
            </div>

            <div class="rec_vertical"></div>

            <div class="total mid align col">
                <span class="num">
                    ${savings}Tr
                </span>
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

            <c:if test="${empty orders}">
                <div class="no_order">Hiện tại không có đơn hàng nào.</div>
            </c:if>
            <c:if test="${not empty orders}">
                <c:forEach var="o" items="${orders}">

                    <div id="order_container" class="mid_align row">
                        <div class="image">
                            <img src="${o.productImage}" alt="Hình ảnh sản phẩm"/>
                        </div>

                        <div class="description mid_align col">
                            <div class="title bold">${o.productName}</div>

                            <div class="quantity">
                                <span class="color_name">Số lượng: <span>${o.quantity}</span></span>
                            </div>

                            <div class="status">

                                <c:choose>
                                    <c:when test="${o.orderStatus == 'PENDING'}">
                                        <span style="color: #FFA500">Chờ xác nhận</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'CONFIRMED'}">
                                        <span style="color: #007bff">Đã xác nhận</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'PROCESSING'}">
                                        <span style="color: #17a2b8">Đang sử lý</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'SHIPPED'}">
                                        <span style="color: #6f42c1">Đang vận chuyển</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'DELIVERED'}">
                                        <span style="color: #28a745">Đã giao hàng</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'CANCELLED'}">
                                        <span style="color: #dc3545 ">Đã hủy</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'RETURNED'}">
                                        <span style="color: #fd7e14" >Khách trả lại hàng</span>
                                    </c:when>
                                    <c:when test="${o.orderStatus == 'FAILED'}">
                                        <span style="color: #dc3545" >Giao hàng thất bại</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>Không rõ trạng thái</span>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                        <div class="section_price mid_align col">
                            <div class="date">
                                <span>${o.createAt}</span>
                            </div>
                            <div class="wrap_price col">
                                <span class="title">Tổng thanh toán: </span>
                                <span class="price">
                                    <fmt:formatNumber value="${o.total}" pattern="#,###"/> VND
                                </span>

                            </div>
                            <div class="btn col">
                                <a href="user-order-detail?orderId=${o.id}">
                                    <button class="btn_detail">Xem chi tiết</button>
                                </a>
                                <button  class="cancel_btn
                                        <c:if test="${o.orderStatus != 'PENDING' && o.orderStatus != 'CONFIRMED'}">disabled </c:if> "
                                >Hủy đơn</button>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

        </div>


    </div>


</body>
</html>