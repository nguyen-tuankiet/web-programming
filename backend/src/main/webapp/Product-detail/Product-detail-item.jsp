<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Images</title>
</head>
<body>

<h1>Danh sách Hình Ảnh Sản Phẩm</h1>

<div class="image-gallery">
    <c:if test="${not empty productImages}">
        <ul>
            <c:forEach var="productImage" items="${productImages}">
                <li>
                    <img src="${productImage.imageId}" alt="Image for Product ID: ${productImage.productId}" style="max-width: 200px; margin: 10px;">
                    <p>Image ID: ${productImage.imageId}</p>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty productImages}">
        <p>Không có hình ảnh nào cho sản phẩm này.</p>
    </c:if>
</div>

</body>
</html>
