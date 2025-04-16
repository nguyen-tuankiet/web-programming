<%--
  Created by IntelliJ IDEA.
  User: hung
  Date: 22/3/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Team Members</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/members/Members.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
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
      <div class="content_header">
        <h2>Team Members</h2>
        <button id="invite">
          <i class="fa-solid fa-user-plus"></i>
          Mời
        </button>
      </div>

      <div class="member_list">
        <div class="operation">
          <div class="search">
            <input type="search" id="serch_member" placeholder="Tìm kiếm">
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
          <div class="role_list">
            <select id="menu">
              <option value="home">Tất cả vai trò</option>
              <option value="">Admin</option>
              <option value="">Staff</option>
            </select>
          </div>
        </div>

        <table id="member_table">
          <thead>
          <tr>
            <th>User</th>
            <th>Role</th>
            <th>Status</th>
            <th>Join date</th>
            <th></th>
          </tr>
          </thead>

          <tbody>
          <c:if test="${empty teamMembers}">
            <tr><td colspan="5">Không có thành viên nào</td></tr>
          </c:if>

          <c:forEach items="${teamMembers}" var="m">
            <tr>
              <td class="user">
                <img class="avatar" src="${m.avatarUrl}" alt="avt">
                <div class="infor">
                  <p class="name">${m.fullName}</p>
                  <p class="mail">${m.email}</p>
                </div>
              </td>

              <td class="role">
                <select class="role_list" disabled>
                  <option ${m.role == 'Admin' ? 'selected' : ''}>Admin</option>
                  <option ${m.role == 'Staff' ? 'selected' : ''}>Staff</option>
                </select>
              </td>

              <td>
                  <form action="${pageContext.request.contextPath}/admin/team-member/update-status" method="post">
                  <input type="hidden" name="memberId" value="${m.id}">
                  <select name="status" onchange="this.form.submit()"
                          class="status ${m.status.name().toLowerCase()}"
                  >
                      <option value="PENDING"  ${m.status.name() == 'PENDING' ? 'selected' : ''}>Pending</option>
                      <option value="ACTIVE"   ${m.status.name() == 'ACTIVE' ? 'selected' : ''}>Active</option>
                      <option value="BANNED"   ${m.status.name() == 'BANNED' ? 'selected' : ''}>Banned</option>
                      <option value="DEACTIVE" ${m.status.name() == 'DEACTIVE' ? 'selected' : ''}>Deactive</option>

                  </select>
                </form>
              </td>

              <td>2025/2/1</td>

              <td class="more_action">
                <i class="fa-solid fa-ellipsis-vertical"></i>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>


