document.addEventListener("DOMContentLoaded", function () {
    const entriesDropdown = document.getElementById("entries");
    const productTableBody = document.getElementById("product-table-body");
    const paginationContainer = document.querySelector(".pagination");
    const prevButton = document.querySelector(".prev-btn");
    const nextButton = document.querySelector(".next-btn");
    const addProductBtn = document.querySelector('.add-product-btn');

    let allRows = Array.from(productTableBody.rows); // Lưu tất cả các dòng sản phẩm vào mảng
    let currentPage = 1;
    let entriesPerPage = parseInt(entriesDropdown.value, 10);

    // Hàm hiển thị các dòng sản phẩm tương ứng với trang hiện tại
    function showPage(page) {
        // Tính toán chỉ số bắt đầu và kết thúc
        const startIndex = (page - 1) * entriesPerPage;
        const endIndex = Math.min(startIndex + entriesPerPage, allRows.length);

        // Ẩn tất cả các dòng trước khi hiển thị dòng phù hợp
        allRows.forEach(row => (row.style.display = "none"));

        // Hiển thị các dòng thuộc trang hiện tại
        for (let i = startIndex; i < endIndex; i++) {
            allRows[i].style.display = "table-row";
        }

        // Cập nhật nút phân trang
        updatePaginationButtons(page);
        currentPage = page;
    }

    // Hàm cập nhật nút phân trang
    function updatePaginationButtons(page) {
        // Xóa các nút phân trang hiện có
        paginationContainer.querySelectorAll(".page-number").forEach(button => button.remove());

        // Tính toán số trang
        const totalPages = Math.ceil(allRows.length / entriesPerPage);

        // Thêm các nút số trang
        for (let i = 1; i <= totalPages; i++) {
            const pageButton = document.createElement("button");
            pageButton.classList.add("page-number");
            pageButton.textContent = i;

            if (i === page) {
                pageButton.classList.add("active");
            }

            pageButton.addEventListener("click", () => showPage(i));
            paginationContainer.insertBefore(pageButton, nextButton);
        }

        // Cập nhật trạng thái nút Trước và Tiếp
        prevButton.disabled = page === 1;
        nextButton.disabled = page === totalPages;
    }

    // Lắng nghe sự kiện khi người dùng thay đổi giá trị dropdown
    entriesDropdown.addEventListener("change", function () {
        entriesPerPage = parseInt(entriesDropdown.value, 10);
        showPage(1); // Luôn hiển thị trang đầu tiên khi thay đổi số mục hiển thị
    });

    // Xử lý sự kiện khi nhấn nút Trước
    prevButton.addEventListener("click", function () {
        if (currentPage > 1) {
            showPage(currentPage - 1);
        }
    });

    // Xử lý sự kiện khi nhấn nút Tiếp
    nextButton.addEventListener("click", function () {
        const totalPages = Math.ceil(allRows.length / entriesPerPage);
        if (currentPage < totalPages) {
            showPage(currentPage + 1);
        }
    });

    // Hàm sắp xếp bảng
    let currentSortColumn = null;
    let currentSortOrder = 'asc';

    function sortTable(columnIndex) {
        const columnType = document.querySelector(`th:nth-child(${columnIndex + 1})`).getAttribute("data-sort");
        const header = document.querySelectorAll("th")[columnIndex];

        // Remove the active class from all sort arrows
        document.querySelectorAll(".sort-arrow").forEach(arrow => arrow.classList.remove("active"));

        // Toggle the sort order if the current column is selected again
        if (currentSortColumn === columnIndex) {
            currentSortOrder = currentSortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            currentSortOrder = 'asc';
        }
        currentSortColumn = columnIndex;

        // Set the active class for the appropriate arrow
        const sortArrows = header.querySelector(".sort-arrows");
        if (currentSortOrder === 'asc') {
            sortArrows.querySelector(".asc").classList.add("active");
        } else {
            sortArrows.querySelector(".desc").classList.add("active");
        }

        // Sort the rows based on column type
        let sortedRows;
        if (columnType === "number") {
            sortedRows = allRows.sort((a, b) => {
                const valA = parseFloat(a.cells[columnIndex].innerText.replace('$', ''));
                const valB = parseFloat(b.cells[columnIndex].innerText.replace('$', ''));
                return currentSortOrder === 'asc' ? valA - valB : valB - valA;
            });
        } else if (columnType === "date") {
            sortedRows = allRows.sort((a, b) => {
                const dateA = new Date(a.cells[columnIndex].innerText);
                const dateB = new Date(b.cells[columnIndex].innerText);
                return currentSortOrder === 'asc' ? dateA - dateB : dateB - dateA;
            });
        } else {
            sortedRows = allRows.sort((a, b) => {
                const textA = a.cells[columnIndex].innerText;
                const textB = b.cells[columnIndex].innerText;
                return currentSortOrder === 'asc' ? textA.localeCompare(textB) : textB.localeCompare(textA);
            });
        }

        // Update the table with sorted rows
        productTableBody.innerHTML = "";
        sortedRows.forEach(row => productTableBody.appendChild(row));
        allRows = Array.from(productTableBody.rows); // Cập nhật danh sách hàng sau khi sắp xếp
        showPage(1); // Reset lại phân trang
    }

    // Xử lý thêm sản phẩm
    addProductBtn.addEventListener('click', () => {
        // const contextPath = '/backend_war';
        const addProductUrl = `add-product`;
        window.location.href = addProductUrl;
    });

    // Khởi tạo hiển thị mặc định
    showPage(1);
});


document.addEventListener("DOMContentLoaded", function() {
    const deleteIcons = document.querySelectorAll('.delete-icon');

    deleteIcons.forEach(icon => {
        icon.addEventListener('click', function() {
            const productId = this.getAttribute('data-product-id');
            if (confirm('Bạn có chắc chắn muốn xóa sản phẩm này?')) {
                deleteProduct(productId);
            }
        });
    });
});
function deleteProduct(productId) {
    const url = 'delete-product';  // URL của Servlet xử lý yêu cầu

    const requestData = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ productId: productId })
    };

    fetch(url, requestData)
        .then(response => response.json())  // Chuyển phản hồi thành JSON
        .then(data => {
            if (data.status === "success") {
                alert(data.message);  // Hiển thị thông báo thành công
                window.location.reload()
                const row = document.querySelector(`tr[data-product-id="${productId}"]`);

                if (row) {
                    row.remove();
                }
            } else {
                alert(data.message);  // Hiển thị thông báo lỗi
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Không thể kết nối đến máy chủ!');
        });
}

/*

function editProduct(productId) {
    // Gọi API lấy thông tin sản phẩm
    fetch(`editProduct?id=${productId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.statusCode === 200 && data.data) {
                const product = data.data;

                // Điền dữ liệu vào các trường trong form Add Product
                document.getElementById('productName').value = product.name;
                document.getElementById('sku').value = product.sku;
                document.getElementById('categoryDropdown').value = product.categoryId; // Lựa chọn danh mục tương ứng
                document.getElementById('description').value = product.description;

                // Cập nhật hình ảnh (nếu cần)
                document.getElementById('previewImage').src = product.imageUrl;

                // Cập nhật thông tin biến thể, giá và số lượng
                document.getElementById('price').value = product.price;
                document.getElementById('total').value = product.stock;

                // Cập nhật nhà cung cấp và thẻ
                document.getElementById('vendor').value = product.vendor || '';  // Đảm bảo bạn có thông tin nhà cung cấp
                document.getElementById('tags').value = product.tags || ''; // Nếu có thẻ

                // Bạn có thể bổ sung các phần khác nếu có (ví dụ: biến thể, thuộc tính...)
            } else {
                alert('Không tìm thấy thông tin sản phẩm.');
            }
        })
        .catch(error => {
            console.error('Có lỗi xảy ra khi gọi API:', error);
            alert('Có lỗi xảy ra khi lấy thông tin sản phẩm.');
        });
}
*/
document.addEventListener("DOMContentLoaded", function () {
    const exportBtn = document.getElementById("exportBtn");
    console.log("Export button:", exportBtn);

    if (exportBtn) {
        exportBtn.addEventListener("click", function (e) {
            e.preventDefault();
            console.log("Export button clicked");
            console.log("pageContextPath:", pageContextPath);
            
            try {
                if (!pageContextPath) {
                    throw new Error("pageContextPath is not defined");
                }
                
                const exportUrl = `${pageContextPath}/admin/export-products`;
                console.log("Export URL:", exportUrl);
                
                // Thêm loading state
                this.disabled = true;
                const originalText = this.innerHTML;
                this.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang xuất...';
                
                // Tạo một form tạm thời để submit
                const form = document.createElement('form');
                form.method = 'GET';
                form.action = exportUrl;
                document.body.appendChild(form);
                form.submit();
                document.body.removeChild(form);
                
                // Reset button after 2 seconds
                setTimeout(() => {
                    this.disabled = false;
                    this.innerHTML = originalText;
                }, 2000);
                
            } catch (error) {
                console.error("Export error:", error);
                alert("Có lỗi xảy ra khi xuất file Excel: " + error.message);
                
                // Reset button
                this.disabled = false;
                this.innerHTML = '<i class="fas fa-file-excel"></i> Xuất Excel';
            }
        });
    } else {
        console.error("Export button not found");
    }
});
