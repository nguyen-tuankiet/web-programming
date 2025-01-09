<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/9/25
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-user_order/OrderSuccess.css">
</head>
<body>

<div id="header" class="mid_align row">


  <div class="cart_header">
    <jsp:include page="/home/header.jsp"/>
  </div>


</div>

<div class="container mid_align  col">
  <div class="title">Đặt hàng thành công</div>
  <div class="image">
    <img alt="" src="${pageContext.request.contextPath}/static/image/delivery.jpg" height="7730" width="7730"/>
  </div>


  <a  id="btn"  href="home"   >Tiếp tục mua sắm</a>

</div>

</body>
</html>
