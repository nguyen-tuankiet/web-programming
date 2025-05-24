<%--
  Created by IntelliJ IDEA.
  User: win10pro
  Date: 5/21/2025
  Time: 2:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-admin/review.css">
</head>
<body>

<div class="container">
    <div class ="left">
        <div class="side_bar">
            <jsp:include page="SideBar.jsp"/>
        </div>

    </div>


    <div class="center">
        <div class="wrap_header">
            <jsp:include page="Header.jsp"/>
        </div>

        <div class=" wrap">
            <div class="main-content">

                <div class="page-header">
                    <h1 class="page-title">Quản lý đánh giá sản phẩm</h1>
                    <button class="btn btn-primary">Xuất báo cáo</button>
                </div>

                <div class="stat-cards">
                    <div class="stat-card">
                        <div class="stat-title">Tổng số đánh giá</div>
                        <div class="stat-value">247</div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-title">Đánh giá mới (tháng 5)</div>
                        <div class="stat-value">32</div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-title">Đánh giá trung bình</div>
                        <div class="stat-value">4.6</div>
                    </div>


                </div>

                <div class="filter-bar">
                    <div class="filter-group">
                        <select class="filter-select" disabled>
                            <option>Tất cả sản phẩm</option>
                        </select>

                        <select class="filter-select" disabled>
                            <option>Tất cả đánh giá</option>
                            <option>5 sao</option>
                            <option>4 sao</option>
                            <option>3 sao</option>
                            <option>2 sao</option>
                            <option>1 sao</option>
                        </select>

                        <select class="filter-select" disabled>
                            <option>Trạng thái: Tất cả</option>
                            <option>Đã phê duyệt</option>
                            <option>Chờ phê duyệt</option>
                            <option>Bị từ chối</option>
                        </select>
                    </div>

                </div>

                <div class="reviews-table">
                    <table>
                        <thead>
                        <tr>
                            <th>Sản phẩm</th>
                            <th>Khách hàng</th>
                            <th>Đánh giá</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="product-cell">
                                    <img src="/api/placeholder/100/100" alt="Tủ lạnh" class="product-image">
                                    <div>
                                        <div class="product-name">Tủ Lạnh Bespoke 4 Cửa AI Family Hub™</div>
                                        <div class="product-category">Tủ lạnh</div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="customer-cell">
                                    <div class="customer-avatar">T</div>
                                    <div>
                                        <div>Trần Văn Minh</div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="rating">
                                    <div class="stars">★★★★★</div>
                                </div>
                                <div class="review-text">
                                    Tủ lạnh này rất thông minh, tính năng AI giúp tiết kiệm điện đáng kể. Không gian bên trong rộng rãi và thiết kế bên ngoài rất sang trọng, phù hợp với không gian bếp của gia đình tôi.
                                </div>
                            </td>
                        </tr>



                        </tbody>
                    </table>
                </div>

                <div class="pagination">
                    <div class="page-buttons">
                        <button class="page-btn">«</button>
                        <button class="page-btn active">1</button>
                        <button class="page-btn">2</button>
                        <button class="page-btn">3</button>
                        <button class="page-btn">»</button>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<script>
    document.addEventListener('click', function(e) {
        e.preventDefault();
    });
</script>
</body>
</html>
