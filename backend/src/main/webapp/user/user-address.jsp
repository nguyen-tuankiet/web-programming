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
    <script src="${pageContext.request.contextPath}/static/style-component/style-user_profile/Address.js"></script>

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
            <div id="addAddressFormContainer" style="display: none;">
                <h2>Thêm Địa Chỉ</h2>
<%--                <form id="addAddressForm">--%>
<%--                    <label for="province">Tỉnh/Thành phố:</label>--%>
<%--                    <input type="text" id="province" name="province" placeholder="Nhập tỉnh/thành phố"/>--%>

<%--                    <label for="district">Quận/Huyện:</label>--%>
<%--                    <input type="text" id="district" name="district" placeholder="Nhập quận/huyện"/>--%>

<%--                    <label for="commune">Phường/Xã:</label>--%>
<%--                    <input type="text" id="commune" name="commune" placeholder="Nhập phường/xã"/>--%>

<%--                    <label for="name">Tên:</label>--%>
<%--                    <input type="text" id="name" name="name" placeholder="Nhập tên"/>--%>

<%--                    <label for="phone">Số điện thoại:</label>--%>
<%--                    <input type="text" id="phone" name="phone" placeholder="Nhập số điện thoại"/>--%>

<%--                    <div class="HomeorOffice">--%>
<%--                        <label>--%>
<%--                            <input type="radio" name="type" value="house"/> Nhà--%>
<%--                        </label>--%>
<%--                        <label>--%>
<%--                            <input type="radio" name="type" value="building"/> Văn phòng--%>
<%--                        </label>--%>

<%--                    </div>--%>
<%--                    <div class="savecane">--%>
<%--                        <button type="button" id="saveAddress">Lưu</button>--%>
<%--                        <button type="button" id="cancelAddressForm">Hủy</button>--%>
<%--                    </div>--%>
<%--                </form>--%>
                <form method="POST" action="AddAddressController">
                    <label for="province">Tỉnh/Thành phố:</label>
                    <input type="text" id="province" name="province" placeholder="Nhập tỉnh/thành phố" required/>

                    <label for="district">Quận/Huyện:</label>
                    <input type="text" id="district" name="district" placeholder="Nhập quận/huyện" required/>

                    <label for="commune">Phường/Xã:</label>
                    <input type="text" id="commune" name="commune" placeholder="Nhập phường/xã" required/>

                    <label for="detail">Chi tiết:</label>
                    <input type="text" id="detail" name="detail" placeholder="Nhập địa chỉ chi tiết" required/>

                    <label for="phone">Số điện thoại:</label>
                    <input type="text" id="phone" name="phone" placeholder="Nhập số điện thoại" required/>

                    <label for="name">Họ tên:</label>
                    <input type="text" id="name" name="name" placeholder="Nhập họ tên" required/>

                    <label>Loại địa chỉ:</label>
                    <input type="radio" id="home" name="type" value="Home" checked/>
                    <label for="home">Nhà</label>
                    <input type="radio" id="office" name="type" value="Office"/>
                    <label for="office">Văn phòng</label>

                    <!-- Hidden field to pass userId -->
                    <input type="hidden" name="userId" value="${userId}"/>

                    <button type="submit">Lưu</button>
                    <button type="reset">Hủy</button>
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
            <div class="address_item row">
                <div class="icon mid_align">
                    <i class="fa-solid <%= address.getType().equals("house") ? "fa-house" : "fa-building" %>"></i>
                </div>

                <div class="infor">
                    <div class="item_header row mid_align">
                        <span class="name"><%= user.getFullName() %></span>
                        <div class="rec_vertical"></div>
                        <span class="phone"><%= address.getPhone() %></span>
                        <% if (address.getIsDefault()) { %>
                        <div class="default">Mặc định</div>
                        <% } %>
                    </div>

                    <div class="item_body">
                        <div class="address_detail">
                            <span><%= address.getDetail() %></span>
                        </div>
                        <div class="location">
                            <span><%= address.getDistrict() %>, <%= address.getProvince() %></span>
                        </div>
                    </div>
                </div>

                <div class="manage mid_align col">
                    <button class="update_btn">Thay đổi</button>
                    <% if (!address.getIsDefault()) { %>
                    <button class="set_default_btn">Đặt làm mặc định</button>
                    <% } else { %>
                    <button class="set_default_btn disabled" disabled>Đặt làm mặc định</button>
                    <% } %>
                    <button class="delete_btn">Xóa</button>
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
</html>