<%--
  Created by IntelliJ IDEA.
  User: win10pro
  Date: 12/27/2024
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Đơn Hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/orderDetail.css">
</head>
<body>

<div class="row">
    <h2 class="header-title">Chi Tiết Đơn Hàng</h2>
</div>

<div class="order-details">
    <div class="order-header">
        <div class="order-progress">
            <div class="step completed">
                <div class="circle active"></div>
                <span class="label">Đơn Hàng Đã Đặt</span>
            </div>
            <div class="line active"></div>
            <div class="step">
                <div class="circle"></div>
                <span class="label">Đã Đóng Gói</span>
            </div>
            <div class="line"></div>
            <div class="step">
                <div class="circle"></div>
                <span class="label">Đã Giao Hàng</span>
            </div>
            <div class="line"></div>
            <div class="step">
                <div class="circle"></div>
                <span class="label">Đã Giao Thành Công</span>
            </div>
        </div>
    </div>

    <div class="order-summary-container">

        <div class="order-info">
            <h3>Mã Đơn Hàng: DU00017</h3>
            <p>Ngày Đặt Hàng: 13/6/2024 - 14:50 <span class="status-paid">Đã Thanh Toán</span></p>
            <table class="products-table">
                <thead>
                <tr>
                    <th>Sản Phẩm</th>
                    <th>Số Lượng</th>
                    <th>Số Tiền</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="product-info">
                            <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rf48a4000b4-sv/gallery/vn-twin-cooling-plus-rf48a4000b4-sv-thumb-514890317?$172_172_PNG$" alt="Giày Nữ" class="product-image">
                            <span>Tủ Lạnh 4 Cửa RF48A4000B4 </span><br>
                            <small>Mã SP: 1</small>
                        </div>
                    </td>
                    <td>1</td>
                    <td>1,533,315 VND</td>
                </tr>
                <tr>
                    <td>
                        <div class="product-info">
                            <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/ww10tp44dsb-sv/gallery/vn-front-loading-washer-ww10tp44dsxfq-ww10tp44dsb-sv-thumb-536820962?$172_172_PNG$" alt="Kính Mát Tròn" class="product-image">
                            <span>Máy giặt thông minh AI Ecobubble™</span><br>
                            <small>Mã SP: 1</small>
                        </div>
                    </td>
                    <td>1</td>
                    <td>375,965 VND</td>
                </tr>
                <tr>
                    <td>
                        <div class="product-info">
                            <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/f-sv04dw50nvas/gallery/vn-4-electric-oven-with-bespoke-design-and-dishwasher-with-soft-railing-auto-open-f-sv04dw50nvas-thumb-544328250?$252_252_PNG$" alt="Túi Đeo Vai" class="product-image">
                            <span>Lò Nướng Series 4 Nướng Đối Lưu</span><br>
                            <small>Mã SP: 1</small>
                        </div>
                    </td>
                    <td>1</td>
                    <td>1,533,315 VND</td>
                </tr>
                <tr>
                    <td>
                        <div class="product-info">
                            <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/dw60cg550fsgsv/gallery/vn-dw6500amdw60cg550fsgsv-dw60cg550fsgsv-539468517?$592_472_PNG$" alt="Nước Hoa Cổ Điển" class="product-image">
                            <span>Máy rửa bát Hygiene Care </span><br>
                            <small>Mã SP: 1</small>
                        </div>
                    </td>
                    <td>1</td>
                    <td>1,063,315 VND</td>
                </tr>
                </tbody>
            </table>
            <button class="invoice-btn">Hóa Đơn</button>
        </div>

        <div class="summary-details">

            <div class="order">
                <h3>Tóm Tắt Đơn Hàng</h3>
                <p>Tổng Phụ: <span>7,990,000 VND</span></p>
                <p>Giảm Giá (DIS15%): <span>-1,198,500 VND</span></p>
                <p>Phí Vận Chuyển: <span>352,500 VND</span></p>
                <p>Thuế VAT 19% (đã bao gồm): <span>1,504,000 VND</span></p>
                <p class="total-amount">Tổng Số Tiền: <span>8,644,000 VND</span></p>
            </div>

            <div class="payment">
                <h3>Chi Tiết Thanh Toán</h3>
                <p>Giao Dịch: <span> #DU4444TO10000</span> </p>
                <p>Phương Thức Thanh Toán: <span> Thẻ Tín Dụng</span></p>
                <p>Chủ Thẻ <span>Tên: Harold Gonzalez</span></p>
                <p>Số Thẻ: <span>xxxx xxxx xxxx 6779</span></p>
                <p class="total-amount">Tổng Số Tiền: <span>8,644,000 VND</span></p>
            </div>

        </div>

    </div>
</div>
</body>
</html>
