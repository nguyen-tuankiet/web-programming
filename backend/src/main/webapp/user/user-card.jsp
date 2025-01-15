<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 1/12/25
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_profile/Payment.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_profile/CardComponent.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_profile/AddCard.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>


<div class="header">
    <jsp:include page="/home/header.jsp"/>
</div>


<div class="wrap ">
    <div class="side_bar">
        <jsp:include page="user-sidebar.jsp"/>
    </div>

    <div class="content">

        <div id="content_header" class="mid_align row">
            <span class="title">Quản lý thanh toán</span>


            <div class="add_btn mid_align" onclick="openAddCardOverlay()">
                <i class="fa-solid fa-plus"></i>
                <a href="#">Thêm</a>
            </div>

        </div>

        <div id="content_body" class="mid_align">


            <div id="card"
                 style="background-image: url('${pageContext.request.contextPath}/static/image/card_bg.jpg');">
                <div class="overlay"></div>
                <div id="card_header" class="mid_align">

                    <div class="infor col">
                        <div class="card_infor row">
                            <span class="title">Số thẻ :</span>
                            <span class="last4"> **** 9999</span>

                        </div>
                        <span class="card_holder">JACKIE CHAN</span>
                    </div>


                    <div class="type_card">
                        <img src="${pageContext.request.contextPath}/static/image/visa.png" alt="">
                    </div>


                </div>

                <div id="card_body">
                    <div class="card_date">
                        <span> Date: 11/25</span>
                    </div>


                </div>


                <div id="footer" class="mid_align">
                    <div id="default">Mặc định</div>
                    <div class="edit_card mid_align">
                        <a href="#" class="edit_btn">Sửa</a>
                        <a href="#" class="delete_btn">Xóa</a>

                    </div>
                </div>


            </div>


        </div>


        <div id="add-card-overlay">
            <div class="add-card-container">


                <div class="card-form">
                    <h2>Thêm thẻ</h2>
                    <%--                    <label for="name">Tên trên thẻ</label>--%>
                    <%--                    <input type="text" id="name" placeholder="TÊN CHỦ THẺ">--%>

                    <%--                    <label for="card-number">Số thẻ</label>--%>
                    <%--                    <input type="number" id="card-number" placeholder="XXXX XXXX XXXX XXXX">--%>

                    <%--                    <div class="input-group">--%>
                    <%--                        <div>--%>
                    <%--                            <label for="expiry">Ngày hết hạn</label>--%>
                    <%--                            <input type="month" id="expiry" placeholder="XX/XXXX">--%>
                    <%--                        </div>--%>
                    <%--                        <div>--%>
                    <%--                            <label for="cvv">Mã CVV</label>--%>
                    <%--                            <input type="password" id="cvv" placeholder="XXX">--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>

                    <%--                    <div class="checkbox-group">--%>
                    <%--                        <input type="checkbox" id="primary-card">--%>
                    <%--                        <label for="primary-card">Đặt làm thẻ chính</label>--%>
                    <%--                    </div>--%>

                    <%--                    <div class="button-group">--%>
                    <%--                        <button class="close-btn">Đóng</button>--%>
                    <%--                        <button class="save-btn">Lưu thay đổi</button>--%>
                    <%--                    </div>--%>

                    <form id="card_form"  >
                        <label for="name">Tên trên thẻ:</label>
                        <input type="text" id="name" name="name" required><br>

                        <label for="card-number">Số thẻ:</label>
                        <input type="number" id="card-number" name="cardNumber" required><br>

                        <label for="expiry">Ngày hết hạn:</label>
                        <input type="text" id="expiry" name="expiry" placeholder="MM/YYYY" required><br>

                        <label for="cvv">Mã CVV:</label>
                        <input type="password" id="cvv" name="cvv" required><br>

                        <div class="checkbox-group">
                            <input type="checkbox" id="primary-card">
                            <label for="primary-card">Đặt làm thẻ chính</label>
                        </div>

                        <button type="submit">Lưu thẻ</button>
                    </form>

                </div>


                <button class="close-btn" onclick="closeAddCardOverlay()">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        </div>

    </div>
</div>


<script src="${pageContext.request.contextPath}/static/style-component/style-user_profile/AddCard.js"></script>
</body>
</html>
