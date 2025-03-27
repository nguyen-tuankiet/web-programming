<%--
  Created by IntelliJ IDEA.
  User: win10pro
  Date: 12/27/2024
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
             <!-- Bảng bên trái -->
            <div class="wrap_content">

                <div class="left-panel">
                    <div class="profile-card">
                        <div class ="info">
                            <img src="https://dashui.codescandy.com/dashuipro/assets/images/avatar/avatar-11.jpg" alt="Jessica Moore" class="profile-pic">
                            <h3>Nguyễn Tuấn Kiệt</h3>
                            <p>nguyentuankiet@gmail.com</p>
                            <p class="phone">012399999</p>
                        </div>
                        <hr>
                       <div class="wrap_info">
                           <p><strong>Đơn hàng gần nhất:</strong>  <a href="#">#80294</a></p>
                           <p><strong>Giá trị đơn hàng trung bình:</strong> 574.000 VND </p>
                           <p><strong>Đăng ký:</strong> 2 tháng trước</p>
                       </div>
                    </div>
                </div>

                <!-- Bảng bên phải -->
                <div class="right-panel">
                    <!-- Phần Đơn hàng -->
                    <div class="orders">
                         <table>
                            <thead>
                            <tr>
                                <th>Đơn hàng</th>
                                <th>Ngày</th>
                                <th>Tình trạng</th>
                                <th>Số mặt hàng</th>
                                <th>Tổng cộng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><a href="#">#80294</a></td>
                                <td>Hôm nay lúc 6:10 PM</td>
                                <td>Chờ xử lý</td>
                                <td>4</td>
                                <td>320.000 VND</td>
                            </tr>
                            <tr>
                                <td><a href="#">#63736</a></td>
                                <td>15 Tháng 5, 2019</td>
                                <td>Hoàn thành</td>
                                <td>7</td>
                                <td>2,574.311 VND</td>
                            </tr>
                            <tr>
                                <td><a href="#">#63501</a></td>
                                <td>7 Tháng 1, 2019</td>
                                <td>Hoàn thành</td>
                                <td>1</td>
                                <td>34.000 VND</td>
                            </tr>
                            <tr>
                                <td><a href="#">#40278</a></td>
                                <td>19 Tháng 10, 2018</td>
                                <td>Hoàn thành</td>
                                <td>2</td>
                                <td>704.000 VND</td>
                            </tr>
                            </tbody>
                        </table>
                     </div>

                    <!-- Phần Địa chỉ -->
                    <div class="addresses">
                        <h4>Địa chỉ</h4>
                        <p> Quận 1 Thành phố Hồ Chí Minh</p>
                        <p> Quận 2  Thành phố Hồ Chí Minh</p>
                     </div>
                </div>
            </div>
         </div>

     </div>


</div>
</body>
</html>
