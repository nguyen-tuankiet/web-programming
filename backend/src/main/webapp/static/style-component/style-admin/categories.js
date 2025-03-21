document.addEventListener("DOMContentLoaded", () => {
    // **Sắp xếp bảng**
    let currentSortColumn = null;
    let currentSortOrder = 'asc';

    function sortTable(columnIndex) {
        const tableBody = document.getElementById("product-table-body");
        const rows = Array.from(tableBody.rows);
        const header = document.querySelectorAll("th")[columnIndex];
        const columnType = header.getAttribute("data-sort");

        // Xác định hướng sắp xếp
        if (currentSortColumn === columnIndex) {
            currentSortOrder = currentSortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            currentSortOrder = 'asc';
        }
        currentSortColumn = columnIndex;

        // Sắp xếp các hàng
        const sortedRows = rows.sort((a, b) => {
            const valA = a.cells[columnIndex].innerText.trim();
            const valB = b.cells[columnIndex].innerText.trim();

            if (columnType === "number") {
                return currentSortOrder === 'asc'
                    ? parseFloat(valA) - parseFloat(valB)
                    : parseFloat(valB) - parseFloat(valA);
            } else {
                return currentSortOrder === 'asc'
                    ? valA.localeCompare(valB)
                    : valB.localeCompare(valA);
            }
        });

        // Cập nhật bảng
        tableBody.innerHTML = "";
        sortedRows.forEach(row => tableBody.appendChild(row));

        // Cập nhật mũi tên sắp xếp
        document.querySelectorAll(".sort-arrow").forEach(arrow => arrow.classList.remove("active"));
        if (currentSortOrder === 'asc') {
            header.querySelector(".asc").classList.add("active");
        } else {
            header.querySelector(".desc").classList.add("active");
        }
    }

    document.querySelectorAll("th[data-sort]").forEach((header, index) => {
        header.addEventListener("click", () => sortTable(index));
    });

    // **Dropdown menu**
    function toggleDropdown(button) {
        const dropdownContent = button.nextElementSibling;
        const isVisible = dropdownContent.style.display === "block";

        document.querySelectorAll(".dropdown-content").forEach(content => {
            content.style.display = "none";
        });

        dropdownContent.style.display = isVisible ? "none" : "block";
    }

    window.addEventListener("click", (e) => {
        if (!e.target.closest(".dropdown")) {
            document.querySelectorAll(".dropdown-content").forEach(content => {
                content.style.display = "none";
            });
        }
    });

    // Gắn sự kiện cho các nút dropdown
    document.querySelectorAll(".dropdown button").forEach(button => {
        button.addEventListener("click", (e) => {
            e.stopPropagation();
            toggleDropdown(button);
        });
    });

    // **Phân trang**
    const pageButtons = document.querySelectorAll(".page-number");
    const prevButton = document.querySelector(".prev-btn");
    const nextButton = document.querySelector(".next-btn");
    const tableBody = document.getElementById("product-table-body");
    const rowsPerPage = 10; // Có thể thay đổi số lượng hàng mỗi trang
    const rows = Array.from(tableBody.rows);
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    let currentPage = 1;

    function renderTable(page) {
        const start = (page - 1) * rowsPerPage;
        const end = page * rowsPerPage;

        rows.forEach((row, index) => {
            row.style.display = index >= start && index < end ? "table-row" : "none";
        });

        // Cập nhật trạng thái nút
        pageButtons.forEach((btn, index) => {
            btn.classList.toggle("active", index + 1 === page);
        });

        prevButton.disabled = page === 1;
        nextButton.disabled = page === totalPages;
    }

    pageButtons.forEach((button, index) => {
        button.addEventListener("click", () => {
            currentPage = index + 1;
            renderTable(currentPage);
        });
    });

    prevButton.addEventListener("click", () => {
        if (currentPage > 1) {
            currentPage--;
            renderTable(currentPage);
        }
    });

    nextButton.addEventListener("click", () => {
        if (currentPage < totalPages) {
            currentPage++;
            renderTable(currentPage);
        }
    });

    renderTable(1); // Hiển thị trang đầu tiên

    // **Hiển thị/Ẩn thêm sản phẩm**
    const addProductBtn = document.querySelector(".add-product-btn");
    const addCategoryBox = document.getElementById("add-category-box");
    const discardBtn = document.querySelector(".discard-btn");

    addProductBtn.addEventListener("click", () => {
        addCategoryBox.classList.toggle("hidden");
    });

    discardBtn.addEventListener("click", () => {
        addCategoryBox.classList.add("hidden");
    });

    // **Xử lý nút "Thêm"**
    const addCateBtn = document.querySelector(".add-cate-btn");
    const inputField = document.querySelector(".input-field");

    addCateBtn.addEventListener("click", async () => {
        const categoryName = inputField.value.trim();

        if (!categoryName) {
            alert("Vui lòng nhập tên danh mục!");
            return;
        }

        try {
            const response = await fetch('add-category', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ name: categoryName }),
            });

            const result = await response.json();
            if (response.ok) {
                alert("Danh mục được thêm thành công!");
                inputField.value = ""; // Reset input field
            } else {
                alert(`Lỗi: ${result.message}`);
            }
        } catch (error) {
            alert("Có lỗi xảy ra khi thêm danh mục!");
            console.error(error);
        }
    });
});
