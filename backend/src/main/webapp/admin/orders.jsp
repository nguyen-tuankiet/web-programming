<%--
  Created by IntelliJ IDEA.
  User: kiet
  Date: 12/27/2024
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đơn Hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/orders.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<div class="row">
    <h1 class="header-title">Đơn Hàng</h1>
</div>

<div class="container">
    <div class="header-container">
        <div class="row">
            <div class="search-bar">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                </svg>
                <input type="text" placeholder="Tìm kiếm">
            </div>


            <!--            <label class="form-label me-2 mb-0">Trạng Thái</label>-->
            <select class="status-select">
                <option>Mặc Định</option>
                <option>Đã Gửi</option>
                <option>Đang Xử Lý</option>
                <option>Đã Giao</option>
            </select>
            <div class="header-actions">

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
        </div>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th><input type="checkbox"></th>
                <th>Mã Đơn Hàng</th>
                <th>Tên Khách Hàng</th>
                <th>Ngày</th>
                <th>Thanh Toán</th>
                <th>Tổng Tiền</th>
                <th>Trạng Thái </th>
                <th>Hoạt Động</th>
            </tr>
            </thead>
            <tbody>
            <tr class="order-row" data-url="${pageContext.request.contextPath}/admin/orderDetail.jsp">
                <td><input type="checkbox"></td>
                <td><a href="${pageContext.request.contextPath}/admin/orderDetail.jsp" class="order-id">#DU00017</a></td>
                <td class="order-name">Nguyễn Tuấn Kiệt</td>
                <td class="order-date">3/10/2024 20:02</td>
                <td><span class="status status-paid">Đã Thanh Toán</span></td>
                <td class="order-total">200.000 VND</td>
                <td><span class="status order-status-shipped">Đã Gửi</span></td>

                <td class="actions">
                    <button class="actions-btn">⋮</button>
                    <div class="actions-menu">
                        <div class="options">
                            <p>OPTIONS</p>
                            <button class="action-option"><i class="fas fa-copy"></i> Copy</button>
                            <button class="action-option"><i class="fas fa-print"></i> Print</button>
                        </div>
                        <div class="divider"></div>
                        <div class="download-options">
                            <p>DOWNLOAD OPTIONS</p>
                            <button class="action-option"><i class="fas fa-file-excel"></i> Excel</button>
                            <button class="action-option"><i class="fas fa-file-csv"></i> .CSV</button>
                            <button class="action-option"><i class="fas fa-file-pdf"></i> PDF</button>
                        </div>
                        <div class="divider"></div>
                        <button class="action-option"><i class="fas fa-trash-alt"></i> Delete</button>
                    </div>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <div class="footer-container">
        <nav class="mt-2 mt-md-0">
            <ul class="pagination mb-0">
                <li class="page-item"><a class="page-link" href="#!" data-action="prev">Quay lại</a></li>
                <li class="page-item"><a class="page-link active" href="#!">1</a></li>
                <li class="page-item"><a class="page-link" href="#!">2</a></li>
                <li class="page-item"><a class="page-link" href="#!">3</a></li>
                <li class="page-item"><a class="page-link" href="#!" data-action="next">Tiếp theo</a></li>
            </ul>
        </nav>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/static/style-component/style-admin/order.js"></script>
</html>
