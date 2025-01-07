<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Carousel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.css">
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.js" defer></script>
</head>
<body>

<!-- Carousel Container -->
<div class="carousel-container">
    <!-- Main Image Display -->
    <img id="mainImage"
         src="<c:choose>
                <c:when test='${not empty images}'>
                    ${images[0].url}
                </c:when>
                <c:otherwise>
                    ${pageContext.request.contextPath}/static/images/placeholder.jpg
                </c:otherwise>
              </c:choose>"
         alt="Carousel Image"
         class="carousel-image"
         data-context-path="${pageContext.request.contextPath}">

    <!-- Navigation Arrows -->
    <div class="nav-arrow left" onclick="prevImage()">&#10094;</div>
    <div class="nav-arrow right" onclick="nextImage()">&#10095;</div>

    <!-- Thumbnails -->
    <div class="thumbnails">
        <c:forEach var="image" items="${images}" varStatus="status">
            <img src="${image.url}"
                 alt="Thumbnail ${status.index + 1}"
                 class="thumbnail <c:if test='${status.index == 0}'>active</c:if>"
                 onclick="showImage(${status.index})">
        </c:forEach>
    </div>
</div>

</body>
</html>
