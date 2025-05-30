<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/28/24
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<head>
    <title>Title</title>
    <%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/sytle-cart/CartItem.css">--%>
    <%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">--%>
</head>
<body>

<div class="wrap mid_align row">
    <input type="checkbox">
    <div class="image">
                <img src="${pageContext.request.contextPath}/static/image/default_img.jpg" alt=""/>

    </div>


    <div class="description mid_align col  ">
        <div class="title ">${item.name}</div>

        <div class="color">
            <span class="color_name">Màu Sắc: <span>Đen</span></span>
        </div>

        <div class="status">
            <span class="status_type">Còn hàng</span>
        </div>

    </div>


    <div class="section_price mid_align col  ">
        <span class="price">  <fmt:formatNumber value="${item.price}" pattern="#,###"/> VND</span>


        <div class="quantity mid_align row">
            <i class="fa-solid fa-minus"></i>
            <span class="num mid_align">
                ${item.quantity}
            </span>
            <i class="fa-solid fa-plus"></i>

        </div>

        <div class="remove">
            <i class=" del fa-solid fa-trash-can"></i>
        </div>


    </div>


</div>

</body>
