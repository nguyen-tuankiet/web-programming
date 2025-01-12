<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/12/25
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-user_profile/Address.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
</head>
<body>

<div class="header">
    <jsp:include page="/home/header.jsp"/>
</div>

<div class="container">

    <div class="sidebar">
        <jsp:include page="user-sidebar.jsp"/>
    </div>

    <div class="content">

        <div id="address_header" class="row mid_align">
            <span class="title">Địa Chỉ</span>
            <div class="add_btn mid_align">
                <i class="fa-solid fa-plus"></i>
                <a href="#">Thêm </a>
            </div>
        </div>
        <div id="card_body">

            <div class="address_item row">

                <div class="icon mid_align">
                    <i class="fa-solid fa-house"></i>
                </div>


                <div class="infor">
                    <div class="item_header row mid_align">
                        <span class="name">Jackei Chan</span>
                        <div class="rec_vertical"></div>
                        <span class="phone">03999989970</span>

                        <div class="default">Mặc định</div>

                    </div>


                    <div class="item_body">
                        <div class="address_detail">
                            <span>Số 8, Đường Hàm Nghi </span>
                        </div>

                        <div class="location">
                            <span>Quận 1, TP.HCM </span>
                        </div>

                    </div>
                </div>

                <div class="manage mid_align col">
                    <button class="update_btn">
                        Thay đổi
                    </button>
                    <button disabled class="set_default_btn disabled">
                        Đặt làm mặc định
                    </button>
                    <button class="delete_btn">Xóa</button>
                </div>


            </div>


            <div class="address_item row">

                <div class="icon mid_align">
                    <i class="fa-regular fa-building"></i>
                </div>


                <div class="infor">
                    <div class="item_header row mid_align">
                        <span class="name">Jackei Chan</span>
                        <div class="rec_vertical"></div>
                        <span class="phone">03999989970</span>


                    </div>


                    <div class="item_body">
                        <div class="address_detail">
                            <span>Số 8, Đường Hàm Nghi </span>
                        </div>

                        <div class="location">
                            <span>Quận 1, TP.HCM </span>
                        </div>

                    </div>
                </div>

                <div class="manage mid_align col">
                    <button class="update_btn">
                        Thay đổi
                    </button>
                    <button class="set_default_btn">
                        Đặt làm mặc định
                    </button>
                    <button class="delete_btn">Xóa</button>
                </div>


            </div>


            <div class="address_item row">

                <div class="icon mid_align">
                    <i class="fa-solid fa-house"></i>
                </div>


                <div class="infor">
                    <div class="item_header row mid_align">
                        <span class="name">Jackei Chan</span>
                        <div class="rec_vertical"></div>
                        <span class="phone">03999989970</span>


                    </div>


                    <div class="item_body">
                        <div class="address_detail">
                            <span>Số 8, Đường Hàm Nghi </span>
                        </div>

                        <div class="location">
                            <span>Quận 1, TP.HCM </span>
                        </div>

                    </div>
                </div>

                <div class="manage mid_align col">
                    <button class="update_btn">
                        Thay đổi
                    </button>
                    <button class="set_default_btn">
                        Đặt làm mặc đinh
                    </button>
                    <button class="delete_btn">Xóa</button>
                </div>


            </div>

        </div>

    </div>


</div>



</body>
</html>
