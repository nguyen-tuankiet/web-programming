

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
let timeout;  // Biến để lưu trữ timeout cũ

// Hàm gọi API tìm kiếm sản phẩm
function searchProducts() {
    const searchInput = document.getElementById("search-input").value;

    // Nếu độ dài từ khóa nhỏ hơn 3, không gọi API
    if (searchInput.length < 3) {
        document.getElementById("popular-keywords").innerHTML = '<p>Từ khóa tìm kiếm ít nhất 3 ký tự</p>';
        return;
    }

    // Gọi API sau khi debounce
    clearTimeout(timeout);  // Hủy bỏ lần gọi API cũ
    timeout = setTimeout(function() {
        // Gọi API tìm kiếm sản phẩm (ở đây dùng fetch hoặc XMLHttpRequest)
        const encodedQuery = encodeURIComponent(searchInput);
        fetch(`products/search?name=${encodedQuery}`)
            .then(response => {
                console.log('Response Status:', response.status);
                // Kiểm tra mã trạng thái HTTP và chỉ parse JSON nếu trạng thái là OK
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`HTTP error! Status: ${response.status}, Message: ${text}`);
                    });
                }
                return response.text();  // Đọc phản hồi dưới dạng text
            })
            .then(text => {
                console.log('Response Body:', text);
                try {
                    const data = JSON.parse(text);  // Cố gắng parse thành JSON
                    console.log('Data received:', data);
                    updatePopularKeywords(data);
                } catch (e) {
                    console.error('Error parsing JSON:', e);
                }
            })
            .catch(error => {
                console.error('Có lỗi khi gọi API:', error);
            });

    }, 1000);  // Delay 1 giây giữa các lần gọi API
}

// Cập nhật kết quả tìm kiếm vào `popular-keywords`
function updatePopularKeywords(data) {
    const popularKeywordsDiv = document.getElementById("popular-keywords");
    popularKeywordsDiv.innerHTML = "<p>Từ khóa phổ biến</p>";

    if (data && data.length > 0) {
        const ul = document.createElement("ul");
        data.forEach(product => {
            const li = document.createElement("li");
            li.textContent = product.name;
            ul.appendChild(li);
        });
        popularKeywordsDiv.appendChild(ul);
    } else {
        popularKeywordsDiv.innerHTML += "<p>Không tìm thấy kết quả nào</p>";
    }
}

// Lắng nghe sự kiện keyup trên input
document.getElementById("search-input").addEventListener("keyup", searchProducts);
