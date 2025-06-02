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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-admin/members/Members.css">
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
                <h2>Thành viên</h2>
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
                            <option value="home">Trạng thái</option>
                            <option value="">Đang hoạt động</option>
                            <option value="">Chờ xác nhận</option>
                            <option value="">....</option>
                        </select>
                    </div>
                </div>

                <table id="member_table">
                    <thead>
                    <tr>
                        <th>Người dùng</th>
                        <th>Vai trò</th>
                        <th>Trạng thái</th>
                        <th>Ngày tham gia</th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:if test="${empty members}">
                        <tr>
                            <td colspan="5">Không có thành viên nào</td>
                        </tr>
                    </c:if>

                    <c:forEach items="${members}" var="m">
                        <p>${m}</p>
                        <tr>
                            <td class="user">
                                <c:if test="${not empty m.avatarUrl}">
                                    <img class="avatar" src="${m.avatarUrl}" alt="Avatar"/>
                                </c:if>

                                <c:if test="${empty m.avatarUrl}">
                                    <img class="avatar"
                                         src="${pageContext.request.contextPath}/static/image/default-avatar.png"
                                         alt="Avatar"/>
                                </c:if>

                                <div class="infor">
                                    <p class="name">${m.fullName}</p>
                                    <p class="mail">${m.email}</p>
                                </div>
                            </td>

                            <td class="role">
                                <label>
                                    <select class="role_list">


                                        <option value="${m.email}" selected>${m.email} </option>

    <%--                                    <c:if test="${not empty roles}">--%>
    <%--                                        <c:forEach var="r" items="${roles}">--%>
    <%--                                            <c:choose>--%>
    <%--                                                <c:when test="${m.role.roleType == r.roleType} ">--%>
    <%--                                                    <option value="${r.roleType}" selected>${r.name} </option>--%>
    <%--                                                </c:when>--%>

    <%--                                                <c:otherwise>--%>
    <%--                                                    <option value="${r.roleType}">${r.name} </option>--%>
    <%--                                                </c:otherwise>--%>
    <%--                                            </c:choose>--%>
    <%--                                        </c:forEach>--%>
    <%--                                    </c:if>--%>

                                    </select>
                                </label>
                            </td>


                            <td>
                              <span class="status_label ${m.status.toLowerCase()}">
                                <c:choose>
                                    <c:when test="${m.status eq 'ACTIVE'}">Đang hoạt động</c:when>
                                    <c:when test="${m.status eq 'PENDING'}">Chờ xác nhận</c:when>
                                    <c:when test="${m.status eq 'INACTIVE'}">VÔ HIỆU HÓA</c:when>
                                    <c:otherwise>Unknown</c:otherwise>
                                </c:choose>
                              </span>
                            </td>

                            <td>2025/2/1</td>

                            <td class="more_action">
<%--                                <i class="fa-solid fa-gear" onclick="toggleMoreActionMenu(this)"></i>--%>
<%--                                <div class="more_action_menu hidden">--%>
<%--                                    <form action="${pageContext.request.contextPath}/admin/team-member/update-status"--%>
<%--                                          method="post">--%>
<%--                                        <input type="hidden" name="memberId" value="${m.id}">--%>
<%--                                        <input type="hidden" name="status" value="DEACTIVE">--%>
<%--                                        <button type="submit" class="btn_deactivate">Vô hiệu hóa</button>--%>
<%--                                    </form>--%>
<%--                                </div>--%>
                            </td>

                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>


        <!-- Phần Modal (popup) cho Invite -->
        <div id="inviteModal" class="modal hidden">
            <div class="modal_content">
                <span class="close_btn">&times;</span>
                <h2>Mời thành viên mới</h2>
                <form id="inviteForm">
                    <!-- Các trường form -->
                    <div class="form_group">
                        <label for="inviteEmail">Địa chỉ Email</label>
                        <input type="email" id="inviteEmail" name="email" placeholder="email"
                               required>
                    </div>
                    <div class="form_group">
                        <label for="inviteName">Tên</label>
                        <input type="text" id="inviteName" name="name" placeholder="Tên" required>
                    </div>
                    <div class="form_group">
                        <label for="inviteRole">Vai trò</label>
                        <select id="inviteRole" name="role">

                            <c:if test="${not empty (roles)}">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </c:if>

                        </select>
                    </div>
                    <div class="form_action">
                        <button type="button" id="cancelInvite" class="btn_cancel">Hủy</button>
                        <button type="submit" class="btn_submit">Gửi lời mời</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/style-component/style-admin/members/Members.js"></script>
</body>
</html>


