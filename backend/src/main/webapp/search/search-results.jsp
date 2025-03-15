<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 3/9/2025
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Kết quả tìm kiếm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search-results.css">
</head>
<body>

<div class="search_header">
    <jsp:include page="../home/header.jsp"/>
</div>

<div id="list_product">
    <%-- Hàng sản phẩm 1 --%>
    <div class="product-row">
        <div class="search_body">
            <div class="wrap_img">
                <img src="${pageContext.request.contextPath}/static/image/tulanh2.jpeg" alt="Tủ lạnh 1" />
            </div>
            <div class="infor col">
<%--                <div  class="product-title"><a href="#">636 L Tủ Lạnh Bespoke 4 Cửa - AI Family Hub™</a></div>--%>
                <div class="infor mid_align col ">
                    <div id="top_name" class="bold f16">
                        <a href="product-detail?id=${pro.id}"> ${pro.name} 636 L Tủ Lạnh Bespoke 4 Cửa - AI Family Hub™</a>
                    </div>


                    <div id="price" class="bold f22">
                        <%--                                        20.490.000 ₫--%>
                        <fmt:formatNumber value="${pro.price}" pattern="#,###"/> VND

                        <span id="ratting" class="" style="padding: 0 5px">
                                            5 (153)
                                            <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                                        </span>

                    </div>


                    <div id="top_description">
                        <ul class="list_descriptions">
                            <li class="desc_item f12">Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                            <li class=" desc_item f12">Làm đá tự động nhanh chóng, tiện lợi</li>
                            <li class="desc_item f12">Công nghệ làm lạnh vòm All Around Cooling</li>
                        </ul>
                    </div>

                </div>
            </div>
            <div class="operation">
                <button class="buy-now">Mua ngay</button>
                <button class="btn add">Thêm vào giỏ hàng</button>
            </div>
        </div>

        <div class="search_body">
            <div class="wrap_img">
                <img src="${pageContext.request.contextPath}/static/image/tulanh2.jpeg" alt="Tủ lạnh 2" />
            </div>
            <div class="infor col">
<%--                <div  class="product-title"><a href="#">Tủ lạnh LG French Door mặt gương 612L</a></div>--%>
                <div class="infor mid_align col ">
                    <div id="top_name" class="bold f16">
                        <a href="product-detail?id=${pro.id}"> ${pro.name} Tủ lạnh LG French Door mặt gương 612L</a>
                    </div>


                    <div id="price" class="bold f22">
                        <%--                                        20.490.000 ₫--%>
                        <fmt:formatNumber value="${pro.price}" pattern="#,###"/> VND

                        <span id="ratting" class="" style="padding: 0 5px">
                                            5 (153)
                                            <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                                        </span>

                    </div>


                    <div id="top_description">
                        <ul class="list_descriptions">
                            <li class="desc_item f12">Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                            <li class=" desc_item f12">Làm đá tự động nhanh chóng, tiện lợi</li>
                            <li class="desc_item f12">Công nghệ làm lạnh vòm All Around Cooling</li>
                        </ul>
                    </div>

                </div>
            </div>
            <div class="operation">
                <button class="buy-now">Mua ngay</button>
                <button class="btn add">Thêm vào giỏ hàng</button>
            </div>
        </div>

        <div class="search_body">
            <div class="wrap_img">
                <img src="${pageContext.request.contextPath}/static/image/tulanh2.jpeg" alt="Tủ lạnh 3" />
            </div>
            <div class="infor col">
<%--                <div  class="product-title"><a href="#">Tủ lạnh ngăn đá dưới 340L InstaView™</a></div>--%>
                <div class="infor mid_align col ">
                    <div id="top_name" class="bold f16">
                        <a href="product-detail?id=${pro.id}"> ${pro.name} Tủ lạnh ngăn đá dưới 340L InstaView™</a>
                    </div>


                    <div id="price" class="bold f22">
                        <%--                                        20.490.000 ₫--%>
                        <fmt:formatNumber value="${pro.price}" pattern="#,###"/> VND

                        <span id="ratting" class="" style="padding: 0 5px">
                                            5 (153)
                                            <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                                        </span>

                    </div>


                    <div id="top_description">
                        <ul class="list_descriptions">
                            <li class="desc_item f12">Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                            <li class=" desc_item f12">Làm đá tự động nhanh chóng, tiện lợi</li>
                            <li class="desc_item f12">Công nghệ làm lạnh vòm All Around Cooling</li>
                        </ul>
                    </div>

                </div>
            </div>
            <div class="operation">
                <button class="buy-now">Mua ngay</button>
                <button class="btn add">Thêm vào giỏ hàng</button>
            </div>
        </div>
    </div>
</div>

</body>
