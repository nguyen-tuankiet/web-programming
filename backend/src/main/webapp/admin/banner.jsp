<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quản lý Banner</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/banner/banner.css">
  <script src="${pageContext.request.contextPath}/static/style-component/style-admin/banner/banner.js" defer></script>
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
        <button class="add-banner-btn">+ Thêm Banner</button>
      </div>

      <!-- Form thêm banner -->
      <div id="add-banner-box" class="hidden">
        <h3>Thêm Banner</h3>
        <input type="text" id="banner-status" class="input-field" placeholder="Trạng thái (active/inactive)">
        <input type="text" id="banner-imageId" class="input-field" placeholder="ID hình ảnh">
        <input type="date" id="banner-startDate" class="input-field">
        <input type="date" id="banner-endDate" class="input-field">
        <div class="action-buttons">
          <button id="submit-banner-btn" class="add-btn">Thêm</button>
          <button id="discard-banner-btn" class="discard-btn">Hủy</button>
        </div>
      </div>

      <div class="table-wrapper">
        <table class="product-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>Ảnh</th>
            <th>Trạng thái</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Thao tác</th>
          </tr>
          </thead>
          <tbody id="banner-table-body">
          <c:if test="${empty banners}">
            <tr>
              <td colspan="6">Không có banner nào được tìm thấy.</td>
            </tr>
          </c:if>
          <c:if test="${not empty banners}">
            <c:forEach items="${banners}" var="b">
              <tr>
                <td>${b.id}</td>
                <td>
                  <img src="${imageMap[b.imageId]}" alt="Banner" class="banner-image" style="width: 100px; border-radius: 8px;"/>
                </td>
                <td>
                                    <span class="status ${b.status == 'active' ? 'active' : 'deactive'}">
                                        ${b.status == 'active' ? 'Hoạt động' : 'Không hoạt động'}
                                    </span>
                </td>
                <td>${b.startDate}</td>
                <td>${b.endDate}</td>
                <td>
                  <div class="action-icons">
                                        <span class="icon toggle-icon"
                                              data-id="${b.id}"
                                              data-status="${b.status}"
                                              data-imageid="${b.imageId}"
                                              data-startdate="${b.startDate}"
                                              data-enddate="${b.endDate}">
                                            <i class="fa-solid ${b.status == 'active' ? 'fa-trash' : 'fa-eye-slash'}"></i>
                                        </span>
                  </div>
                </td>
              </tr>
            </c:forEach>
          </c:if>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

</body>
</html>
