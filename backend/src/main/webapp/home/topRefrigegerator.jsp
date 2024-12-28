<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 12/27/2024
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Slideshow Example</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style_product/topRefrigerator.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style_product/topRefrigerator.js"></script>
</head>
<body>

<div id="banner">
    <iframe src="${pageContext.request.contextPath}/home/banner.jsp"></iframe>
</div>

<!-- Text Block 1 -->
<div class="text-section1">
    <h2>Bespoke - Làm chủ phong cách với kỷ nguyên tủ lạnh mới</h2>
    <p>
        Không còn nhàm chán với những chiếc tủ lạnh “tất cả như một”, Samsung Bespoke mang đến cho bạn đa dạng gam
        màu
        thời thượng, đẹp mọi không gian bếp. Đã có hơn 3 triệu chiếc tủ lạnh Samsung Bespoke được bán ra trên toàn
        thế
        giới, bạn đã sẵn sàng khởi tạo xu hướng màu sắc ngay hôm nay? Đẹp đi đừng đợi!
    </p>
</div>

<!-- Phần giới thiệu sản phẩm -->
<div class="showcase">
    <!-- Product Card 1 -->
    <div class="product-large">
        <a href="${pageContext.request.contextPath}/home/Product-detail/ProductDetail.jsp" aria-label="View more">
            <div class="product-name-large">Trắng Thạch Anh/ Nâu Be</div>
            <div class="img-wrap">
                <img src="${pageContext.request.contextPath}/static/image/img_showcase/2.1.jpg" alt="2.1">
            </div>
            <div class="cta-button">Xem sản phẩm</div>
        </a>
    </div>

    <!-- Wrap product-small in all-product-small -->
    <div class="all-product-small">
        <!-- Product Card 2 -->
        <div class="product-small">
            <a href="${pageContext.request.contextPath}/home/Product-detail/ProductDetail.jsp" aria-label="View more">
                <div class="product-name">Trắng Thạch Anh/ Xanh Navy</div>
                <div class="img-wrap">
                    <img src="${pageContext.request.contextPath}/static/image/img_showcase/2.2.jpg" alt="2.2">
                </div>
                <div class="cta-button">Xem sản phẩm</div>
            </a>
        </div>
        <!-- Product Card 3 -->
        <div class="product-small">
            <a href="${pageContext.request.contextPath}/home/Product-detail/ProductDetail.jsp" aria-label="View more">
                <div class="product-name">Trắng Thạch Anh/ Hồng Pha Lê</div>
                <div class="img-wrap">
                    <img src="${pageContext.request.contextPath}/static/image/img_showcase/2.3.jpg" alt="2.3">
                </div>
                <div class="cta-button">Xem sản phẩm</div>
            </a>
        </div>
        <!-- Product Card 4 -->
        <div class="product-small">
            <a href="${pageContext.request.contextPath}/home/Product-detail/ProductDetail.jsp" aria-label="View more">
                <div class="product-name">Trắng Thạch Anh</div>
                <div class="img-wrap">
                    <img src="${pageContext.request.contextPath}/static/image/img_showcase/2.4.jpg" alt="2.4">
                </div>
                <div class="cta-button">Xem sản phẩm</div>
            </a>
        </div>
        <!-- Product Card 5 -->
        <div class="product-small">
            <a href="${pageContext.request.contextPath}/home/Product-detail/ProductDetail.jsp" aria-label="View more">
                <div class="product-name">Trắng Thạch Anh/ Nâu Ánh Than</div>
                <div class="img-wrap">
                    <img src="${pageContext.request.contextPath}/static/image/img_showcase/2.5.jpg" alt="2.5">
                </div>
                <div class="cta-button">Xem sản phẩm</div>
            </a>
        </div>
    </div>
</div>

<!-- Text Block 2 -->
<div class="text-section2">
    <h2>Đẹp từ thiết kế, sáng bừng không gian</h2>
    <p>
        Không còn nhàm chán với những chiếc tủ lạnh “tất cả như một”, Samsung Bespoke mang đến cho bạn đa dạng gam
        màu
        thời thượng, đẹp mọi không gian bếp. Đã có hơn 3 triệu chiếc tủ lạnh Samsung Bespoke được bán ra trên toàn
        thế
        giới, bạn đã sẵn sàng khởi tạo xu hướng màu sắc ngay hôm nay? Đẹp đi đừng đợi!
    </p>
</div>

<!-- Slideshow Container 2 -->
<div id="slideshow2">
    <iframe src="${pageContext.request.contextPath}/home/slideshow-topRefrigerator.jsp" class="slideshow2"></iframe>
</div>
<!-- Feature benefit -->
<div class="feature-benefit">
    <div class="feature-benefit__text">
        <h2>Ngăn đông mềm linh hoạt 4 chế độ Optimal Fresh+</h2>
        <h3>Linh hoạt chuyển đổi 4 chế độ theo nhu cầu</h3>
        <p>Với 4 chế độ làm lạnh linh hoạt...</p>
        <ul>
            <li>*Chế độ “Làm mát”: Thanh kéo ở “Min”, nhiệt độ 3°C.</li>
            <li>*Chế độ “Thịt & Cá”: Thanh kéo ở “Max”, nhiệt độ 3°C.</li>
            <li>*Chế độ “Đông mềm”: Thanh kéo ở “Max”, nhiệt độ 1°C.</li>
            <li>*Chế độ “Làm lạnh nhanh”: Thanh kéo ở “Max”, nhấn “Power Cool”.</li>
        </ul>
    </div>
    <div class="feature-benefit__video">
        <video autoplay muted loop playsinline preload="auto">
            <source src="//images.samsung.com/is/content/samsung/assets/vn/refrigerators/top-mount-freezer/optimal-fresh-plus/20231013/Che-do-lam-lanh_720x720.mp4"
                    type="video/mp4">
        </video>
    </div>
</div>

<!-- Text Block 3 -->
<div class="text-section3">
    <h2>Bảo toàn dưỡng chất & giữ tươi lâu hơn gấp 2 lần</h2>
    <p>
        Ngăn đông mềm linh hoạt Optimal Fresh+ ở chế độ “Đông mềm” giữ thịt, cá tươi lâu hơn gấp 2 lần...
    </p>
    <img src="${pageContext.request.contextPath}/static/image/img_slideshow/ptmain.jpg" alt="ptmain">
</div>

<footer>
    <div class="footer">
        <iframe src="${pageContext.request.contextPath}/home/Product-detail/footer.jsp" class="iframe"></iframe>
    </div>
</footer>

</body>
</html>
