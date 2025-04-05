<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 3/29/2025
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tag Management</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style-admin/tags/tags.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/tags/tags.js"
            defer></script>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
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
            <div class="toolbar">
                <div id="add-category-box" class="hidden">
                    <h3>Thêm Tag</h3>
                    <input type="text" id="tag-name" name="tagName" placeholder="Nhập tên tag"
                           class="input-field" required/>
                    <div id="tag-error-message" class="error hidden">Tên tag không được để trống</div>
                    <div class="action-buttons">
                        <button class="add-btn" id="add-tag-btn">Thêm</button>
                        <button class="discard-btn" id="discard-tag-btn">Hủy</button>
                    </div>
                </div>

                <button class="add-product-btn" id="show-add-tag-box">+ Thêm</button>
            </div>

            <table class="product-table">
                <thead>
                <tr>
                    <th onclick="sortTable(0)">
                        <div class="header-content">
                            <span class="header-text">ID</span>
                            <span class="sort-arrows">
                                <span class="sort-arrow asc">▲</span>
                                <span class="sort-arrow desc">▼</span>
                            </span>
                        </div>
                    </th>
                    <th onclick="sortTable(1)">
                        <div class="header-content">
                            <span class="header-text">Tên Tag</span>
                            <span class="sort-arrows">
                                <span class="sort-arrow asc">▲</span>
                                <span class="sort-arrow desc">▼</span>
                            </span>
                        </div>
                    </th>
                    <th onclick="sortTable(2)">
                        <div class="header-content">
                            <span class="header-text">Tổng Sản Phẩm</span>
                            <span class="sort-arrows">
                                <span class="sort-arrow asc">▲</span>
                                <span class="sort-arrow desc">▼</span>
                            </span>
                        </div>
                    </th>
                    <th onclick="sortTable(3)">
                        <div class="header-content">
                            <span class="header-text">Trạng thái</span>
                            <span class="sort-arrows">
                                <span class="sort-arrow asc">▲</span>
                                <span class="sort-arrow desc">▼</span>
                            </span>
                        </div>
                    </th>
<%--                    <th>Tổng Sản Phẩm</th>--%>
                    <th>Thao Tác</th>
                </tr>
                </thead>

                <tbody id="tag-table-body">
                <c:if test="${empty tags}">
                    <tr>
                        <td colspan="3">Không có tag nào được tìm thấy.</td>
                    </tr>
                </c:if>
                <c:forEach items="${tags}" var="tag">
                    <tr>
                        <td>${tag.id}</td>
                        <td>
                            <div class="product">
                                <p>${tag.name}</p>
                            </div>
                        </td>
                        <td>${tag.totalProducts}</td>
                        <td>
                            <div class="status tag-status-toggle ${tag.isActive ? 'active' : 'deactive'}" data-id="${tag.id}">
                                    ${tag.isActive ? 'Hoạt động' : 'Không hoạt động'}
                            </div>
                        </td>
                        <td>
                            <span class="icon toggle-icon" data-id="${tag.id}">
                                <i class="fa-solid ${tag.isActive ? 'fa-trash' : 'fa-eye-slash'}" style="padding: 5px;"></i>
                            </span>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <button class="prev-btn">Trước</button>
                <button class="page-number active">1</button>
                <button class="page-number">2</button>
                <button class="next-btn">Tiếp Theo</button>
            </div>
        </div>
    </div>
</div>

<script>const contextPath = "${pageContext.request.contextPath}";</script>


</body>
</html>
