<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 4/16/2025
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quản lý vai trò</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/manage-role/ManageRole.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
      <h2>Quản lý vai trò</h2>
      <p class="subtitle">Tạo và quản lý các vai trò với quyền tuỳ chỉnh</p>

      <div class="content_header">
        <h3>Danh sách vai trò</h3>
        <button class="btn_add_role" id="addRoleBtn">
          <i class="fa-solid fa-plus"></i> Thêm vai trò
        </button>
      </div>

      <table id="role_table">
        <thead>
        <tr>
          <th>Tên vai trò</th>
          <th>Thành viên</th>
          <th>Quyền</th>
          <th>Ngày tạo</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td><strong>Quản Trị Viên</strong><br><small>Toàn quyền hệ thống</small></td>
          <td>0 thành viên</td>
          <td>Không có quyền</td>
          <td>18/03/2025</td>
          <td><i class="fa-solid fa-pen action_icon"></i></td>
        </tr>
        <tr>
          <td><strong>Người Dùng</strong><br><small>Chỉnh sửa nội dung</small></td>
          <td>0 thành viên</td>
          <td>Không có quyền</td>
          <td>18/03/2025</td>
          <td><i class="fa-solid fa-pen action_icon"></i></td>
        </tr>
        <tr>
          <td><strong>Nhân Viên</strong><br><small>Chỉnh sửa nội dung</small></td>
          <td>0 thành viên</td>
          <td>Không có quyền</td>
          <td>18/03/2025</td>
          <td><i class="fa-solid fa-pen action_icon"></i></td>
        </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div class="modal hidden" id="roleModal">
      <div class="modal_content">
        <span class="close_btn" id="closeRoleModal">&times;</span>
        <h3>Thêm vai trò mới</h3>
        <form id="roleForm">
          <div class="form_group">
            <label>Tên vai trò</label>
            <input type="text" placeholder="Ví dụ: Kiểm thử Beta">
          </div>
          <div class="form_group">
            <label>Mô tả</label>
            <textarea placeholder="Mô tả ngắn gọn về vai trò này..."></textarea>
          </div>
          <div class="form_group">
            <label>Phân quyền</label>
            <p>Chọn quyền hạn cho vai trò này:</p>
            <div>
              <label><input type="checkbox"> Đọc <small>Có thể xem nội dung</small></label><br>
              <label><input type="checkbox"> Ghi <small>Có thể tạo và chỉnh sửa nội dung</small></label><br>
              <label><input type="checkbox"> Xoá <small>Có thể xoá nội dung</small></label><br>
              <label><input type="checkbox"> Mời <small>Có thể mời thêm thành viên</small></label><br>
              <label><input type="checkbox"> Quản trị <small>Toàn quyền hệ thống</small></label><br>
              <label><input type="checkbox"> Sản phẩm <small>Truy cập sản phẩm và tính năng</small></label><br>
              <label><input type="checkbox"> Góp ý <small>Gửi phản hồi và đề xuất</small></label>
            </div>
          </div>
          <div class="form_action">
            <button type="button" class="btn_cancel" id="cancelAddRole">Huỷ</button>
            <button type="submit" class="btn_submit">Thêm</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/static/style-component/style-admin/manage-role/ManageRole.js"></script>
</body>
</html>
