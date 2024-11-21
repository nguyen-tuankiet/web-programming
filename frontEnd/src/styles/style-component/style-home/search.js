

function toggleDropdown() {
    const dropdown = document.getElementById("popular-keywords");
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
}

document.addEventListener("click", function(event) {
    const searchContainer = document.querySelector(".search-container");
    const dropdown = document.getElementById("popular-keywords");
    if (!searchContainer.contains(event.target)) {
        dropdown.style.display = "none";
    }
});
// Hàm đọc tham số từ URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

// Lấy loại sản phẩm từ URL
const productType = getQueryParam('product');

// Hiển thị nội dung theo loại sản phẩm
if (productType) {
    const titleElement = document.querySelector('#list_product .product_item > span');
    const productMap = {
        'tu-lanh': 'Tủ Lạnh Bán Chạy',
        'may-giat': 'Máy Giặt Thông Minh',
        'may-lanh': 'Máy Lạnh Hiện Đại',
        'dung-cu-nha-bep': 'Dụng Cụ Nhà Bếp'
    };

    // Thay đổi tiêu đề danh sách sản phẩm
    titleElement.textContent = productMap[productType] || 'Sản Phẩm Nổi Bật';
}
