<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<head>
    <title>List Product</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/Search_Product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/TopProduct.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.css">

</head>
<body>
<div class="container">

    <div class="search_header">
        <jsp:include page="../home/header.jsp"/>
    </div>

    <div id="banner">
        <%--    <iframe src = "../home/banner.html"></iframe>--%>
        <jsp:include page="../home/banner.jsp"/>
    </div>

    <div id="body" class="row">
        <div id="sidebar">

            <div class="title f18 ">
                <i class="fa-solid fa-filter"></i>
                Bộ lọc
            </div>

            <!---------------------------------------------------------------------------->
            <div class="section_type section_item col">

                <div class="title">Kiểu Tủ</div>

                <div class="item mid_align">
                    <input type="checkbox" id="type1">
                    <label for="type1">4 Cửa</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="type2">
                    <label for="type2">Family Hub</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="type3">
                    <label for="type3">Side by Side</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="type4">
                    <label for="type4">2 Cửa - Ngăn đông dưới</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="type5">
                    <label for="type5">2 Cửa - Ngăn đông trên</label>
                </div>


            </div>

            <div class="rec_horizontal"></div>

            <!-------------------------------------------------------------------------->


            <div class="section_price section_item col">

                <div class="title">Mức giá</div>

                <div class="item mid_align">
                    <input type="checkbox" id="price1">
                    <label for="price1">Từ 5-8 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price2">
                    <label for="price2">Từ 8-12 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price3">
                    <label for="price3">Từ 12-15 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price4">
                    <label for="price4">Từ 15-20 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price5">
                    <label for="price5">Trên 20 triệu</label>
                </div>


            </div>

            <div class="rec_horizontal"></div>

            <!-------------------------------------------------------------------------->

            <div class="section_capacity section_item col">

                <div class="title">Dung tích</div>

                <div class="item mid_align">
                    <input type="checkbox" id="capacity1">
                    <label for="capacity1">Dưới 300L</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="capacity2">
                    <label for="capacity2">Từ 300L đến 400L</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="capacity3">
                    <label for="capacity3">Từ 400L đến 500L</label>
                </div>


                <div class="item">
                    <input type="checkbox" id="capacity4">
                    <label for="capacity4">Trên 550L </label>
                </div>


            </div>

            <div class="rec_horizontal"></div>


        </div>


        <div id="list_product">


            <div id="top_product" class="col mid_align">
                <span>Top Sản Phẩm Bán Chạy </span>

                <div class="wrap_item row mid_align">
                    <jsp:include page="top-product.jsp"/>
                    <jsp:include page="top-product.jsp"/>
                    <jsp:include page="top-product.jsp"/>
                </div>
            </div>

            <span class="popular_title mid_align">Top Sản Phẩm Nổi Bậc </span>

            <c:if test="${not empty products}">
                <c:forEach items="${products}" var="p">

                    <div class="product_item col" data-stock="${p.stock}">

                        <div class="wrap mid_align row">

                            <div class="img_section">

                                <c:if test="${not empty p.imageUrl}">
                                    <img src="${p.imageUrl}" alt=""/>
                                </c:if>

                            </div>


                            <div class="infor_section">

                                <div class="infor_name bold f22" id="name">
                                    <span> ${p.name}</span>
                                </div>


                                <div class="infor_color col">
                                    <span class="bold f16">Màu Sắc: <span class="normal f16"> Đen Starry</span></span>

                                    <div class="choose_color row">
                                        <div class="col_item" id="pink"></div>
                                        <div class="col_item" id="gray"></div>
                                        <div class="col_item" id="yellow"></div>
                                    </div>


                                </div>

                                <div class="rating row mid_align">
                            <span id="noOfRatting" class="bold" style="padding: 0 5px">
                                4.7 (153)
                                <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                            </span>
                                </div>


                                <div id="description">
                                    <ul class="list_descriptions">
                                        <li class="desc_item f14">Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                                        <li class=" desc_item f14">Làm đá tự động nhanh chóng, tiện lợi</li>
                                        <li class="desc_item f14">Công nghệ làm lạnh vòm All Around Cooling</li>
                                    </ul>
                                </div>

                            </div>


                            <div class="rec_vertical"></div>


                            <div class="section_right col">
                                <div class="price">
                                   <span class="bold f22">
                                       <fmt:formatNumber value="${p.price}" pattern="#,###"/> VND
                                   </span>
                                </div>

                                <div class="service">
                                    <div class="service_item">
                                        <i class="fa-solid fa-gift"></i>
                                        <span>Ưu đãi thêm 5% (đến 1TRIỆU đồng)</span>
                                    </div>

                                    <div class="service_item">
                                        <i class="fa-solid fa-truck"></i>
                                        <span>Miễn Phí Vận Chuyển Toàn Quốc</span>
                                    </div>

                                    <div class="service_item">
                                        <i class="fa-solid fa-box-open"></i>
                                        <span>Đổi trả trong 14 ngày nếu phát sinh lỗi</span>
                                    </div>

                                    <div class="service_item">
                                        <i class="fa-solid fa-wallet"></i>
                                        <span>Trả Góp 0% Linh Hoạt Đến 24 Tháng</span>
                                    </div>

                                </div>


                                <div  class="wrap_btn col">
                                    <button class="btn buy" id="buy-now-btn">Mua Ngay</button>

                                    <button onclick="addToCart(${p.id},${p.optionId})" class="btn add">
                                        Thêm vào giỏ hàng
                                    </button>

                                </div>

                                <div id="cart-notification" class="notification hidden">
                                    <i class="fa fa-check-circle"></i>
                                    <span>Thêm vào giỏ hàng thành công</span>
                                </div>

                            </div>


                        </div>


                    </div>
                </c:forEach>
            </c:if>


        </div>


    </div>


</div>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.js"></script>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/Search_Product.js"></script>

</body>
