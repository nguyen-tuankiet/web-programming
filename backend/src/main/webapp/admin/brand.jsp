<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Brand Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/brand/brands.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/brand/brands.js" defer></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>



<div class="container">
    <div class ="left">
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
                    <h3>Thêm Nhãn Hàng</h3>
                    <input type="text" id="category-name" name="categoryName" placeholder="Nhập tên nhà sản xuất"
                           class="input-field brand-input-field " required/>
                    <div id="error-message" class="error hidden">Tên nhà sản xuất không được để trống</div>
<%--                    <select id="brand-status" class="input-field">--%>
<%--                        <option value="true">Hoạt động</option>--%>
<%--                        <option value="false">Không hoạt động</option>--%>
<%--                    </select>--%>
                    <div class="action-buttons">
                        <button class="add-btn add-brand-btn" id="add-category-btn">Thêm</button>
                        <button class="discard-btn" id="discard-category-btn">Hủy</button>
                    </div>
                </div>

                <button class="add-product-btn">+ Thêm</button>
            </div>
            <div class="table-wrapper">
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
                    <th data-sort="string" onclick="sortTable(1)">
                        <div class="header-content">
                            <span class="header-text">Tên nhà sản xuất</span>
                            <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                        </div>
                    </th>
                    <th data-sort="string" onclick="sortTable(2)">
                        <div class="header-content">
                            <span class="header-text">Trạng thái</span>
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
                <c:if test="${empty brands}">
                    <tr>
                        <td colspan="4">Không có nhà sản xuất nào được tìm thấy.</td>
                    </tr>
                </c:if>
                <c:if test="${not empty brands}">
                    <c:forEach items="${brands}" var="b">
                        <tr>
                            <td>
                                    ${b.id}
                            </td>
                            <td>
                                <div class="product">
                                    <p>${b.name}</p>
                                </div>
                            </td>
                            <td>
    <span class="status brand-status-toggle ${b.isActive ? 'active' : 'deactive'}">
            ${b.isActive ? 'Hoạt động' : 'Không hoạt động'}
    </span>
                            </td>
                            <td>
                                <div class="action-icons">
        <span class="icon toggle-icon" data-id="${b.id}" data-active="${b.isActive}">
            <i class="fa-solid ${b.isActive ? 'fa-trash' : 'fa-eye-slash'}"></i>
        </span>
                                </div>
                            </td>



                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            </div>

            <div class="pagination">
                <button class="prev-btn">Trước</button>
                <span class="page-numbers"></span>
                <button class="next-btn">Tiếp Theo</button>
            </div>
        </div>



    </div>

</div>
</body>
<script>const contextPath = "${pageContext.request.contextPath}";</script>
</html>
