<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/12/25
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User SideBar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-user_profile/UserSideBar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">

</head>
<body>
<div id="side_bar" class="container  col">
    <div class=" infor row mid_align  ">
        <div class="profile_avatar">
            <img src="${pageContext.request.contextPath}/static/image/medium%20(1).png" height="500" width="500"/>
        </div>
        <div class="name">
            <span>Jackie Chan</span>
        </div>

    </div>

    <div class="rec_horizontal"></div>

    <div class=" wrap_item  col">

        <div class="item  ">

            <i class="fa-solid fa-user-large"></i>
            <span>Tài Khoản</span>
            <i class="fa-solid fa-caret-down icon_down"></i>

        </div>


        <div class="menu_sub_item  ">
            <ul>
                <li class="sub_items  nav ">
                    <i class="fa-solid fa-address-card"></i>
                    <a href="#" class="item_link" data-src="../component/user_profile_component/UserProfileDetail.html">Hồ
                        sơ</a>
                </li>

                <li class="sub_items nav">
                    <i class="fa-regular fa-credit-card"></i>
                    <a href="#" class="item_link" data-src="../component/user_profile_component/Payment.html">Thanh
                        toán</a>
                </li>

                <li class="sub_items nav">
                    <i class="fa-solid fa-location-dot"></i>
                    <a href="#" class="item_link" data-src="../component/user_profile_component/Address.html">Địa
                        chỉ</a>
                </li>

                <li class="sub_items nav">
                    <i class="fa-solid fa-key"></i>
                    <a href="#" class="item_link" data-src="../component/user_profile_component/Password.html">Bảo
                        mật</a>
                </li>

                <li class="sub_items nav">
                    <i class="fa-regular fa-bell"></i>
                    <a href="#" class="item_link" data-src="../component/admin_components/notifications.html">Thông báo</a>
                </li>
            </ul>
        </div>
    </div>


    <div class="item nav ">
        <i class="fa-solid fa-cart-shopping"></i>
        <span>  <a href="#" class="item_link"  data-src="../component/user_order/UserOrder.html">Đơn hàng</a></span>

    </div>


    <div class="item  ">
        <i class="fa-brands fa-rocketchat"></i>
        <span>  <a href="#" class="item_link"  data-src="../component/user_order/UserOrder.html">Tin nhắn</a></span>

    </div>


</div>

<script src="${pageContext.request.contextPath}/static/style-component/style-user_profile/UserSidebar.js"></script>

</body>
</html>