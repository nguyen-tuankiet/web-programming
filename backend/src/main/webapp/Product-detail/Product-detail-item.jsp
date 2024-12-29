<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 12/27/2024
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Carousel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.css">
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.js"></script>
</head>
<body>

<!-- Carousel Container -->
<div class="carousel-container">
    <!-- Main Image Display -->
    <img id="mainImage"
         src="${pageContext.request.contextPath}/static/image/img-detail/image1.jpg"
         alt="Carousel Image"
         class="carousel-image"
         data-context-path="${pageContext.request.contextPath}">

    <!-- Navigation Arrows -->
    <div class="nav-arrow left" onclick="prevImage()">&#10094;</div>
    <div class="nav-arrow right" onclick="nextImage()">&#10095;</div>

    <!-- Thumbnails -->
    <div class="thumbnails">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image1.jpg" alt="Thumbnail 1" class="thumbnail active" onclick="showImage(0)">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image2.jpg" alt="Thumbnail 2" class="thumbnail" onclick="showImage(1)">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image3.jpg" alt="Thumbnail 3" class="thumbnail" onclick="showImage(2)">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image4.jpg" alt="Thumbnail 4" class="thumbnail" onclick="showImage(3)">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image5.jpg" alt="Thumbnail 5" class="thumbnail" onclick="showImage(4)">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image6.jpg" alt="Thumbnail 6" class="thumbnail" onclick="showImage(5)">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/image7.jpg" alt="Thumbnail 7" class="thumbnail" onclick="showImage(6)">
    </div>
</div>

</body>
</html>



