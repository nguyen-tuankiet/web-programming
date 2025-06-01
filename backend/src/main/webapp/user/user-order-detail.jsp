<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/14/25
  Time: 13:04
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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_order/UserOrderDetail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/style-component/style-user_order/UserOrderDetail.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_order/OrderHistoryItem.css">

</head>
<body>

<div class="header">
    <jsp:include page="/home/header.jsp"/>
</div>

<div class="body">
    <div class="sidebar">
        <jsp:include page="user-sidebar.jsp"/>
    </div>

    <c:if test="${ empty order}">
        <p>Chưa có đơn hàng</p>
    </c:if>

    <c:if test="${not empty order}">
        <div class="content">

            <div id="order_header" class="row mid_align">
                <i id="back_btn" class="back_btn fa-solid fa-arrow-left-long"></i>
                <span class="title">Lịch sử đơn hàng</span>
            </div>

            <div id="order_body">


                <div id="order_infor" class=" col">
                    <div class="wrap row">
                        <div class="order_id" data-order-id="${order.id}">
                            Mã đơn hàng: <span>#${order.id}</span>
                        </div>
                    </div>

                    <div class="order_status">
                        Trạng thái:
                        <c:choose>
                            <c:when test="${order.orderStatus == 'PENDING'}">
                                <span style="color: #FFA500">Chờ xác nhận</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'CONFIRMED'}">
                                <span style="color: #007bff">Đã xác nhận</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'PROCESSING'}">
                                <span style="color: #17a2b8">Đang sử lý</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'SHIPPED'}">
                                <span style="color: #6f42c1">Đang vận chuyển</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'DELIVERED'}">
                                <span style="color: #28a745">Đã giao hàng</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'CANCELLED'}">
                                <span style="color: #dc3545 ">Đã hủy</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'RETURNED'}">
                                <span style="color: #fd7e14">Khách trả lại hàng</span>
                            </c:when>
                            <c:when test="${order.orderStatus == 'FAILED'}">
                                <span style="color: #dc3545">Giao hàng thất bại</span>
                            </c:when>
                            <c:otherwise>
                                <span>Không rõ trạng thái</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>


                <div id="wrap_order_item" class=" col">


                    <c:if test="${empty orderDetails}">
                        <p>Đã xảy ra lỗi</p>
                    </c:if>


                    <c:if test="${not empty orderDetails}">
                        <c:forEach var="od" items="${orderDetails}">

                            <div class="order_item mid_align row" data-product-id="${od.productId}">


                                <div class="image">
                                    <img src="${od.imageUrl}"/>
                                </div>


                                <div class="description mid_align col  ">
                                    <div class="title bold">${od.productName}</div>

                                    <div class="color">
                                        <span class="color_name">Màu Sắc: <span>Đen</span></span>
                                    </div>

                                    <div class="quantity">
                                        <span class="color_name">Số lượng: <span>${od.quantity}</span></span>
                                    </div>
                                </div>

                                <div class="section_price mid_align col  ">

                                    <div class="wrap_price col">
                                        <span class="title">Tổng thanh toán: </span>
                                        <span class="price">
                                         <fmt:formatNumber value="${od.total}" pattern="#,###"/> VND

                                     </span>
                                    </div>
                                </div>
                                <c:set var="productReview" value="${reviewMap[od.productId]}" />
                                <div class="review-section" style="width:100%;margin-top:8px;">
                                    <c:choose>
                                        <c:when test="${not empty productReview}">
                                            <!-- Hiển thị số sao và nội dung review -->
                                            <div class="user-rating stars">
                                                <c:forEach begin="1" end="5" var="i">
                                                    <span class="star">
                                                        <i class="fa-solid fa-star"
                                                           style="color:${i <= productReview.rating ? 'gold' : 'gray'}"></i>
                                                    </span>
                                                </c:forEach>
                                            </div>
                                            <div class="review-text" style="margin-top:6px;font-style:italic;color:#555;">
                                                    ${productReview.description}
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Hiển thị form đánh giá nếu chưa đánh giá -->
                                            <c:if test="${order.orderStatus == 'DELIVERED'}">
                                                <form class="review-form" method="post">
                                                    <div class="user-rating stars">
                                                        <span class="star" data-value="1"><i class="fa-solid fa-star"></i></span>
                                                        <span class="star" data-value="2"><i class="fa-solid fa-star"></i></span>
                                                        <span class="star" data-value="3"><i class="fa-solid fa-star"></i></span>
                                                        <span class="star" data-value="4"><i class="fa-solid fa-star"></i></span>
                                                        <span class="star" data-value="5"><i class="fa-solid fa-star"></i></span>
                                                        <input type="hidden" name="rating" value="0" />
                                                    </div>
                                                    <textarea class="review-text" name="review" rows="3" placeholder="Chia sẻ suy nghĩ của bạn"></textarea>
                                                    <button type="submit" class="btn_submit">Gửi đánh giá</button>
                                                </form>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                            </div>


                        </c:forEach>
                    </c:if>


                    <div id="status_detail" class=" row">

                        <c:set var="statusStep">
                            <c:choose>
                                <c:when test="${order.orderStatus == 'PENDING'}">1</c:when>
                                <c:when test="${order.orderStatus == 'CONFIRMED'}">2</c:when>
                                <c:when test="${order.orderStatus == 'PROCESSING'}">2</c:when>
                                <c:when test="${order.orderStatus == 'SHIPPED'}">3</c:when>
                                <c:when test="${order.orderStatus == 'DELIVERED'}">4</c:when>
                                <c:when test="${order.orderStatus == 'CANCELLED'}">-1</c:when>
                                <c:when test="${order.orderStatus == 'RETURNED'}">99</c:when>
                                <c:when test="${order.orderStatus == 'FAILED'}">99</c:when>

                            </c:choose>
                        </c:set>

                        <div class="item_status section1 col ${statusStep >= 1 ? 'active' : ''}">
                            <i class="fa-solid fa-box-archive"></i>
                            <span>Đặt hàng thành công</span>
                            <span class="status_date">19/10/2024</span>
                        </div>

                        <div class="line"></div>


                        <div class="item_status section1 col ${statusStep >= 2 ? 'active' : ''}">
                            <i class="fa-solid fa-box-archive"></i>
                            <span>Chuẩn bị hàng</span>
                            <span class="status_date">19/10/2024</span>
                        </div>

                        <div class="line"></div>

                        <div class="item_status section3 col ${statusStep >= 3 ? 'active' : ''}">
                            <i class="fa-solid fa-truck-fast"></i>
                            <span>Đang vận chuyển</span>
                            <span class="status_date">20/10/2024</span>
                        </div>

                        <div class="line"></div>


                        <div class="item_status section4 col
                            <c:choose>
                                <c:when test='${statusStep < 0}'>failed</c:when>
                                <c:when test='${statusStep == 99}'>failed</c:when>
                                <c:when test='${statusStep >= 4}'>active</c:when>


                            </c:choose>">
                            <i class="fa-solid fa-box-open"></i>


                            <span>
                                <c:choose>
                                    <c:when test="${order.orderStatus == 'CANCELLED'}">Đơn hàng đã bị hủy</c:when>
                                    <c:when test="${order.orderStatus == 'RETURNED'}">Đã trả hàng</c:when>
                                    <c:when test="${order.orderStatus == 'FAILED'}">Giao hàng không thành công</c:when>
                                    <c:when test="${order.orderStatus == 'CANCELLED'}">Đơn hàng đã bị hủy</c:when>
                                    <c:otherwise>Đã nhận được hàng</c:otherwise>
                                </c:choose>
                            </span>
                            <span class="status_date">22/10/2024</span>
                        </div>


                    </div>
                    <div id="payment_infor" class="col">

                        <div class="title">
                            <span>Thông tin thanh toán </span>
                        </div>

                        <div class="content">


                            <div class="content_item">
                                <span class="desc">Tổng giá trị</span>
                                <span id="product_price" class="value">
                                    <fmt:formatNumber value="${order.total}" pattern="#,###"/> VND
                                </span>
                            </div>


<%--                            <div class="content_item">--%>
<%--                                <span class="desc">Thuế GTGT ( 10% )</span>--%>
<%--                                <span id="VAT" class="value">0 VND</span>--%>
<%--                            </div>--%>

                            <div class="content_item">
                                <span class="desc">Phí vận chuyển</span>
                                <span id="shipping_fee" class="value">
                                        <fmt:formatNumber value="${order.shippingFee}" pattern="#,###"/> VND
                                </span>

                            </div>

                            <div class="rec_horizontal"></div>

                            <div class="content_item">
                                <span class="desc">Tổng thanh toán:</span>
                                <span id="total_charge" class="value">
                                     <fmt:formatNumber value="${order.total + order.shippingFee}" pattern="#,###"/> VND
                                </span>
                            </div>


                        </div>


                    </div>


                    <c:if test="${not empty user}">
                        <div id="customer" class="col">

                            <div class="title">
                                <span>Thông tin khách hàng</span>
                            </div>

                            <div class="content row">

                                <div class="section_left col">


                                    <div class="content_item full_name">
                                        <i class="fa-regular fa-user"></i>
                                        <span> ${address.name} </span>
                                    </div>

                                    <div class="content_item phone">
                                        <i class="fa-solid fa-phone"></i>
                                        <c:if test="${not empty address.phone}">
                                            <span>${address.phone}</span>
                                        </c:if>
                                    </div>

                                    <div class="content_item mail">
                                        <i class="fa-regular fa-envelope"></i>
                                        <span>${user.email}</span>
                                    </div>

                                    <div class="content_item address">
                                        <i class="fa-solid fa-location-dot"></i>

                                        <c:if test="${not empty address}">
                                            <span>${address.detail}, ${address.commune}, ${address.district}, ${address.province}
                                             </span>
                                        </c:if>


                                    </div>


                                </div>


                                <div class="section_right">
                                    <div class="title">
                                        <span>Phương thức thanh toán:</span>
                                    </div>

                                    <div class="content col">

                                        <c:if test="${ not empty card}">
                                            <span>${card.type} : **** ${card.last4}</span>
                                        </c:if>

                                        <c:if test="${COD == true }">
                                            <span>Thanh toán khi nhận hàng</span>
                                        </c:if>

                                    </div>


                                    <div class="title">
                                        <span>Trạng thái thanh toán:</span>
                                    </div>
                                    <div class="content col">

                                        <c:if test="${order.paymentStatus =='PENDING'}">
                                            <span>Chưa thanh toán</span>
                                        </c:if>

                                        <c:if test="${order.paymentStatus =='PAID'}">
                                            <span>Đã thanh toán</span>
                                        </c:if>

                                    </div>

                                </div>


                            </div>


                        </div>
                    </c:if>


                </div>
            </div>

            <c:if test="${order.orderStatus == 'DELIVERED'  && !allReviewed}">
                <div class="write-review-section" id="review-section">
                    <h2>Viết đánh giá của bạn</h2>
                    <form id="review-form" method="post">
                        <div class="input-group">
                            <label for="rating-value"> </label>
                            <div id="user-rating" class="stars">
                                <span class="star" data-value="1"><i class="fa-solid fa-star"></i></span>
                                <span class="star" data-value="2"><i class="fa-solid fa-star"></i></span>
                                <span class="star" data-value="3"><i class="fa-solid fa-star"></i></span>
                                <span class="star" data-value="4"><i class="fa-solid fa-star"></i></span>
                                <span class="star" data-value="5"><i class="fa-solid fa-star"></i></span>
                            </div>
                            <input type="hidden" id="rating-value" name="rating" value="0">
                        </div>

                        <div class="input-group">
                            <label for="review-text">Viết đánh giá:</label>
                            <textarea id="review-text" name="review" rows="4" placeholder="Chia sẻ suy nghĩ của bạn" required></textarea>
                        </div>

                        <div class="send">
                            <button id="btn_submit">Gửi đánh giá</button>
                        </div>
                    </form>
                </div>
            </c:if>

        </div>
    </c:if>


</div>

</body>
</html>