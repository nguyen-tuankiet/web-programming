<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/5/25
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-checkout/Checkout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/sytle-cart/CartItem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-checkout/Checkout.js"></script>


    <style>
        #price {
            margin-bottom: 5px;
            font-size: 18px;
            font-weight: 600;
            color: #0170F0;

        }
    </style>


</head>
<body>

<c:if test="${empty addressList}">
    <div class="notice">Bạn chưa có địa chỉ giao hàng. <a href="${pageContext.request.contextPath}/profile/address">Thêm địa chỉ mới</a></div>
</c:if>
<c:if test="${empty cardList}">
    <div class="notice">Bạn chưa có thẻ thanh toán. <a href="${pageContext.request.contextPath}/profile/card">Thêm thẻ mới</a></div>
</c:if>


<div id="header" class="mid_align row">


    <div class="cart_header">
        <jsp:include page="/home/header.jsp"/>
    </div>


</div>


<div class="nav">
    <a href="cart" class="back">
        <i class="fa-solid fa-arrow-left"></i> Back
    </a>
</div>


<div id="body" class="mid_align">


    <div class="container row">

        <div class="left-side">


            <div class="list_product">

                <jsp:useBean id="productList" scope="request" type="java.util.List"/>

                <c:if test="${not empty productList }">

                <c:forEach items="${productList}" var="p">

                <div class="wrap mid_align row product-item"
                     data-stock="${p.stock}"
                     data-id="${p.productId}"  data-option-id="${p.optionId}"
                     data-quantity="${p.quantity}" data-price="${p.price}"

                     data-height = "${p.height}" data-length = "${p.length}"
                     data-width = "${p.width}" data-weight = "${p.weight}"
                >

                <div class="image">
                    <c:choose>
                        <c:when test="${empty p.imageUrl}">
                            <img src="${pageContext.request.contextPath}/static/image/default_img.jpg"
                                 alt=""/>
                        </c:when>
                        <c:otherwise>
                            <img src="${p.imageUrl}" alt=""/>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="description mid_align col  ">
                    <div class="title ">${p.name}</div>

                    <div class="color">
                        <span class="color_name">Màu Sắc: Đen  </span>
                    </div>

                    <c:choose>
                        <c:when test="${not empty p.stock and p.stock > 0}">
                            <div class="status">
                                <span class="status_type">Còn hàng</span>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="status">
                                <span class="status_type">Đang về hàng</span>
                            </div>
                        </c:otherwise>
                    </c:choose>


                </div>


                <div class=" price_and_quantity mid_align col  ">
                    <div id="price" data-price="${p.price}">
                        <fmt:formatNumber value="${p.price * p.quantity}" pattern="#,###"/> VND
                    </div>


                    <div id="quantity" class=" mid_align row" data-quantity="${p.quantity}">
                        Số lượng : ${p.quantity}
                    </div>


                </div>


            </div>


            </c:forEach>

            </c:if>


            <div class="address">
                <div class="address_title row ">
                    <span class="">Địa chỉ Nhận hàng</span>

                </div>

                <div class="address_body">
                    <c:if test="${not empty addressList }">

                        <c:set var="found" value="fasle"/>
                        <c:forEach items="${addressList}" var="address">

                            <c:if test="${address.isDefault == true }">
                                <div id="address" class="item_header row mid_align"
                                     data-address-id="${address.id}"
                                     data-province-id="${address.provinceId}"
                                     data-district-id="${address.districtId}"
                                     data-commune-id="${address.communeId}"

                                >

                                    <span class="name">${address.name}</span>
                                    <div class="rec_vertical"></div>
                                    <span class="phone">${address.phone}</span>
                                    <a href="#" class="change">Thay đổi</a>
                                </div>

                                <div class="address_detail">
                                    <span> ${address.detail}, ${address.commune}, ${address.district}, ${address.province}  </span>
                                </div>

                                <c:set var="found" value="true"/>
                            </c:if>


                        </c:forEach>


                        <c:if test="${found == false }">

                            <c:forEach items="${addressList}" var="address">

                                <c:if test="${found == false}">
                                    <div id="address" class="item_header row mid_align"
                                         data-address-id="${address.id}"
`                                        data-province-id="${address.provinceId}"
                                         data-district-id="${address.districtId}"
                                         data-commune-id="${address.communeId}"

                                    >

                                        <span class="name">${address.name}</span>
                                        <div class="rec_vertical"></div>
                                        <span class="phone">${address.phone}</span>
                                        <a href="#" class="change">Thay đổi</a>
                                    </div>

                                    <div class="address_detail">
                                        <span> ${address.detail}, ${address.commune}, ${address.district}, ${address.province}  </span>
                                    </div>

                                    <c:set var="found" value="true"/>


                                </c:if>

                            </c:forEach>
                        </c:if>

                    </c:if>


                </div>


            </div>

            <div class="payment">
                <div class="payment_title row ">
                    <span class="">Phương thức thanh toán</span>
                </div>

                <div class="payment_body col">


                    <div class="item mid_align cod">
                        <i class="fa-solid fa-money-bill"></i>
                        <span>Thanh toán khi nhận hàng</span>
                        <input type="radio" name="payment-method" data-payment="COD" checked>
                    </div>

                    <div class="item mid_align banking col">

                        <div class="title">
                            <i class="fa-solid fa-building-columns"></i>
                            <span>Thanh toán qua ngân hàng</span>
                        </div>


                        <div class="card_list col">

                            <c:if test="${not empty cardList }">
                                <c:forEach items="${cardList}" var="card">

                                    <div class="card_item">
                                        <div class="wrap_card">

                                            <c:if test="${card.type == 'Visa'}">
                                                <i class="fa-brands fa-cc-visa"></i>
                                            </c:if>

                                            <c:if test="${card.type == 'MasterCard'}">
                                                <i class="fa-brands fa-cc-mastercard"></i>
                                            </c:if>

                                            <span>**** ${card.last4}</span>


                                            <c:if test="${card.isDefault == 'true'}">
                                                <input type="radio" name="payment-method" data-payment="${card.id}"
                                                       checked>
                                            </c:if>

                                            <c:if test="${card.isDefault == 'false'}">
                                                <input type="radio" name="payment-method" data-payment="${card.id}">
                                            </c:if>


                                        </div>
                                    </div>

                                </c:forEach>
                            </c:if>

                        </div>

                    </div>


                </div>


            </div>


        </div>


    </div>

    <div class="right-side col ">
        <div class="bill mid_align col ">
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
                    <span id="before_tax" class="value">0 VND</span>
                </div>

                <div class="tax item_price">
                    <span>Thuế GTGT</span>
                    <span id="VAT" class="value">0 VND</span>
                </div>

                <div class="ship item_price">
                    <span>Phí vận chuyển</span>
                    <span id="ship_fee" class="value">0 VND</span>
                </div>

            </div>


            <div class="wrap_total">
                <div class="total_label">
                    <span>Tổng cộng</span>
                    <span id="total">0 VND</span>
                </div>
                <span class="note">Đã bao gồm thuế GTGT</span>
            </div>


            <button type="submit" id="pay">
                Thanh Toán
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
</div>

</div>

</body>
</html>