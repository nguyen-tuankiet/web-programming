<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Sản Phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/products/listProduct.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/products/listProduct.js"></script>
</head>
<body>






<div class="container">

    <div class ="left">
        <div class="side_bar">
            <jsp:include page="SideBar.jsp"/>
        </div>

    </div>



    <div class="center">
        <div class="header">
            <jsp:include page="Header.jsp"/>
        </div>



    <div class="content">
        <div class="toolbar">
            <button class="add-product-btn" >+ Thêm Sản Phẩm</button>
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
            <div class="export-container">
                <button class="export-btn">Xuất <i class="fas fa-chevron-down"></i></button>
                <div class="export-menu">
                    <div class="options">
                        <p>OPTIONS</p>
                        <button class="export-option"><i class="fas fa-copy"></i> Copy</button>
                        <button class="export-option"><i class="fas fa-print"></i> Print</button>
                    </div>
                    <div class="divider"></div>
                    <div class="download-options">
                        <p>DOWNLOAD OPTIONS</p>
                        <button class="export-option"><i class="fas fa-file-excel"></i> Excel</button>
                        <button class="export-option"><i class="fas fa-file-csv"></i> .CSV</button>
                        <button class="export-option"><i class="fas fa-file-pdf"></i> PDF</button>
                    </div>
                </div>
            </div>
        </div>

        <table class="product-table">
            <thead>
            <tr>
                <th><label>
                    <input type="checkbox">
                </label></th>
                <th data-sort="string" onclick="sortTable(1)">
                    <div class="header-content">
                        <span class="header-text">Sản Phẩm</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th data-sort="string" onclick="sortTable(2)">
                    <div class="header-content">
                        <span class="header-text">Danh Mục</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th data-sort="date" onclick="sortTable(3)">
                    <div class="header-content">
                        <span class="header-text">Ngày Thêm</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th data-sort="number" onclick="sortTable(4)">
                    <div class="header-content">
                        <span class="header-text">Giá</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th data-sort="number" onclick="sortTable(5)">
                    <div class="header-content">
                        <span class="header-text">Số Lượng</span>
                        <span class="sort-arrows">
                        <span class="sort-arrow asc">▲</span>
                        <span class="sort-arrow desc">▼</span>
                    </span>
                    </div>
                </th>
                <th data-sort="string" onclick="sortTable(6)">
                    <div class="header-content">
                        <span class="header-text">Trạng Thái</span>
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
                <p>Không có sản phẩm</p>
            </c:if>
            <c:if test="${not empty products}">
                <c:forEach items="${products}" var="p">
                    <tr>
                        <td><label><input type="checkbox" class="checkbox"></label></td>
                        <td>
                            <div class="product">

                                <img src="${p.imageUrl}" alt="${p.name}" class="product-img">

                                <p>${p.name}</p>
                            </div>
                        </td>

                        <td>${p.categoryName}</td>

                        <td>01/8/2023</td>

                        <td>   <fmt:formatNumber value="${p.price}" pattern="#,###"/> VND </td>

                        <td>${p.stock}</td>

                        <td>
                            <c:choose>
                                <c:when test="${p.active}">
                                    <span class="status active">Hoạt Động</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status deactive">Không Hoạt Động</span>
                                </c:otherwise>
                            </c:choose>
                        </td>



                        <!-- Thao tác -->
                        <td>
                            <div class="action-icons">
                                <div class="dropdown">
                                    <button onclick="toggleDropdown(this)">
                                        <i class="fa-solid fa-pen-to-square icon-xs" style="padding: 5px;"></i>
                                        <i class="fa-solid fa-chevron-down" style="padding: 5px;"></i>
                                    </button>
                                    <div class="dropdown-content">

                                        <span class="icon edit-icon">
    <a href="addProduct.jsp?id=${p.id}">
        <i class="fa-solid fa-pencil-alt" style="padding: 5px;"></i> Chỉnh sửa
    </a>
</span>

                                        <span class="icon delete-icon" data-product-id="${p.id}">
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

</div>


</body>
</html>

