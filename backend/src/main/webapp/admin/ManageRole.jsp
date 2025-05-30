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
          <th></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty roles}">

          <c:forEach items="${roles}" var="role">
            <tr>
              <td><strong>${role.name}</strong><br><small>${role.description}</small></td>
              <td>0 thành viên</td>
              <td>Không có quyền</td>
              <td>
                <c:choose>
                  <c:when test="${role.roleType != 'CUSTOM'}">
                    <i class="fa-solid fa-pen action_icon disable"></i>
                  </c:when>
                  <c:otherwise>
                    <i class="fa-solid fa-pen action_icon"></i>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </c:if>


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
            <label>Tên</label>
            <input type="text" name="name" placeholder="Ví dụ: Kiểm thử Beta">
          </div>
          <div class="form_group">
            <label>Mô tả</label>
            <textarea name="description" placeholder="Mô tả ngắn gọn về vai trò này..."></textarea>
          </div>
          <div class="form_group">
             <c:if test="${not empty permissions}">
            <div>
              <c:forEach  items="${permissions}" var="per">
                <label><input type="checkbox" class="permission" data-id="${per.id}"> ${per.name} </label><br>
              </c:forEach>
            </div>
            </c:if>
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
