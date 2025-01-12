<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 1/11/2025
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/categories.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/categories.js" defer></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>

<div class="header">
    <jsp:include page="Header.jsp"/>
</div>

<div class="container">
    <div class="side_bar">
        <jsp:include page="SideBar.jsp"/>
    </div>


    <div class="content">
        <div class="toolbar">
            <div id="add-category-box" class="hidden">
                <h3>Thêm Sản Phẩm</h3>
                <input type="text" placeholder="Nhập" class="input-field"/>
                <div class="action-buttons">
                    <button class="add-btn">Thêm</button>
                    <button class="discard-btn">Hủy</button>
                </div>
            </div>
            <button class="add-product-btn">+ Thêm</button>
        </div>

        <div class="row">
            <div class="entries-dropdown">
                <label for="entries">Hiển thị</label>
                <select id="entries" name="entries">
                    <option value="10">10</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
                mục
            </div>
            <label>
                <input type="text" class="search-bar" placeholder="Tìm kiếm">
            </label>
        </div>

        <table class="product-table">
            <thead>
            <tr>
                <th><label>
                    <input type="checkbox">
                </label></th>
                <th data-sort="string" onclick="sortTable(1)">
                    <div class="header-content">
                        <span class="header-text">Loại Sản Phẩm</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th data-sort="string" onclick="sortTable(2)">
                    <div class="header-content">
                        <span class="header-text">Tổng Sản Phẩm</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th>Thao Tác</th>
            </tr>
            </thead>

            <tbody id="product-table-body">
            <!-- Sản phẩm 1 -->
            <c:if test="${empty products}">
                <p> Khong co product</p>
            </c:if>
            <c:if test="${not empty products}">
                <c:forEach items="${products}" var="p">
                    <tr>

                        <td><label><input type="checkbox" class="checkbox"></label></td>
                        <td>
                            <div class="product">
                                <p>Máy Giặt</p>
                            </div>
                        </td>
                        <td>42</td>
                        <td>
                            <div class="action-icons">
                                <div class="dropdown">
                                    <button onclick="toggleDropdown(this)"> <!-- Nút chính gọi dropdown -->
                                      <i class="fa-solid fa-pen-to-square icon-xs" style="padding: 5px;" ></i>
                                      <i class="fa-solid fa-chevron-down" style="padding: 5px;" ></i>
                                    </button>
                                    <div class="dropdown-content"> <!-- Nội dung dropdown -->
                                        <span class="icon view-icon">
                    <i class="fa-regular fa-eye" style="padding: 5px;"></i>
                    View
                </span>
                                        <span class="icon edit-icon">
                    <i class="fa-solid fa-pencil-alt" style="padding: 5px;"></i>

                    Edit
                </span>
                                        <span class="icon delete-icon">
                  <i class="fa-solid fa-trash" style="padding: 5px;"></i>

                    Delete
                </span>

                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

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

</body>
</html>
