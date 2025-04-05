let currentSortColumn = null;
let currentSortOrder = 'asc';

function sortTable(columnIndex) {
    const tableBody = document.getElementById("product-table-body");
    const rows = Array.from(tableBody.rows);
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
        sortedRows = rows.sort((a, b) => {
            const valA = parseFloat(a.cells[columnIndex].innerText.replace('$', ''));
            const valB = parseFloat(b.cells[columnIndex].innerText.replace('$', ''));
            return currentSortOrder === 'asc' ? valA - valB : valB - valA;
        });
    } else if (columnType === "date") {
        sortedRows = rows.sort((a, b) => {
            const dateA = new Date(a.cells[columnIndex].innerText);
            const dateB = new Date(b.cells[columnIndex].innerText);
            return currentSortOrder === 'asc' ? dateA - dateB : dateB - dateA;
        });
    } else {
        sortedRows = rows.sort((a, b) => {
            const textA = a.cells[columnIndex].innerText;
            const textB = b.cells[columnIndex].innerText;
            return currentSortOrder === 'asc' ? textA.localeCompare(textB) : textB.localeCompare(textA);
        });
    }

    // Update the table with sorted rows
    tableBody.innerHTML = "";
    sortedRows.forEach(row => tableBody.appendChild(row));
}

// function  dropdown

function toggleDropdown(button) {
    const dropdownContent = button.nextElementSibling;
    dropdownContent.style.display = dropdownContent.style.display === "block" ? "none" : "block";
}

// Đóng dropdown khi nhấn ra ngoài
window.onclick = function (event) {
    if (!event.target.matches('.dropdown button') && !event.target.closest('.dropdown')) {
        const dropdowns = document.querySelectorAll(".dropdown-content");
        dropdowns.forEach(dropdown => {
            dropdown.style.display = "none";
        });
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const entriesDropdown = document.getElementById("entries");
    const productTableBody = document.getElementById("product-table-body");
    const paginationContainer = document.querySelector(".pagination");
    const prevButton = document.querySelector(".prev-btn");
    const nextButton = document.querySelector(".next-btn");
    const addProductBtn = document.querySelector('.add-product-btn');
    const exportBtn = document.getElementById('exportBtn');

    let allRows = Array.from(productTableBody.rows);
    let currentPage = 1;
    let entriesPerPage = parseInt(entriesDropdown.value, 10);

    // Export to Excel functionality
    exportBtn.addEventListener('click', function() {
        // Create a form to submit the export request
        const form = document.createElement('form');
        form.method = 'GET';
        form.action = '/admin/export-products';
        
        // Append the form to the body and submit it
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    });

    const pageButtons = document.querySelectorAll(".page-number");
    const page1Products = document.getElementById("product-table-body");
    const page2Products = document.getElementById("product-table-body-page2");

    function showPage(page) {
        // Hiển thị đúng các sản phẩm của trang
        if (page === 1) {
            page1Products.style.display = "table-row-group";
            page2Products.style.display = "none";
        } else if (page === 2) {
            page1Products.style.display = "none";
            page2Products.style.display = "table-row-group";
        }

        // Cập nhật nút phân trang
        updatePaginationButtons(page);
        currentPage = page;
    }

    function updatePaginationButtons(page) {
        // Loại bỏ lớp active khỏi tất cả nút
        pageButtons.forEach(btn => btn.classList.remove("active"));

        // Thêm lớp active vào nút của trang hiện tại
        document.querySelector(`.page-number:nth-child(${page + 1})`).classList.add("active");

        // Cập nhật trạng thái vô hiệu hóa của nút Trước và Tiếp Theo
        prevButton.disabled = page === 1;
        nextButton.disabled = page === pageButtons.length;
    }

    // Xử lý sự kiện khi người dùng nhấp vào các nút số trang
    pageButtons.forEach((button, index) => {
        button.addEventListener("click", () => showPage(index + 1));
    });

    // Xử lý sự kiện khi người dùng nhấp vào nút Trước
    prevButton.addEventListener("click", () => {
        if (currentPage > 1) {
            showPage(currentPage - 1);
        }
    });

    // Xử lý sự kiện khi người dùng nhấp vào nút Tiếp Theo
    nextButton.addEventListener("click", () => {
        if (currentPage < pageButtons.length) {
            showPage(currentPage + 1);
        }
    });

    // Khởi động trang đầu tiên
    showPage(1);
});

document.addEventListener('click', function (e) {
    const row = e.target.closest('.product-row');
    if (row) {
        console.log('Row clicked!');
        const url = row.getAttribute('data-url');
        if (url) {
            window.location.href = url;
        }
    }
});
