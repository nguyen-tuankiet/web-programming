<%--
  Created by IntelliJ IDEA.
  User: kiet
  Date: 12/26/2024
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/addProduct.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>

<div class="page-header">
    <div class="row align-items-center">
        <div class="col-sm mb-2 mb-sm-0">
            <h1 class="page-header-title">Thêm sản phẩm</h1>
        </div>
    </div>
</div>
<div class="main-container">
    <div class="left-column">
        <div class="product-information">
            <h2>Thông tin sản phẩm</h2>
            <form>
                <div class="form-group">
                    <label for="productName">Tên</label>
                    <input type="text" id="productName" placeholder="Tủ lạnh, máy giặt, ect.">
                </div>

                <div class="form-row">
                    <div class="form-group half-width">
                        <label for="sku">Mã SKU</label>
                        <input type="text" id="sku" placeholder="Ví dụ: 348121032">
                    </div>
                    <div class="form-group half-width">
                        <label for="weight">Danh mục</label>
                        <div class="weight-input">
                            <input type="text" id="weight" placeholder="Tủ lạnh">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description">Mô tả (Tùy chọn)</label>

                    <div class="toolbar">
                        <button type="button" class="toolbar-btn"><i class="fas fa-bold"></i></button> <!-- Bold -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-italic"></i></button> <!-- Italic -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-underline"></i></button> <!-- Underline -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-strikethrough"></i></button> <!-- Strike -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-link"></i></button> <!-- Link -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-image"></i></button> <!-- Image -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-quote-right"></i></button> <!-- Blockquote -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-code"></i></button> <!-- Code -->
                        <button type="button" class="toolbar-btn"><i class="fas fa-list-ul"></i></button>
                    </div>

                    <textarea id="description" placeholder="Nhập mô tả của bạn..."></textarea>
                </div>

            </form>
        </div>

        <div class="media-section">
            <div class="media-header">
                <h2>Hình ảnh</h2>
            </div>

            <div class="media-upload-box">
                <div class="upload-icon" id="uploadIcon">
                    <img id="previewImage" src="${pageContext.request.contextPath}/static/image/screenshot-1730907930298-removebg-preview.png" height="170" width="130" alt="Preview Image" />

                </div>
                <div id="imagePreviewContainer" class="image-preview-container"></div>
                <p id="dragDropText">Kéo và thả tệp của bạn vào đây</p>
                <span>hoặc</span>
                <input type="file" id="fileInput" style="display: none;" multiple />
                <button class="browse-files" onclick="document.getElementById('fileInput').click();">Tải ảnh lên</button>
            </div>
        </div>




        <div id="optionsContainer1" class="options-section">
            <h2>Biến thể</h2>
            <div id="optionsContainer" class="options-container">
                <div class="option-group">
                    <select class="option-select">
                        <option value="size">Kích thước</option>
                        <option value="color">Màu sắc</option>
                        <option value="material">Chất liệu</option>
                        <option value="style">Kiểu dáng</option>
                        <option value="title">Tiêu đề</option>
                    </select>
                    <select class="option-select">
                        <option value="size">Kích thước</option>
                        <option value="color">Màu sắc</option>
                        <option value="material">Chất liệu</option>
                        <option value="style">Kiểu dáng</option>
                        <option value="title">Tiêu đề</option>
                    </select>

                    <button class="remove-option-button" onclick="removeOptionGroup(this)">×</button>
                </div>
            </div>
            <button class="add-option-button" onclick="addOptionGroup('optionsContainer')">+ Thêm biến thể</button>
        </div>


    </div>


    <div class="right-column">
        <div class="section pricing-section">
            <h2>Chiếc khấu</h2>
            <label for="price">Giá</label>
            <input type="text" id="price" placeholder="0.00">

            <label for="price">Số lượng</label>
            <input type="text" id="total" placeholder="0">

        </div>


        <div class="section">
            <h2>Thuộc tính</h2>
            <div id="optionsContainer2" class="options-container">
                <div class="option-group">
                    <select class="option-select">
                        <option value="size">Kích thước</option>
                        <option value="color">Màu sắc</option>
                        <option value="material">Chất liệu</option>
                        <option value="style">Kiểu dáng</option>
                        <option value="title">Tiêu đề</option>
                    </select>
                    <select class="option-select">
                        <option value="size">Kích thước</option>
                        <option value="color">Màu sắc</option>
                        <option value="material">Chất liệu</option>
                        <option value="style">Kiểu dáng</option>
                        <option value="title">Tiêu đề</option>
                    </select>
                    <button class="remove-option-button" onclick="removeOptionGroup(this)">×</button>
                </div>
            </div>
            <button class="add-option-button" onclick="addOptionGroup('optionsContainer2')">+ Thêm thuộc tính</button>
        </div>


        <div class="section organization-section">
            <h2>Tổ chức</h2>
            <label for="vendor">Nhà cung cấp</label>
            <input type="text" id="vendor" placeholder="eg. Nike">


            <label for="collections">Bộ sưu tập</label>
            <select id="collections">
                <option value="winter">Nhà bếp</option>
                <option value="summer">Phòng khách</option>
                <option value="summer">Phòng ngủ</option>
                <option value="summer">Khác</option>
            </select>


            <label for="tags">Thẻ</label>
            <input type="text" id="tags" placeholder="Nhập thẻ tại đây">
        </div>
        <div class = "save">
            <button>Lưu</button>
        </div>
    </div>

</div>
</body>
<script src="${pageContext.request.contextPath}/static/style-component/style-admin/addProduct.js"></script>
</html>