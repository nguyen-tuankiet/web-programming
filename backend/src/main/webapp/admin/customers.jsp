<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-admin/customers/customer.css">
</head>
<body>


<div class="container">


    <div class="left">
        <div class="side_bar">
            <jsp:include page="SideBar.jsp"/>
        </div>
    </div>


    <div class="center">
        <div class="wrap_header">
            <jsp:include page="Header.jsp"/>
        </div>


        <div class="content">
            <h1 class="header-title">Khách Hàng</h1>
            <div class="action-buttons">

                <button class="export">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" class="size-6">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M12 16.5V9.75m0 0 3 3m-3-3-3 3M6.75 19.5a4.5 4.5 0 0 1-1.41-8.775 5.25 5.25 0 0 1 10.233-2.33 3 3 0 0 1 3.758 3.848A3.752 3.752 0 0 1 18 19.5H6.75Z"/>
                    </svg>
                    Xuất
                </button>
                <div class="more-options">
                    Tùy Chọn
                </div>

            </div>


            <div class="table-container">
                <div class="search-bar">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" class="size-6">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"/>
                    </svg>
                    <input type="text" placeholder="Tìm kiếm">
                </div>
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox"></th>
                        <th>Khách Hàng</th>
                        <th>Số Điện Thoại</th>
                        <th>Email</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty customers}">
                        <c:forEach items="${customers}" var="c">
                            <tr class="order-row">
                                <td><input type="checkbox"></td>
                                <td>
                                    <a href="customer?id=${c.id}" class="name">
                                        <c:if test="${not empty c.avatarUrl}">
                                            <img src="${c.avatarUrl}" alt="Avatar"
                                                 style="width:50px; height:50px; border-radius:50%; margin-right:10px;">
                                        </c:if>

                                        <c:if test="${empty c.avatarUrl}">
                                            <img src="${pageContext.request.contextPath}/static/image/default-avatar.png" alt="Avatar"
                                                 style="width:50px; height:50px; border-radius:50%; margin-right:10px;">
                                        </c:if>

                                            ${c.fullName}
                                    </a>
                                </td>
                                <td> ${c.phone}</td>
                                <td>${c.email} </td>
                             </tr>
                        </c:forEach>

                    </c:if>
                    </tbody>


                </table>
            </div>
            <div class="footer-container">
                <nav class="mt-2 mt-md-0">
                    <ul class="pagination mb-0">
                        <li class="page-item"><a class="page-link" href="#!" data-action="prev">Quay lại</a></li>
                        <li class="page-item"><a class="page-link active" href="#!">1</a></li>
                        <li class="page-item"><a class="page-link" href="#!">2</a></li>
                        <li class="page-item"><a class="page-link" href="#!">3</a></li>
                        <li class="page-item"><a class="page-link" href="#!" data-action="next">Tiếp theo</a></li>
                    </ul>
                </nav>
            </div>
        </div>


    </div>

</div>


<script src="${pageContext.request.contextPath}/static/style-component/style-admin/customers/customer.js"></script>
</body>
</html>
