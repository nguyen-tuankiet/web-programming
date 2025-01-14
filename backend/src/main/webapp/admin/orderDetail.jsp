<%--
  Created by IntelliJ IDEA.
  User: win10pro
  Date: 12/27/2024
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Đơn Hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/orderDetail.css">
</head>
<body>

<div class="header">
    <jsp:include page="/home/header.jsp"/>
</div>

<div class="container">


    <div class="sidebar">
        <jsp:include page="SideBar.jsp"/>
    </div>


    <div class="content">
        <div class="row">
            <h2 class="header-title">Chi Tiết Đơn Hàng</h2>
        </div>

        <div class="order-details">


            <div class="order-header">
                <div class="order-progress">
                    <div class="step completed">
                        <div class="circle active"></div>
                        <span class="label active">Đơn Hàng Đã Đặt</span>
                    </div>
                    <div class="line active"></div>
                    <div class="step completed">
                        <div class="circle active"></div>
                        <span class="label active">Đã Đóng Gói</span>
                    </div>
                    <div class="line active "></div>
                    <div class="step completed">
                        <div class="circle active "></div>
                        <span class="label active">Đã Giao Hàng</span>
                    </div>
                    <div class="line active"></div>
                    <div class="step completed">
                        <div class="circle active"></div>
                        <span class="label active">Đã Giao Thành Công</span>
                    </div>
                </div>
            </div>

            <div class="order-summary-container">

                <div class="order-info">
                    <h3>Mã Đơn Hàng: DU00017</h3>
                    <p>Ngày Đặt Hàng: 13/6/2024 - 14:50 <span class="status-paid">Đã Thanh Toán</span></p>

                    <table class="products-table">
                        <thead>
                        <tr>
                            <th>Sản Phẩm</th>
                            <th>Mã Sản Phẩm</th>
                            <th>Số Lượng</th>
                            <th>Số Tiền</th>
                        </tr>
                        </thead>
                        <tbody>



                        <c:if test="${not empty orderDetails}">
                            <c:forEach var="od" items="${orderDetails}">

                                <tr>
                                    <td style="text-align: left;">
                                        <div class="product-info">
                                            <img src="${od.imageUrl}"
                                                 alt="${od.productName}" class="product-image">
                                            <span>${od.productName}</span><br>

                                        </div>
                                    </td>
                                    <td>${od.productId}</td>
                                    <td>${od.quantity}</td>
                                    <td>
                                        <fmt:formatNumber value="${od.total}" pattern="#,###"/> VND
                                    </td>
                                </tr>


                            </c:forEach>
                        </c:if>


                        </tbody>
                    </table>






                    <button class="invoice-btn">Hóa Đơn</button>
                </div>

                <div class="summary-details">

                    <div class="order">
                        <h3>Tóm Tắt Đơn Hàng</h3>
                        <p>Tổng Phụ: <span>7,990,000 VND</span></p>
                        <p>Giảm Giá (DIS15%): <span>-1,198,500 VND</span></p>
                        <p>Phí Vận Chuyển: <span>352,500 VND</span></p>
                        <p>Thuế VAT 19% (đã bao gồm): <span>1,504,000 VND</span></p>
                        <p class="total-amount">Tổng Số Tiền: <span>8,644,000 VND</span></p>
                    </div>

                    <div class="payment">
                        <h3>Chi Tiết Thanh Toán</h3>
                        <p>Giao Dịch: <span> #DU4444TO10000</span></p>
                        <p>Phương Thức Thanh Toán: <span> Thẻ Tín Dụng</span></p>
                        <p>Chủ Thẻ <span>Tên: Harold Gonzalez</span></p>
                        <p>Số Thẻ: <span>xxxx xxxx xxxx 6779</span></p>
                        <p class="total-amount">Tổng Số Tiền: <span>8,644,000 VND</span></p>
                    </div>

                </div>

            </div>
        </div>
    </div>


</div>


</body>
</html>

