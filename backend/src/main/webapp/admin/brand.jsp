<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/brand/brands.css">
    <script src="${pageContext.request.contextPath}/static/style-component/style-admin/brand/brands.js" defer></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                    <div class="action-buttons">
                        <button class="add-btn add-brand-btn" id="add-category-btn">Thêm</button>
                        <button class="discard-btn" id="discard-category-btn">Hủy</button>
                    </div>
                </div>

                <button class="add-product-btn">+ Thêm</button>
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
                                <label><input type="checkbox" class="checkbox"></label>
                            </td>
                            <td>
                                <div class="product">
                                    <p>${b.name}</p>
                                </div>
                            </td>
                            <td>
                                <span class="status brand-status-toggle ${b.isActive ? 'active' : 'deactive'}"
                                      data-id="${b.id}">
                                        ${b.isActive ? 'Hoạt động' : 'Không hoạt động'}
                                </span>
                            </td>
                            <td>
                                <div class="action-icons">
                                <span class="icon delete-icon" data-id="${tag.id}">
                                    <i class="fa-solid fa-trash" style="padding: 5px;"></i>
                                </span>
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




<script>

    //     Add brand
    const addBrandBtn = document.querySelector(".add-brand-btn");
    const brand_input_field = document.querySelector(".brand-input-field");

    addBrandBtn.addEventListener("click", async () => {
        const brandName = brand_input_field.value.trim();

        if (!brandName) {
            alert("Vui lòng nhập tên danh mục!");
            return;
        }

        try {
            const response = await fetch('add-brand', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ name: brandName }),
            });

            const result = await response.json();
            if (response.ok) {
                alert("Thêm thành công!");
                inputField.value = ""; // Reset input field
            } else {
                alert(`Lỗi: ${result.message}`);
            }
        } catch (error) {
            alert("Có lỗi xảy ra khi thêm  !");
            console.error(error);
        }
    });



</script>
</div>
</body>
</html>
