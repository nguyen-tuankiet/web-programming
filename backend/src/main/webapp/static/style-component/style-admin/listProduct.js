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
