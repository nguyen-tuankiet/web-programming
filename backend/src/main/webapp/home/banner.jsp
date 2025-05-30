<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/24/24
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/banner.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
<button class="pause-play-btn" id="pausePlayBtn">
  <i class="fas fa-pause"></i>
</button>

<div class="indicator" id="indicator1">
  <div class="progress-loader" >

    <div class="progress"></div>
  </div>
  <div class="progress-loader" >

    <div class="progress"></div>
  </div>
  <div class="progress-loader" >

    <div class="progress"></div>
  </div>
  <div class="progress-loader" >

    <div class="progress"></div>
  </div>
</div>

<%--<div class="banner-container">--%>
<%--  <c:forEach var="banner" items="${bannerList}" varStatus="loop">--%>
<%--    <div class="banner-slide ${loop.first ? 'active' : ''}" style="background-image: url('${banner.imageId}');">--%>
<%--      <div class="overlay"></div>--%>
<%--      <div class="banner-content">--%>
<%--        <h2>Tiêu đề banner ${loop.index + 1}</h2>--%>
<%--        <p>Mô tả nội dung banner ${loop.index + 1}</p>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </c:forEach>--%>

<%--  <button class="nav-btn prev-btn">❮</button>--%>
<%--  <button class="nav-btn next-btn">❯</button>--%>
<%--</div>--%>
<div class="banner-container">
  <div class="banner-slide active" id="slide-1"
       style="background-image: url('${pageContext.request.contextPath}/static/image/1440x640_disclaimer.webp');">
    <div class="overlay"></div>
    <div class="banner-content">
      <h2>Tủ lạnh số 1 Thế giới và Việt Nam</h2>
      <p>Chuyên gia tươi ngon - Dẫn đầu thiết kế - Tiên phong AI</p>
      <!--            <button class="buy-now-btn">Mua ngay</button>-->
    </div>
  </div>

  <div class="banner-slide" id="slide-2" style="background-image: url('${pageContext.request.contextPath}/static/image/pc-1440x810wm (1).jpg');">
    <div class="overlay"></div>
    <div class="banner-content">
      <h2>Máy giặt thông minh</h2>
      <p>Làm sạch hiệu quả - Công nghệ tiên tiến - Tiết kiệm năng lượng</p>
      <!--            <button class="buy-now-btn">Mua ngay</button>-->
    </div>
  </div>

  <div class="banner-slide" id="slide-3" style="background-image: url('${pageContext.request.contextPath}/static/image/pc-achomepage.jpg');">
    <div class="overlay"></div>
    <div class="banner-content">
      <h2>Máy lạnh công nghệ cao</h2>
      <p>Làm lạnh nhanh - Hoạt động êm ái - Đa dạng chức năng</p>
      <!--            <button class="buy-now-btn">Mua ngay</button>-->
    </div>
  </div>

  <div class="banner-slide" id="slide-4" style="background-image: url('${pageContext.request.contextPath}/static/image/pc-1440x810bic.jpg');">
    <div class="overlay"></div>
    <div class="banner-content">
      <h2>Dụng cụ nhà bếp hiện đại</h2>
      <p>Thiết kế tinh tế - Dễ sử dụng - Đảm bảo sức khỏe</p>
      <!--            <button class="buy-now-btn">Mua ngay</button>-->
    </div>
  </div>

  <button class="nav-btn prev-btn">❮</button>
  <button class="nav-btn next-btn">❯</button>


</div>
</body>
<script src="${pageContext.request.contextPath}/static/style-component/style-home/banner.js"></script>
