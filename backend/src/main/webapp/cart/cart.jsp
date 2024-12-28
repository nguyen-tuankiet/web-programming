<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/28/24
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/sytle-cart/Cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <script src="${pageContext.request.contextPath}/static/style-component/sytle-cart/Cart.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/sytle-cart/CartItem.css">

</head>
<body>

<div id="wrap_cart">

    <div class="cart_header">
        <jsp:include page="/home/header.jsp"/>
    </div>
    <div id="cart_body" class="mid_align col">


        <c:if test="${empty productCarts}">
            <div id="empty_cart" class="mid_align col">
                <i class="fa-solid fa-cart-plus"></i>
                <h1>Giỏ Hàng Đang Trống</h1>
                <span>Đăng nhập vào tài khoản Samsung của bạn để xem các mục đã lưu hoặc tiếp tục mua sắm</span>

                <div class="btn row">
                    <button class="btn_shopping">Tiếp tục mua sắm</button>
                    <button class="btn_login">Đăng nhập</button>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty productCarts }">


            <div class="content row">
                <div class="list_item ">
                    <span>Bạn đang có 4 sản phâm trong giỏ hàng</span>


                    <c:forEach items="${productCarts}" var="p">
                        <jsp:include page="cart-item.jsp">
                            <jsp:param name="item" value="${p}"/>
                        </jsp:include>
                    </c:forEach>


                </div>


                <div class="bill mid_align rol ">
                    <div class="discount">
                        <span class="title">Nhập Mã Khuyến Mãi</span>

                        <div class="wrap_input">
                            <input id="voucher" type="text" placeholder="Voucher hoặc gift code ">
                            <label for="voucher"></label>
                            <button type="submit">Áp Dụng</button>
                        </div>

                    </div>


                    <div class="summary col">
                        <span class="title">Bản Tóm Tắt</span>
                        <div class="price item_price">
                            <span>Tổng giá trước thuế</span>
                            <span class="value">95,775,561 VND</span>
                        </div>

                        <div class="tax item_price">
                            <span>Thuế GTGT</span>
                            <span class="value">9,577,556 VND</span>
                        </div>
                    </div>


                    <div class="wrap_total">
                        <div class="total">
                            <span>Tổng cộng</span>
                            <span>105,353,117 VND</span>
                        </div>
                        <span class="note">Đẫ bao gồm thuế GTGT</span>
                    </div>


                    <button type="submit" id="pay"
                            data-src="/web-programming/frontEnd/src/component/checkout/Checkout.html">Thanh Toán
                    </button>

                    <div class="term_condition">
                <span>Bằng cách gửi đơn đặt hàng,
                    bạn đồng ý với <a href="#">Điều khoản & điều kiện</a> và
                    chúng tôi sẽ sử dụng dữ liệu cá nhân của bạn theo
                    <a href="#">Chính sách quyền riêng tư</a> của chúng tôi.</span>
                    </div>


                    <div class="ads col">
                        <div class="ads_item">
                            <i class="fa-solid fa-medal"></i>
                            <span>Cam kết giá</span>

                        </div>

                        <div class="ads_item">
                            <i class="fa-solid fa-truck-fast"></i>
                            <span>Giao hàng miễn phí toàn quốc </span>

                        </div>
                        <div class="ads_item">
                            <i class="fa-solid fa-percent"></i>
                            <span>Gói trả góp 0%</span>

                        </div>

                        <div class="ads_item">
                            <i class="fa-solid fa-rotate"></i>
                            <span>Đổi sản phẩm theo chính sách quy định trong vòng 14 ngày</span>

                        </div>

                    </div>


                </div>


            </div>


        </c:if>


    </div>

</div>


</body>
</html>