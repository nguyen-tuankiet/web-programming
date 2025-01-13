<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 12/27/2024
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Product Detail</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/review.css"/>
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/review.js"></script>
</head>
<body>
    <div class="cart_header">
        <jsp:include page="/home/header.jsp"/>
    </div>
    <div class="container">
        <div class="section1">
            <div class="carousel-container">


                <div class="carousel-container">

                    <img id="mainImage" src="${primaryImageUrl}" alt="Carousel Image" class="carousel-image">
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
            <div  id="product" data-id="${product.id}" data-option-default="${product.optionId}" class="container-product-Bt">
                <div class="product-title">
                    ${product.name}
                </div>


                <%--   VARIANT              --%>
                <c:if test="${not empty optionVariant  && not empty varaints}">
                    <c:forEach items="${varaints}" var="type">

                        <div class="wrap_variant ">
                            <div class="option-title">Chọn ${type}:</div>

                            <c:forEach items="${optionVariant}" var="op">
                                <c:if test="${op.variantName eq type  }">
                                    <div class="option-item" data-option-id="${op.id}"  data-price="${op.price}"> ${op.variantValue}</div>
                                </c:if>
                            </c:forEach>
                        </div>

                    </c:forEach>

                </c:if>




                <div id="price" class="price">
                    <c:choose>
                        <c:when test="${not empty productPrice}">

                            <fmt:formatNumber value="${productPrice}" pattern="#,###"/> VND
                        </c:when>
                        <c:otherwise>
                            Đang câp nhật
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="product-features">
                    <ul>

                        <!-- Lặp qua danh sách descriptions -->
                        <c:forEach var="desc" items="${descriptions}">
                            <li>${desc}</li>
                        </c:forEach>
                    </ul>
                </div>


<%--                <div class="button-group">--%>
<%--                    <a id="add-to-cart" href="#" onclick="addToCart(${product.id},${product.optionId})">--%>
<%--                        <button class="btn-add-to-cart btn add">Thêm vào giỏ hàng</button>--%>
<%--                    </a>--%>

<%--                    <a id="buy-now" href="buy-now?productId=${product.id}&optionId=${product.optionId}">--%>
<%--                        <button class="btn-buy-now btn buy"> Mua ngay </button>--%>
<%--                    </a>--%>
<%--                </div>--%>

                <div class="button-group">
                    <a id="add-to-cart" href="#">
                        <button class="btn-add-to-cart btn add">Thêm vào giỏ hàng</button>
                    </a>

                    <a id="buy-now" href="#">
                        <button class="btn-buy-now btn buy"> Mua ngay </button>
                    </a>
                </div>


            </div>
        </div>

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
        <p>Nâng tầm không gian bếp với thiết kế thời thượng từ tủ lạnh Samsung thế hệ mới. Thiết kế phẳng giảm thiểu
            chi
            tiết đem lại sự tao nhã sang trọng, cùng chất liệu cao cấp bền đẹp theo thời gian.</p>
    </div>
    <div class="carousel-container">
        <!-- Main Image Display -->
<%--        <div class="carousel-slide">--%>
<%--            <button class="carousel-button left" onclick="prevSlide()">❮</button>--%>
<%--            <img id="carousel-image" src="${pageContext.request.contextPath}/static/image/img-detail/carousel1.jpg"--%>
<%--                 alt="Carousel Image" class="carousel-image">--%>
<%--            <button class="carousel-button right" onclick="nextSlide()">❯</button>--%>
<%--        </div>--%>
        <!-- Carousel Navigation Dots -->
<%--        <div class="carousel-dots">--%>
<%--            <span class="dot active" onclick="showSlide(0)"></span>--%>
<%--            <span class="dot" onclick="showSlide(1)"></span>--%>
<%--            <span class="dot" onclick="showSlide(2)"></span>--%>
<%--        </div>--%>
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
        <p>Máy nén biến tần kỹ thuật số Digital Inverter tự động điều chỉnh tốc độ vận hành tùy theo nhu cầu làm
            lạnh.
            Nhờ
            đó, tủ lạnh tiết kiệm điện năng tốt hơn, giảm thiểu tiếng ồn và hạn chế hao mòn động cơ để kéo dài tuổi
            thọ
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

    <div class="write-review-section">
        <h2>Viết đánh giá của bạn</h2>
        <form id="review-form" method="post" action="<%=request.getContextPath()%>/submitReview">
            <div class="input-group">
                <label for="rating-value">Đánh giá:</label>
                <div id="user-rating" class="stars">
                    <span data-value="1">☆</span>
                    <span data-value="2">☆</span>
                    <span data-value="3">☆</span>
                    <span data-value="4">☆</span>
                    <span data-value="5">☆</span>
                </div>
                <input type="hidden" id="rating-value" name="rating" value="0">
            </div>

            <div class="input-group">
                <label for="review-text">Viết đánh giá:</label>
                <textarea id="review-text" name="review" rows="4" placeholder="Chia sẻ suy nghĩ của bạn" required></textarea>
            </div>
            <div class="custom-file-upload">
                <label for="review-photo" class="file-label">+ Chọn hình ảnh</label>
                <input type="file" id="review-photo" name="photo" accept="image/*" multiple>
            </div>
            <div id="photo-preview" class="photo-preview"></div>

            <button type="submit">Gửi đánh giá</button>
        </form>
    </div>

    <div class="rating-section">
        <div class="rating-details">
            <h1>Đánh giá</h1>
            <p>Kết xuất nhanh xếp loại</p>
            <div class="rating-bar">
                <span>5 sao</span>
                <div><span style="width: 10%;"></span></div>
                <span>1</span>
            </div>
            <div class="rating-bar">
                <span>4 sao</span>
                <div><span style="width: 0%;"></span></div>
                <span>0</span>
            </div>
            <div class="rating-bar">
                <span>3 sao</span>
                <div><span style="width: 0%;"></span></div>
                <span>0</span>
            </div>
            <div class="rating-bar">
                <span>2 sao</span>
                <div><span style="width: 0%;"></span></div>
                <span>0</span>
            </div>
            <div class="rating-bar">
                <span>1 sao</span>
                <div><span style="width: 90%;"></span></div>
                <span>6</span>
            </div>
        </div>
        <div class="rating-summary">
            <div class="overall-rating">Xếp hạng tổng thể</div>
            <div class="stars">4.6 ★★★★☆</div>
            <a href="#">7 đánh giá</a>
            <p>0 trong số 6 (0%) người đánh giá giới thiệu sản phẩm này</p>
        </div>
        <div class="rating-input">
            <p>Đánh giá sản phẩm này</p>
            <div class="stars">
                <span>☆</span>
                <span>☆</span>
                <span>☆</span>
                <span>☆</span>
                <span>☆</span>
            </div>
            <p>Thêm đánh giá sẽ yêu cầu một email hợp lệ để xác minh</p>
        </div>
    </div>

    <div class="detailed-rating-section">
        <h2>Xếp loại khách hàng trung bình</h2>
        <div class="criteria">
            <div class="criteria-item">
                <span>Tính năng</span>
                <div class="criteria-bar">
                    <span style="width: 10%;"></span>
                </div>
            </div>
            <div class="criteria-item">
                <span>Hiệu năng</span>
                <div class="criteria-bar">
                    <span style="width: 20%;"></span>
                </div>
            </div>
            <div class="criteria-item">
                <span>Thiết kế</span>
                <div class="criteria-bar">
                    <span style="width: 30%;"></span>
                </div>
            </div>
        </div>
    </div>

    <h2>Hình ảnh và video của khách hàng</h2>
    <div class="customer-images">
<%--        <img src="<%=request.getContextPath()%>/resource/image/pc-achomepage.jpg" alt="Ảnh khách hàng">--%>
<%--        <img src="<%=request.getContextPath()%>/resource/image/pc-achomepage.jpg" alt="Ảnh khách hàng">--%>
<%--        <img src="<%=request.getContextPath()%>/resource/image/pc-achomepage.jpg" alt="Ảnh khách hàng">--%>
<%--        <img src="<%=request.getContextPath()%>/resource/image/pc-achomepage.jpg" alt="Ảnh khách hàng">--%>
<%--        <img src="<%=request.getContextPath()%>/resource/image/pc-achomepage.jpg" alt="Ảnh khách hàng">--%>
    <img src="<%=request.getContextPath()%>/static/image/pc-achomepage.jpg" alt="Ảnh khách hàng">
    <img src="<%=request.getContextPath()%>/static/image/pc-achomepage.jpg" alt="Ảnh khách hàng">
    <img src="<%=request.getContextPath()%>/static/image/pc-achomepage.jpg" alt="Ảnh khách hàng">
    <img src="<%=request.getContextPath()%>/static/image/pc-achomepage.jpg" alt="Ảnh khách hàng">
    <img src="<%=request.getContextPath()%>/static/image/pc-achomepage.jpg" alt="Ảnh khách hàng">

    </div>
    <div class="filter-section">
        <h2>Lọc Đánh Giá</h2>
        <input type="text" placeholder="Tìm kiếm chủ đề và đánh giá">
        <div class="filter-controls">
            <select>
                <option>Xếp hạng</option>
                <option>1 sao</option>
                <option>2 sao</option>
                <option>3 sao</option>
                <option>4 sao</option>
                <option>5 sao</option>
            </select>
            <select>
                <option>Bản địa</option>
                <option>Tiếng Việt</option>
                <option>Tiếng Anh</option>
            </select>
        </div>
    </div>

    <div class="review-header">
        <p>1 - 6 / 7 Đánh giá</p>
        <div class="sort-section">
            <span>Đánh giá theo khu vực</span>
            <select>
                <option>Sắp xếp theo</option>
                <option>Khu vực</option>
                <option>Thời gian</option>
                <option>Đánh giá cao nhất</option>
                <option>Đánh giá thấp nhất</option>
            </select>
        </div>
    </div>

    <div class="review-container">
        <h2>Đánh giá theo khu vực</h2>
        <!-- Ví dụ Đánh giá -->
        <div class="review-item">
            <div class="review">
                <img class="avatar" src="<%=request.getContextPath()%>/static/image/medium%20(1).png" alt="User Avatar">
                <div class="review-info">
                    <div class="star-rating">★★★★</div>
                    <span class="reviewer-name">Tuan Kiet</span>
                    <span class="time">24 ngày trước</span>
                </div>
            </div>
            <p>Tủ lạnh nhanh đóng tuyết, đã đổi 1 tủ mới và nv samsung đã xuống bảo hành 1 lần. Giờ lại bị tiếp tục. Đèn báo bị chớp không dừng im khi mở tủ.</p>
            <div class="image">
                <img src="<%=request.getContextPath()%>/static/image/1440x640_disclaimer.webp" alt="Product issue photo">
            </div>
            <div class="review-footer">
                <button>Hữu ích? (0)</button>
                <button>Không (0)</button>
                <button>Báo cáo</button>
            </div>
        </div>
    </div>



    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail.js"></script>
</body>
</html>
