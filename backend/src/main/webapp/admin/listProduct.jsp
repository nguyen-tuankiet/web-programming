<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Sản Phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/listProduct.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/listProduct.js"></script>
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
            <button class="add-product-btn" >+ Thêm Sản Phẩm</button>
            <div class="toolbar-buttons">
                <button class="export-btn">Xuất</button>
            </div>
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


            <%--        <!-- Sản phẩm 2 -->--%>
            <%--        <tr>--%>
            <%--            <td><label><input type="checkbox" class="checkbox"></label></td>--%>
            <%--            <td>--%>
            <%--                <div class="product">--%>
            <%--                    <img src="../../../resource/image/listProduct/2.jpg" alt="listProduct2" class="product-img">--%>
            <%--                    <p>Máy Giặt</p>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--            <td>Thiết Bị Gia Dụng</td>--%>
            <%--            <td>02/8/2023</td>--%>
            <%--            <td>10,500,000 VNĐ</td>--%>
            <%--            <td>15</td>--%>
            <%--            <td><span class="status deactive">Không Hoạt Động</span></td>--%>
            <%--            <td>--%>
            <%--                <div class="action-icons">--%>
            <%--                    <div class="dropdown">--%>
            <%--                        <button onclick="toggleDropdown(this)"> <!-- Nút chính gọi dropdown -->--%>
            <%--                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"--%>
            <%--                                 fill="none"--%>
            <%--                                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                                 class="feather feather-edit icon-xs">--%>
            <%--                                <path--%>
            <%--                                        d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>--%>
            <%--                                <path--%>
            <%--                                        d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>--%>
            <%--                            </svg>--%>
            <%--                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"--%>
            <%--                                 viewBox="0 0 24 24">--%>
            <%--                                <path d="M6 9l6 6 6-6"></path>--%>
            <%--                            </svg>--%>
            <%--                        </button>--%>
            <%--                        <div class="dropdown-content"> <!-- Nội dung dropdown -->--%>
            <%--                            <span class="icon view-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-eye icon-xs"><path--%>
            <%--                            d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12"--%>
            <%--                                                                                            r="3"></circle>--%>
            <%--                    </svg>--%>
            <%--                    View--%>
            <%--                </span>--%>
            <%--                            <span class="icon edit-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-edit icon-xs"><path--%>
            <%--                            d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path--%>
            <%--                            d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>--%>
            <%--                    </svg>--%>
            <%--                    Edit--%>
            <%--                </span>--%>
            <%--                            <span class="icon delete-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-trash-2 icon-xs"><polyline points="3 6 5 6 21 6"></polyline><path--%>
            <%--                            d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line--%>
            <%--                            x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line>--%>
            <%--                    </svg>--%>
            <%--                    Delete--%>
            <%--                </span>--%>

            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--        </tr>--%>

            <%--        <!-- Sản phẩm 3 -->--%>
            <%--        <tr>--%>
            <%--            <td><label><input type="checkbox" class="checkbox"></label></td>--%>
            <%--            <td>--%>
            <%--                <div class="product">--%>
            <%--                    <img src="../../../resource/image/listProduct/3.jpg" alt="listProduct3" class="product-img">--%>
            <%--                    <p>Tủ lạnh</p>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--            <td>Thiết Bị Nhà Bếp</td>--%>
            <%--            <td>03/8/2023</td>--%>
            <%--            <td>1,500,000 VNĐ</td>--%>
            <%--            <td>45</td>--%>
            <%--            <td><span class="status active">Hoạt Động</span></td>--%>
            <%--            <td>--%>
            <%--                <div class="action-icons">--%>
            <%--                    <div class="dropdown">--%>
            <%--                        <button onclick="toggleDropdown(this)"> <!-- Nút chính gọi dropdown -->--%>
            <%--                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"--%>
            <%--                                 fill="none"--%>
            <%--                                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                                 class="feather feather-edit icon-xs">--%>
            <%--                                <path--%>
            <%--                                        d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>--%>
            <%--                                <path--%>
            <%--                                        d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>--%>
            <%--                            </svg>--%>
            <%--                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"--%>
            <%--                                 viewBox="0 0 24 24">--%>
            <%--                                <path d="M6 9l6 6 6-6"></path>--%>
            <%--                            </svg>--%>
            <%--                        </button>--%>
            <%--                        <div class="dropdown-content"> <!-- Nội dung dropdown -->--%>
            <%--                            <span class="icon view-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-eye icon-xs"><path--%>
            <%--                            d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12"--%>
            <%--                                                                                            r="3"></circle>--%>
            <%--                    </svg>--%>
            <%--                    View--%>
            <%--                </span>--%>
            <%--                            <span class="icon edit-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-edit icon-xs"><path--%>
            <%--                            d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path--%>
            <%--                            d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>--%>
            <%--                    </svg>--%>
            <%--                    Edit--%>
            <%--                </span>--%>
            <%--                            <span class="icon delete-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-trash-2 icon-xs"><polyline points="3 6 5 6 21 6"></polyline><path--%>
            <%--                            d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line--%>
            <%--                            x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line>--%>
            <%--                    </svg>--%>
            <%--                    Delete--%>
            <%--                </span>--%>

            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--        </tr>--%>

            <%--        <tr>--%>
            <%--            <td><label><input type="checkbox" class="checkbox"></label></td>--%>
            <%--            <td>--%>
            <%--                <div class="product">--%>
            <%--                    <img src="../../../resource/image/listProduct/3.jpg" alt="listProduct3" class="product-img">--%>
            <%--                    <p>Tủ lạnh</p>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--            <td>Thiết Bị Nhà Bếp</td>--%>
            <%--            <td>03/8/2023</td>--%>
            <%--            <td>1,500,000 VNĐ</td>--%>
            <%--            <td>45</td>--%>
            <%--            <td><span class="status active">Hoạt Động</span></td>--%>
            <%--            <td>--%>
            <%--                <div class="action-icons">--%>
            <%--                    <div class="dropdown">--%>
            <%--                        <button onclick="toggleDropdown(this)"> <!-- Nút chính gọi dropdown -->--%>
            <%--                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"--%>
            <%--                                 fill="none"--%>
            <%--                                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                                 class="feather feather-edit icon-xs">--%>
            <%--                                <path--%>
            <%--                                        d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>--%>
            <%--                                <path--%>
            <%--                                        d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>--%>
            <%--                            </svg>--%>
            <%--                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"--%>
            <%--                                 viewBox="0 0 24 24">--%>
            <%--                                <path d="M6 9l6 6 6-6"></path>--%>
            <%--                            </svg>--%>
            <%--                        </button>--%>
            <%--                        <div class="dropdown-content"> <!-- Nội dung dropdown -->--%>
            <%--                            <span class="icon view-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-eye icon-xs"><path--%>
            <%--                            d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12"--%>
            <%--                                                                                            r="3"></circle>--%>
            <%--                    </svg>--%>
            <%--                    View--%>
            <%--                </span>--%>
            <%--                            <span class="icon edit-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-edit icon-xs"><path--%>
            <%--                            d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path--%>
            <%--                            d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>--%>
            <%--                    </svg>--%>
            <%--                    Edit--%>
            <%--                </span>--%>
            <%--                            <span class="icon delete-icon">--%>
            <%--                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"--%>
            <%--                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"--%>
            <%--                         class="feather feather-trash-2 icon-xs"><polyline points="3 6 5 6 21 6"></polyline><path--%>
            <%--                            d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line--%>
            <%--                            x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line>--%>
            <%--                    </svg>--%>
            <%--                    Delete--%>
            <%--                </span>--%>

            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </td>--%>
            <%--        </tr>--%>
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

