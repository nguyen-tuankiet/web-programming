<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/27/24
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">--%>
</head>
<body>
<div class="wrap mid_align row">

    <div class="img_section">
        <img src="${pageContext.request.contextPath}/static/image/tulanh2.jpg"  alt=""/>
    </div>


    <div class="infor_section">

        <div class="infor_name bold f22" id="name">
            <span>285 L Tủ Lạnh Ngăn Đông Dưới với Optimal Fresh Zone</span>
        </div>


        <div class="infor_color col">
            <span  class="bold f16">Màu Sắc: <span class="normal f16" > Đen Starry</span></span>

            <div class="choose_color row">
                <div class="col_item" id="pink"></div>
                <div class="col_item" id="gray"></div>
                <div class="col_item" id="yellow"></div>
            </div>


        </div>

        <div class="rating row mid_align">

            <span class="star" >
                <span class="star_empty"></span>
                <span class="star_fill"></span>
            </span>


            <span class="star" >
                <span class="star_empty "></span>
                <span class="star_fill"></span>
            </span>

            <span class="star " >
                <span class="star_empty"></span>
                <span class="star_fill"></span>
            </span>

            <span class="star" >
                <span class="star_empty"></span>
                <span class="star_fill"></span>
            </span>


            <span class="star" >
                <span class="star_empty"></span>
                <span class="star_fill"></span>
            </span>

            <span id="ratting" class="bold" style="padding: 0 5px"> 5 </span>
            <span id="noOfRatting"  class="bold" >(153)</span>

        </div>


        <div id="description">
            <ul class="list_descriptions">
                <li class="desc_item f14" >Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                <li class=" desc_item f14">Làm đá tự động nhanh chóng, tiện lợi</li>
                <li class="desc_item f14">Công nghệ làm lạnh vòm All Around Cooling </li>
            </ul>
        </div>

    </div>



    <div class="rec_vertical"></div>



    <div class="section_right col">
        <div class="price">
            <span class="bold f22">31.190.000 VND</span>
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



        <div class="wrap_btn col">
            <button class="btn buy" id="buy-now-btn">Mua Ngay</button>
            <button class="btn add">Thêm vào giỏ hàng</button>
        </div>

        <div id="cart-notification" class="notification hidden">
            <i class="fa fa-check-circle"></i>
            <span>Thêm vào giỏ hàng thành công</span>
        </div>

    </div>








</div>

<script src="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.js"></script>
</body>
