<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 12/27/2024
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.css">
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.js"
            defer></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.css">
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.js"></script>
</head>
<body2>
    <div class="cart_header">
        <jsp:include page="/home/header.jsp"/>
    </div>
    <div class="container">
        <div class="section1">
            <div class="carousel-container">
                <!-- Main Image Display -->
                <%--            <img id="mainImage"--%>
                <%--                 src="<c:choose>--%>
                <%--                <c:when test='${not empty images}'>--%>
                <%--                    ${images[0].url}--%>
                <%--                </c:when>--%>
                <%--                <c:otherwise>--%>
                <%--                    ${pageContext.request.contextPath}/static/images/placeholder.jpg--%>
                <%--                </c:otherwise>--%>
                <%--              </c:choose>"--%>
                <%--                 alt="Carousel Image"--%>
                <%--                 class="carousel-image"--%>
                <%--                 data-context-path="${pageContext.request.contextPath}">--%>

                <!-- Carousel Container -->
                <div class="carousel-container">
                    <!-- Main Image Display -->
                    <img id="mainImage" src="${pageContext.request.contextPath}/static/image/img-detail/image1.jpg"
                         alt="Carousel Image" class="carousel-image">
                    <!-- Navigation Arrows -->
                    <div class="nav-arrow left" onclick="prevImage()">&#10094;</div>
                    <div class="nav-arrow right" onclick="nextImage()">&#10095;</div>

                    <!-- Thumbnails -->
                    <div class="thumbnails">
                        <div class="thumbnail">
                            <c:if test="${not empty images}">
                                <c:forEach var="image" items="${images}">
                                    <img src="${image}" alt="Thumbnail "/>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="section1">
            <div class="container-product-Bt">
                <div class="product-title">
                    ${product.name}
                </div>

                <a href="<%= request.getContextPath() %>/link-Đánh giá" class="product-rating">
                    ★★★☆☆ <span>(<%= "2.6 / 10 đánh giá" %>)</span>
                </a>

                <div class="product-features">
                    <ul>
                        <li>
                            <div>Ưu đãi thêm 5% (đến 1 TRIỆU đồng) cho đơn hàng đầu tiên từ 8 triệu</div>
                            <div>Thanh toán online trả trước giảm thêm 2%</div>
                            <div>Giảm thêm 3% khi mua từ 2 - 5 sản phẩm điện gia dụng, điều hòa, TV, điện thoại (trừ phụ
                                kiện)
                            </div>
                            <div>Tích thêm 2% điểm Rewards Samsung</div>
                            <div>Giảm thêm 10% tối đa 1 triệu khi nhập mã SSDDeal. Áp dụng cho đơn hàng shoppapp hệ điều
                                hành iOS
                                đến hết ngày 07/11
                            </div>
                            <div>Liên hệ với tư vấn viên online để biết thêm chi tiết về ưu đãi hoặc để nhận hướng dẫn
                                mua
                                hàng.
                            </div>
                        </li>
                        <!-- Lặp qua danh sách descriptions -->
                        <c:forEach var="desc" items="${descriptions}">
                            <li>${desc}</li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="option-title">Chọn Màu Sắc</div>
                <div class="color-options">
                    <div class="color-option black"></div>
                    <div class="color-option silver"></div>
                </div>

                <div class="option-title">Chọn Dung Tích</div>
                <div class="capacity-options">
                    <div class="capacity-option">305 L</div>
                    <div class="capacity-option">348 L</div>
                    <div class="capacity-option">345 L</div>
                </div>
<%--                <div class="price">--%>
<%--                    <%= "23.000.000 VND" %>--%>
<%--                </div>--%>
                <div class="price">
                    <c:choose>
                        <c:when test="${not empty product.price}">
                            ${product.price} VND
                        </c:when>
                        <c:otherwise>
                            Giá không khả dụng
                        </c:otherwise>
                    </c:choose>
                </div>

                <!--    <button class="btn-add-to-cart">Thêm vào giỏ hàng</button>-->
            </div>
        </div>
        <div>
            <a href="#">
                <button></button>
            </a>
        </div>

        <a href="#">
            <button class="btn-add-to-cart btn add">Thêm vào giỏ hàng</button>
        </a>
    </div>

    <div class="summary__list">
        <div class="summary__item">
            <img src="${pageContext.request.contextPath}/static/image/img-detail/icon1.jpg" alt="Icon 1"
                 class="summary__icon">
            <p class="summary__text">Ngăn đông mềm linh hoạt Optimal Fresh+</p>
        </div>
        <div class="summary__divider"></div>
        <div class="summary__item">
            <img src="${pageContext.request.contextPath}/static/image/img-detail/icon2.jpg" alt="Icon 2"
                 class="summary__icon">
            <p class="summary__text">+20L dung tích với SpaceMax™</p>
        </div>
        <div class="summary__divider"></div>
        <div class="summary__item">
            <img src="${pageContext.request.contextPath}/static/image/img-detail/icon3.jpg" alt="Icon 3"
                 class="summary__icon">
            <p class="summary__text">Hệ thống lọc than hoạt tính</p>
        </div>
    </div>
    <div class="Section-Pt">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/section1.jpg" alt="Section1">
    </div>
    <div class="text1">
        <h2>Thiết kế phẳng hiện đại, hoàn hảo mọi gian bếp</h2>
        <p>Nâng tầm không gian bếp với thiết kế thời thượng từ tủ lạnh Samsung thế hệ mới. Thiết kế phẳng giảm thiểu chi
            tiết đem lại sự tao nhã sang trọng, cùng chất liệu cao cấp bền đẹp theo thời gian.</p>
    </div>
    <div class="carousel-container">
        <!-- Main Image Display -->
        <div class="carousel-slide">
            <button class="carousel-button left" onclick="prevSlide()">❮</button>
            <img id="carousel-image" src="${pageContext.request.contextPath}/static/image/img-detail/carousel1.jpg"
                 alt="Carousel Image" class="carousel-image">
            <button class="carousel-button right" onclick="nextSlide()">❯</button>
        </div>
        <!-- Carousel Navigation Dots -->
        <div class="carousel-dots">
            <span class="dot active" onclick="showSlide(0)"></span>
            <span class="dot" onclick="showSlide(1)"></span>
            <span class="dot" onclick="showSlide(2)"></span>
        </div>
    </div>

    <!-- Feature benefit -->
    <div class="feature-benefit">
        <div class="feature-benefit__text">
            <h2>Ngăn đông mềm linh hoạt 4 chế độ Optimal Fresh+</h2>
            <h3>Linh hoạt chuyển đổi 4 chế độ theo nhu cầu</h3>
            <p>Với 4 chế độ làm lạnh linh hoạt giúp bạn dễ dàng chuyển đổi theo từng nhu cầu bảo quản:
                "Làm mát" (cho thực phẩm dùng hằng ngày như sữa, thịt nguội…);
                "Thịt & Cá";
                "Đông mềm" (lưu trữ thực phẩm dài ngày hơn);
                "Làm lạnh nhanh" cho đồ uống.
            </p>
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

    <div class="iframe-container">
        <jsp:include page="Product-Feature.jsp"/>
    </div>

    <div class="feature-benefit4__text">
        <h2>Tiết kiệm điện năng, vận hành bền bỉ</h2>
        <h3>Máy nén Digital Inverter</h3>
        <p>Máy nén biến tần kỹ thuật số Digital Inverter tự động điều chỉnh tốc độ vận hành tùy theo nhu cầu làm lạnh.
            Nhờ
            đó, tủ lạnh tiết kiệm điện năng tốt hơn, giảm thiểu tiếng ồn và hạn chế hao mòn động cơ để kéo dài tuổi thọ
            máy,
            đồng thời được bảo hành đến 20 năm*.</p>
    </div>

    <div class="img8">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image8.jpg" alt="image8" height="700"
             width="1200"/>
    </div>

    <div class="specification-container">
        <h1>Thông số kỹ thuật</h1>
        <div class="specs">
            <div class="spec-item">
                <p>Dung tích tổng (Liter)</p>
                <h2>285 ℓ</h2>
            </div>
            <div class="spec-item">
                <p>Chiều rộng (mm)</p>
                <h2>595 mm</h2>
            </div>
            <div class="spec-item">
                <p>Chiều cao thực tế có bản lề (mm)</p>
                <h2>1580 mm</h2>
            </div>
            <div class="spec-item">
                <p>Chiều sâu với tay cầm cửa (mm)</p>
                <h2>663 mm</h2>
            </div>
            <div class="spec-item">
                <p>Khối lượng thực (kg)</p>
                <h2>56.5 kg</h2>
            </div>
            <div class="spec-item">
                <p>Công nghệ làm lạnh</p>
                <h2>All Around Cooling</h2>
            </div>
        </div>
        <button id="toggle-specs-btn">Xem thêm</button>
    </div>
    <!-- Thêm thông số kỹ thuật -->
    <div id="specification-section" class="specification" style="display: none;">

        <div class="section">
            <h2>Dung Tích</h2>
            <div class="section-item">
                <p>Dung tích tổng (Liter</p>
                <span>285 ℓ</span>
            </div>
        </div>
        <div class="section">
            <h2>Đặc điểm kỹ thuật</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Chiều rộng (mm)</p>
                    <span>595 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều cao thực có bản lề (mm)</p>
                    <span>1580 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều cao thực không có bản lề (mm)</p>
                    <span>1560 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều sâu với tay cầm cửa (mm)</p>
                    <span>663 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều sâu không có tay cầm cửa (mm)</p>
                    <span>663 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều sâu không có cửa (mm)</p>
                    <span>590 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều ngang nguyên kiện (mm)</p>
                    <span>629 mm</span>
                </div>
                <div class="spec-item">
                    <p>Chiều cao nguyên kiện (mm)</p>
                    <span>1665 mm</span>
                </div>
                <div class="spec-item">
                    <p>Độ sâu nguyên kiện (mm)</p>
                    <span>704 mm</span>
                </div>
                <div class="spec-item">
                    <p>Khối lượng thực (kg)</p>
                    <span>56.5 kg</span>
                </div>
                <div class="spec-item">
                    <p>Khối lượng nguyên kiện (kg)</p>
                    <span>60.5 kg</span>
                </div>
                <div class="spec-item">
                    <p>20/40/40H (Container)</p>
                    <span>37 / 78 / 78</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Tính năng làm lạnh</h2>
            <div class="section-item">
                <p>Công nghệ làm lạnh</p>
                <span>All Around Cooling</span>
            </div>
        </div>

        <div class="section">
            <h2>Đặc điểm ngăn lạnh</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Bộ lọc khử mùi</p>
                    <span>Yes</span>
                </div>
                <div class="spec-item">
                    <p>Spill Proof</p>
                    <span>Yes</span>
                </div>
                <div class="spec-item">
                    <p>Spill Proof</p>
                    <span>Yes</span>
                </div>
                <div class="spec-item">
                    <p>Đèn LED bên trong</p>
                    <span>LED</span>
                </div>
                <div class="spec-item">
                    <p>Ngăn chứa rau củ quả</p>
                    <span>1 EA</span>
                </div>
                <div class="spec-item">
                    <p>Số lượng kệ</p>
                    <span>3 EA</span>
                </div>
                <div class="spec-item">
                    <p>Số lượng khay chứa</p>
                    <span>2 EA</span>
                </div>
                <div class="spec-item">
                    <p>Ngăn đông mềm Optimal Fresh+</p>
                    <span>Yes</span>
                </div>
                <div class="spec-item">
                    <p>Chất liệu khay kệ</p>
                    <span>Tempered Glass</span>
                </div>
                <div class="spec-item">
                    <p>Làm lạnh nhanh Power Cool</p>
                    <span>Yes</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Đặc điểm ngăn đông</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Số lượng kệ</p>
                    <span>1 EA</span>
                </div>
                <div class="spec-item">
                    <p>Icemaker</p>
                    <span>Ice Tray (Include Case)</span>
                </div>
                <div class="spec-item">
                    <p>Ngăn chứa</p>
                    <span>1 EA</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Tính năng chung</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Chất làm lạnh</p>
                    <span>R600a</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Đặc điểm ngoại thất</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Hiển thị</p>
                    <span>Internal (Ice Blue)</span>
                </div>
                <div class="spec-item">
                    <p>Màu sắc</p>
                    <span>Đen Starry</span>
                </div>
                <div class="spec-item">
                    <p>Loại tay cầm</p>
                    <span>Recess</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Công suất</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Climate Class</p>
                    <span>SN,N,ST,T</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Năng lượng</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Energy Star Rating</p>
                    <span>5 Star</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Category</h2>
            <div class="section-content specs-grid">
                <div class="spec-item">
                    <p>Refrigerator Type</p>
                    <span>BMF</span>
                </div>
            </div>
        </div>
        <button id="toggle-specs-btn-bottom">Ẩn tất cả các đặc tả</button>
    </div>

    <footer>
        <div class="footer">
            <iframe src="${pageContext.request.contextPath}/Product-detail/footer.jsp" class="iframe"></iframe>
        </div>
    </footer>

    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail.js"></script>
</body2>
</html>
