document.addEventListener("DOMContentLoaded", () => {
    // ==== Sắp xếp bảng ====
    let currentSortColumn = null;
    let currentSortOrder = 'asc';

    function sortTable(columnIndex) {
        const tableBody = document.getElementById("product-table-body");
        const rows = Array.from(tableBody.rows);
        const header = document.querySelectorAll("th")[columnIndex];
        const columnType = header.getAttribute("data-sort");

        if (currentSortColumn === columnIndex) {
            currentSortOrder = currentSortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            currentSortOrder = 'asc';
        }
        currentSortColumn = columnIndex;

        const sortedRows = rows.sort((a, b) => {
            const valA = a.cells[columnIndex].innerText.trim();
            const valB = b.cells[columnIndex].innerText.trim();

            return columnType === "number"
                ? (currentSortOrder === 'asc' ? valA - valB : valB - valA)
                : (currentSortOrder === 'asc' ? valA.localeCompare(valB) : valB.localeCompare(valA));
        });

        tableBody.innerHTML = "";
        sortedRows.forEach(row => tableBody.appendChild(row));

        document.querySelectorAll(".sort-arrow").forEach(arrow => arrow.classList.remove("active"));
        header.querySelector(`.${currentSortOrder}`).classList.add("active");
    }

    document.querySelectorAll("th[data-sort]").forEach((header, index) => {
        header.addEventListener("click", () => sortTable(index));
    });

    // ==== Phân trang ====
    const pageButtons = document.querySelectorAll(".page-number");
    const prevButton = document.querySelector(".prev-btn");
    const nextButton = document.querySelector(".next-btn");
    const tableBody = document.getElementById("product-table-body");
    const rowsPerPage = 10;
    const rows = Array.from(tableBody.rows);
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    let currentPage = 1;

    function renderTable(page) {
        const start = (page - 1) * rowsPerPage;
        const end = page * rowsPerPage;

        rows.forEach((row, index) => {
            row.style.display = index >= start && index < end ? "table-row" : "none";
        });

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

    // ==== Hiển thị/Ẩn form thêm ====
    const addProductBtn = document.querySelector(".add-product-btn");
    const addCategoryBox = document.getElementById("add-category-box");
    const discardBtn = document.querySelector(".discard-btn");

    addProductBtn.addEventListener("click", () => {
        addCategoryBox.classList.toggle("hidden");
    });

    discardBtn.addEventListener("click", () => {
        addCategoryBox.classList.add("hidden");
    });

    // ==== Thêm danh mục ====
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
                inputField.value = "";
                location.reload();
            } else {
                alert(`Lỗi: ${result.message}`);
            }
        } catch (error) {
            alert("Có lỗi xảy ra khi thêm danh mục!");
            console.error(error);
        }
    });

    // ==== Cập nhật trạng thái danh mục (toggle) ====
    document.querySelectorAll(".status-toggle").forEach(statusEl => {
        statusEl.addEventListener("click", () => {
            // const row = statusEl.closest("tr");
            // const iconEl = row.querySelector(".delete-icon i");

            const isActive = statusEl.classList.contains("active");


            // Cập nhật UI
            if (isActive) {
                statusEl.classList.remove("active");
                statusEl.classList.add("deactive");
                statusEl.textContent = "Không hoạt động";
                statusEl.style.color = "#e74c3c";
                statusEl.style.backgroundColor = "#fde2e2";
                // if (iconEl) iconEl.className = "fa-solid fa-eye-slash";
            } else {
                statusEl.classList.remove("deactive");
                statusEl.classList.add("active");
                statusEl.textContent = "Hoạt động";
                statusEl.style.color = "#2ecc71";
                statusEl.style.backgroundColor = "#e9f7ef";
                // if (iconEl) iconEl.className = "fa-solid fa-trash";
            }

            // const categoryId = statusEl.getAttribute("data-id");

            // Gửi PUT request
            fetch(`${contextPath}/admin/api/categories/${categoryId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ isActive: !isActive })
            })
                .then(res => res.json())
                .then(data => {
                    if (!data || data.status !== "success") {
                        alert("Cập nhật trạng thái thất bại!");
                    } else {
                        statusEl.setAttribute("data-is-active", (!isActive).toString());
                    }
                })
                .catch(err => {
                    console.error("Lỗi khi cập nhật trạng thái:", err);
                });
        });
    });

    // ==== Nhấn icon để đổi trạng thái ====
    document.querySelectorAll(".delete-icon").forEach(icon => {
        icon.addEventListener("click", () => {
            const row = icon.closest("tr");
            const statusEl = row.querySelector(".status-toggle");
            if (statusEl) statusEl.click();
        });
    });
});
