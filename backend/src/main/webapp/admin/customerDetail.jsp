
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Detail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/customers/customerDetail.css">

</head>
<body>
<div class="container">


    <div class ="left">
        <div class="side_bar">
            <jsp:include page="SideBar.jsp"/>
        </div>

    </div>

     <div class="center">
         <div class="header">
             <jsp:include page="Header.jsp"/>
         </div>



         <!-- Nội dung chính -->
         <div class="content">
             <h1 class="header-title">Khách Hàng</h1>
             <c:if test="${not empty customer}">
                 <div class="wrap_content">

                     <div class="left-panel">
                         <div class="profile-card">
                             <div class ="info">
                                 <c:if test="${not empty customer.avatarUrl}">
                                     <img src="${customer.avatarUrl}" alt="Avatar"
                                          style="width:50px; height:50px; border-radius:50%; margin-right:10px;">
                                 </c:if>

                                 <c:if test="${empty customer.avatarUrl}">
                                     <img src="${pageContext.request.contextPath}/static/image/default-avatar.png" alt="Avatar"
                                          style="width:50px; height:50px; border-radius:50%; margin-right:10px;">
                                 </c:if>

                                 <h3>${customer.fullName}</h3>
                                 <p>${customer.email}</p>
                                 <p> ${customer.phone}</p>
                             </div>
                             <hr>
                                 <%--                       <div class="wrap_info">--%>
                                 <%--                           <p><strong>Đơn hàng gần nhất:</strong>  <a href="#">#80294</a></p>--%>
                                 <%--                           <p><strong>Giá trị đơn hàng trung bình:</strong> 574.000 VND </p>--%>
                                 <%--                           <p><strong>Đăng ký:</strong> 2 tháng trước</p>--%>
                                 <%--                       </div>--%>
                         </div>
                     </div>

                     <!-- Bảng bên phải -->
                     <div class="right-panel">
                         <!-- Phần Đơn hàng -->
                         <div class="orders">
                             <c:if test="${not empty order}">
                                 <table>
                                     <thead>
                                     <tr>
                                         <th>Đơn hàng</th>
                                         <th>Ngày</th>
                                         <th>Tình trạng</th>
                                         <th>Thanh toán</th>
                                         <th>Tổng cộng</th>
                                     </tr>
                                     </thead>
                                     <tbody>
                                     <c:forEach var="o" items="${order}">
                                         <tr>
                                             <td><a href="order-detail?orderId=${o.id}">#${o.id}</a></td>
                                             <td>${o.createAt}</td>
                                             <td>${o.orderStatus}</td>
                                             <td>${o.paymentStatus}</td>
                                             <td> <fmt:formatNumber value="${o.total}" pattern="#,###"/>  VND</td>
                                         </tr>
                                     </c:forEach>

                                     </tbody>
                                 </table>
                             </c:if>

                             <c:if test="${empty order}">
                                 <p>Chưa có đơn hàng.</p>
                             </c:if>

                         </div>

                         <!-- Phần Địa chỉ -->
                         <div class="addresses">
                             <h4>Địa chỉ</h4>
                             <div class="wrap_info">
                                 <c:if test="${not empty address}">
                                     <c:forEach items="${address}" var="a">
                                         <p> ${a.detail}, ${a.commune} ${a.district} ${a.province} </p>
                                     </c:forEach>
                                 </c:if>

                              </div>


                         </div>
                     </div>
                 </div>
             </c:if>

             <c:if test="${empty customer}">
                 <h1>Đã có lỗi xảy ra</h1>
             </c:if>

         </div>

     </div>


</div>
</body>
</html>
