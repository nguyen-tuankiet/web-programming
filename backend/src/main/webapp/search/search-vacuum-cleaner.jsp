<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<head>
    <title>List Product</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/Search_Product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/TopProduct.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.css">

</head>
<body>
<div class="container">

    <div class="search_header">
        <jsp:include page="../home/header.jsp"/>
    </div>

    <div id="banner">
        <%--    <iframe src = "../home/banner.html"></iframe>--%>
        <jsp:include page="../home/banner.jsp"/>
    </div>

    <div id="body" class="row">
        <div id="sidebar">

            <div class="wrap_title">
                <div class="title f18 ">
                    <i class="fa-solid fa-filter"></i>
                    Bộ lọc
                </div>

                <button id="apply_btn"> Áp dụng</button>

            </div>

            <!---------------------------- Default ------------------------------------------------>

            <div class="section_price section_item col">

                <div class="title">Mức giá</div>

                <div class="item mid_align">
                    <input type="checkbox" id="price1">
                    <label for="price1">Từ 5-8 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price2">
                    <label for="price2">Từ 8-12 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price3">
                    <label for="price3">Từ 12-15 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price4">
                    <label for="price4">Từ 15-20 triệu</label>
                </div>

                <div class="item">
                    <input type="checkbox" id="price5">
                    <label for="price5">Trên 20 triệu</label>
                </div>


            </div>

            <div class="rec_horizontal"></div>


            <!---------------------------------------------------------------------------->

            <c:if test="${not empty variants}">
                <c:forEach var="var" items="${variants}">

                    <div class="section_type section_item col">
                        <div class="title">${var.name}</div>


                        <c:if test="${not empty var.variantValues}">
                            <c:forEach var="value" items="${var.variantValues}">
                                <div class="item mid_align">
                                    <input type="checkbox" id="type1">
                                    <label for="type1">${value.value}</label>

                                </div>

                            </c:forEach>
                        </c:if>

                    </div>

                    <div class="rec_horizontal"></div>

                </c:forEach>


            </c:if>


        </div>

</div>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.js"></script>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/Search_Product.js"></script>

</body>
