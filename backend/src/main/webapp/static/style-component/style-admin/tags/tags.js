document.addEventListener("DOMContentLoaded", () => {
    // ==== Sắp xếp bảng ====
    let currentSortColumn = null;
    let currentSortOrder = 'asc';

    function sortTable(columnIndex) {
        const tableBody = document.getElementById("tag-table-body");
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
    const tableBody = document.getElementById("tag-table-body");
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

    renderTable(1);

    // ==== Toggle trạng thái Tag ====
    document.querySelectorAll(".toggle-icon").forEach(icon => {
        icon.addEventListener("click", () => {
            const tagId = icon.dataset.id;
            const row = icon.closest("tr");
            const statusEl = row.querySelector(".tag-status-toggle");

            const isActive = statusEl.classList.contains("active");

            fetch(`${contextPath}/admin/api/tag/${tagId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ isActive: !isActive })
            })
                .then(res => res.json())
                .then(data => {
                    if (data.status === "success") {
                        // Toggle class
                        statusEl.classList.toggle("active", !isActive);
                        statusEl.classList.toggle("deactive", isActive);
                        statusEl.textContent = !isActive ? "Hoạt động" : "Không hoạt động";

                        // Thay đổi biểu tượng icon
                        const iconEl = icon.querySelector("i");
                        iconEl.className = `fa-solid ${!isActive ? 'fa-trash' : 'fa-eye-slash'}`;
                    } else {
                        alert("Cập nhật trạng thái thất bại!");
                    }
                })
                .catch(err => {
                    console.error("Lỗi khi cập nhật trạng thái icon:", err);
                });
        });
    });


});
