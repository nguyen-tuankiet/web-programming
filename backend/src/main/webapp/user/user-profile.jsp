<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/12/25
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_profile/UserProfileDetail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-page/user/UserProfile.css">
</head>
<body>

<div class="header">
    <jsp:include page="/home/header.jsp"/>
</div>

<div id="body" class="mid_align">
<div class="container mid_align wrap_body">

<div class="side_bar">
    <jsp:include page="user-sidebar.jsp"/>
</div>

<div class="content">

<div id="head_title" class=" col mid_align">
    <div class="header_container mid_align  col">
        <span class="title f22 ">Hồ sơ</span>
        <span class="description">Quản lý thông tin hồ sơ để giữ an toàn cho tài khoản của bạn</span>
    </div>
</div>


<c:if test="${not empty user}">
    <div id="content_body">
    <div class="body_container row">

    <div class="info_left">

    <div class="form_infor row mid_align">
    <div class="avatar">
    <img id="avatar" src="${pageContext.request.contextPath}/static/image/medium%20(1).png" height="500"
    width="500"/>
    <i id="btn_upload" class="fa-solid fa-camera"></i>
    <input type="file" name="" id="upload_avatar" accept="image/*" style="display: none;"/>
    </div>

    <div class="base_infor col">
    <span class=name_title>Họ và Tên :</span>

    <c:if test="${not empty user.fullName}">
        <input type="text" class="name" placeholder="Full Name" value="${user.fullName}">
    </c:if>

    <span class=name_title>Tên hiển thị :</span>
    <c:if test="${not empty user.displayName}">
        <input type="text" class="name" placeholder="Display name" value="${user.displayName}">
    </c:if>

        <div class="gender row ">
            <div class="gender_title">
                <span>Giới tính :</span>
            </div>

            <div class="gender_radio mid_align">
                <label>
                    <c:if  test="${user.gender}"
                    <input type="radio" name="gender" value="male" checked>
                    Nam
                </label>
                <label>
                    <input type="radio" name="gender" value="female">
                    Nữ
                </label>
                <label>
                    <input type="radio" name="gender" value="other">
                    Khác
                </label>
            </div>
        </div>
        </div>


        </div>

        <div class="personal_infor">
            <div class="birth row">
                <div class="birth_title">
                    <span>Ngày sinh : </span>
                </div>

                <div class="birth_form mid_align">
                    <div class="col">
                        <select id="day">
                            <option value="" disabled selected>Ngày</option>
                        </select>
                    </div>

                    <div class="col">
                        <select id="month">
                            <option value="" disabled selected>Tháng</option>
                        </select>
                    </div>

                    <div class="col">
                        <select id="year">
                            <option value="" disabled selected>Năm</option>
                        </select>
                    </div>
                </div>

            </div>

        </div>

        <div class="mid_align bottom">
            <button class="save_btn">
                Lưu
            </button>
        </div>


        </div>


        <div class="info_right">

            <!-- Contact infor -->
            <div class="contact col ">
                <span>Thông tin liên hệ</span>

                <div class="contact_item row mid_align">
                    <i class="fa-solid fa-phone"></i>
                    <span class="item_text">0399989979 </span>
                    <button type="button" class="update_btn">Cập nhật</button>
                </div>

                <div class="contact_item row mid_align">
                    <i class="fa-regular fa-envelope"></i>
                    <span class="item_text ">qhung.fw@gmail.com </span>
                    <button type="button" class="update_btn">Cập nhật</button>
                </div>


            </div>


            <!-- Social Link-->
            <div class="contact social col ">
                <span>Liên kết</span>

                <div class="contact_item row mid_align">
                    <img src="${pageContext.request.contextPath}/static/image/facebook.svg" alt="">
                    <span class="item_text mid_align">Facebook </span>
                    <button type="button" class="update_btn">Liên kết</button>
                </div>

                <div class="contact_item row mid_align">
                    <img src="${pageContext.request.contextPath}/static/image/google.svg" alt="">
                    <span class="item_text ">Google</span>
                    <button type="button" class="update_btn">Xóa</button>
                </div>

                <div class="contact_item row mid_align">
                    <img src="${pageContext.request.contextPath}/static/image/Zalo.png" alt="">
                    <span class="item_text ">Zalo</span>
                    <button type="button" class="update_btn">Xóa</button>
                </div>


            </div>


        </div>

        </div>
        </div>
    </c:if>


    <c:if test="${empty user}">
        <p>User Not found </p>
    </c:if>


    </div>


    </div>
    </div>


    <div id="footer"></div>


    <script src="${pageContext.request.contextPath}/static/style-page/user/UserProfile.js"></script>
    <script src="${pageContext.request.contextPath}/static/style-component/style-user_profile/UserProfileDetail.js"></script>


    </body>
    </html>
