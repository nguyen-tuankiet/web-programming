<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/categories.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/categories1.js" defer></script>
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
                        <span class="header-text">Tên Danh Mục</span>
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
            <!-- Hiển thị danh mục -->
            <c:if test="${empty categoriesWithStock}">
                <tr>
                    <td colspan="4">Không có danh mục nào được tìm thấy.</td>
                </tr>
            </c:if>
            <c:if test="${not empty categoriesWithStock}">
                <c:forEach items="${categoriesWithStock}" var="category">
                    <tr>
                        <td>
                            <label><input type="checkbox" class="checkbox"></label>
                        </td>
                        <td>
                            <div class="product">
                                <p>${category.name}</p>
                            </div>
                        </td>
                        <td>${category.totalStock}</td>
                        <td>
                            <div class="action-icons">
                                <div class="dropdown">
                                    <button onclick="toggleDropdown(this)">
                                        <i class="fa-solid fa-pen-to-square icon-xs" style="padding: 5px;"></i>
                                        <i class="fa-solid fa-chevron-down" style="padding: 5px;"></i>
                                    </button>
                                    <div class="dropdown-content">
                                        <span class="icon view-icon">
                                            <i class="fa-regular fa-eye" style="padding: 5px;"></i>
                                            Xem
                                        </span>
                                        <span class="icon edit-icon">
                                            <i class="fa-solid fa-pencil-alt" style="padding: 5px;"></i>
                                            Sửa
                                        </span>
                                        <span class="icon delete-icon">
                                            <i class="fa-solid fa-trash" style="padding: 5px;"></i>
                                            Xóa
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
