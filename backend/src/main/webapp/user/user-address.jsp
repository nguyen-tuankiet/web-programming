<%@ page import="com.example.backend.model.User" %>
<%@ page import="com.example.backend.model.Address" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-user_profile/Address.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome/css/all.css">
    <link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet">

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
            <div class="overlay"></div>
            <div id="addAddressFormContainer" style="display: none;">
                <span class="close-icon">&times;</span>

                <h2>Thêm Địa Chỉ</h2>
                <form method="POST" action="add-address">

                    <label for="province" >Tỉnh/Thành phố:<span style="color: red;">*</span></label>
                    <select id="province" required></select>

                    <label for="district">Quận/Huyện:<span style="color: red;">*</span></label>
                    <select id="district" required></select>

                    <label for="commune">Xã/Phường: <span style="color: red;">*</span></label>
                    <select id="commune" required></select>

                    <label for="detail" >Địa chỉ chi tiết:<span style="color: red;">*</span></label>
                    <input type="text" id="detail" placeholder="Nhập địa chỉ chi tiết" required>


                    <label for="name">Tên người nhận:<span style="color: red;">*</span></label>
                    <input type="text" id="name" placeholder="Nhập tên người nhận" required>

                    <label for="phone">Số điện thoại:<span style="color: red;">*</span></label>
                    <input type="text" id="phone" placeholder="Nhập số điện thoại" maxlength="10" required>


                    <div class="radio-group">
                        <label>
                            <input type="radio" name="addressType" value="Home" checked> Nhà riêng
                        </label>
                        <label>
                            <input type="radio" name="addressType" value="Office"> Văn phòng
                        </label>
                    </div>


                    <div class="toggle-switch">
                        <input type="checkbox" id="default">
                        <label for="default" class="slider"></label>
                        <span class="toggle-text">Mặc định</span>
                    </div>

                    <button class="submit-btn" >Xác nhận</button>

                </form>

            </div>


        </div>
        <div id="card_body">
            <%
                User user = (User) request.getAttribute("user");
                List<Address> addresses = (List<Address>) request.getAttribute("addresses");
                if (user != null && addresses != null) {
                    for (Address address : addresses) {
            %>
            <div  class="address_item row" data-id ="<%= address.getId() %>" >
                <div class="icon mid_align">
                    <i class="fa-solid <%= address.getType().equals("house") ? "fa-house" : "fa-building" %>"></i>
                </div>

                <div class="infor">
                    <div class="item_header row mid_align">
                        <span class="name"><%= address.getName() %></span>
                        <div class="rec_vertical"></div>
                        <span class="phone"><%= address.getPhone() %></span>
                        <% if (address.getIsDefault()) { %>
                        <% } %>
                    </div>

                    <div class="item_body">
                        <div class="address_detail">
                            <span><%= address.getDetail() %></span>
                        </div>
                        <div class="location">
                            <span><%= address.getCommune() %>, <%= address.getDistrict() %>, <%= address.getProvince() %></span>
                        </div>
                    </div>
                </div>

                <div class="manage mid_align col">
                    <button class="update_btn">Thay đổi</button>
                    <% if (!address.getIsDefault()) { %>
                    <button class="set_default_btn" onclick= "setDefault('<%= address.getId() %>')" >Đặt làm mặc định</button>
                    <button onclick= "deleteAddress('<%= address.getId() %>')" class="delete_btn" >Xóa</button>

                    <% } else { %>
                        <div class="default">Mặc định</div>
<%--                    <button class="set_default_btn disabled" disabled>Đặt làm mặc định</button>--%>
<%--                    <button onclick= "deleteAddress('<%= address.getId() %>')" class="delete_btn disabled" >Xóa</button>--%>

                    <% } %>
<%--                    <button onclick= "deleteAddress('<%= address.getId() %>')" class="delete_btn" >Xóa</button>--%>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>


</div>



</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/style-component/style-user_profile/Address.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>

</html>