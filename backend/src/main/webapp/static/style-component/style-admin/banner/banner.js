document.addEventListener("DOMContentLoaded", () => {
    // ==== TOGGLE trạng thái isActive ====
    document.querySelectorAll(".toggle-icon").forEach(icon => {
        icon.addEventListener("click", async () => {
            const bannerId = icon.dataset.id;
            const isActive = icon.dataset.active === "true";

            try {
                const response = await fetch(`${contextPath}/admin/api/banner/${bannerId}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ isActive: !isActive })
                });

                const result = await response.json();
                if (result.status === "success" || result.success === true) {
                    location.reload();
                } else {
                    alert("Cập nhật trạng thái thất bại!");
                }
            } catch (err) {
                console.error("Lỗi:", err);
                alert("Có lỗi xảy ra khi cập nhật banner.");
            }
        });
    });

    // ==== FORM thêm banner mới ====
    const addBtn = document.querySelector(".add-banner-btn");
    const formBox = document.getElementById("add-banner-box");
    const discardBtn = document.getElementById("discard-banner-btn");

    if (addBtn && formBox && discardBtn) {
        addBtn.addEventListener("click", () => {
            formBox.classList.toggle("hidden");
        });

        discardBtn.addEventListener("click", () => {
            formBox.classList.add("hidden");
        });

        document.getElementById("submit-banner-btn").addEventListener("click", async () => {
            const status = document.getElementById("banner-status").value;
            const imageId = document.getElementById("banner-imageId").value;
            const startDate = document.getElementById("banner-startDate").value;
            const endDate = document.getElementById("banner-endDate").value;

            if (!status || !imageId || !startDate || !endDate) {
                alert("Vui lòng điền đầy đủ thông tin.");
                return;
            }

            try {
                const response = await fetch(`${contextPath}/admin/api/banner`, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        status, imageId, startDate, endDate
                    })
                });

                const result = await response.json();
                if (result.status === "success") {
                    alert("Thêm banner thành công!");
                    location.reload();
                } else {
                    alert("Thêm banner thất bại!");
                }
            } catch (err) {
                console.error("Lỗi:", err);
                alert("Có lỗi xảy ra khi thêm banner.");
            }
        });
    }

    // ==== SẮP XẾP bảng ====
    let currentSortColumn = null;
    let currentSortOrder = "asc";

    window.sortTable = function (columnIndex) {
        const rows = Array.from(document.querySelectorAll("#banner-table-body tr"));
        const header = document.querySelectorAll("th")[columnIndex];

        if (currentSortColumn === columnIndex) {
            currentSortOrder = currentSortOrder === "asc" ? "desc" : "asc";
        } else {
            currentSortOrder = "asc";
        }
        currentSortColumn = columnIndex;

        const sortedRows = rows.sort((a, b) => {
            const valA = a.cells[columnIndex].innerText.trim();
            const valB = b.cells[columnIndex].innerText.trim();

            return !isNaN(valA) && !isNaN(valB)
                ? (currentSortOrder === "asc" ? valA - valB : valB - valA)
                : (currentSortOrder === "asc" ? valA.localeCompare(valB) : valB.localeCompare(valA));
        });

        renderTable(sortedRows, currentPage); // giữ trang hiện tại
        document.querySelectorAll(".sort-arrow").forEach(arrow => arrow.classList.remove("active"));
        header.querySelector(`.sort-arrow.${currentSortOrder}`).classList.add("active");
    };

    // ==== PHÂN TRANG ====
    const rows = Array.from(document.querySelectorAll("#banner-table-body tr"));
    const rowsPerPage = 10;
    let currentPage = 1;
    const totalPages = Math.ceil(rows.length / rowsPerPage);

    const paginationContainer = document.createElement("div");
    paginationContainer.className = "pagination";
    document.querySelector(".content").appendChild(paginationContainer);

    function renderPagination() {
        paginationContainer.innerHTML = "";

        const prevBtn = document.createElement("button");
        prevBtn.textContent = "Trước";
        prevBtn.disabled = currentPage === 1;
        prevBtn.onclick = () => {
            if (currentPage > 1) {
                currentPage--;
                renderTable(rows, currentPage);
            }
        };
        paginationContainer.appendChild(prevBtn);

        for (let i = 1; i <= totalPages; i++) {
            const pageBtn = document.createElement("button");
            pageBtn.className = "page-number";
            pageBtn.textContent = i;
            if (i === currentPage) pageBtn.classList.add("active");
            pageBtn.onclick = () => {
                currentPage = i;
                renderTable(rows, currentPage);
            };
            paginationContainer.appendChild(pageBtn);
        }

        const nextBtn = document.createElement("button");
        nextBtn.textContent = "Tiếp";
        nextBtn.disabled = currentPage === totalPages;
        nextBtn.onclick = () => {
            if (currentPage < totalPages) {
                currentPage++;
                renderTable(rows, currentPage);
            }
        };
        paginationContainer.appendChild(nextBtn);
    }

    function renderTable(allRows, page) {
        const start = (page - 1) * rowsPerPage;
        const end = page * rowsPerPage;
        allRows.forEach((row, index) => {
            row.style.display = index >= start && index < end ? "table-row" : "none";
        });
        renderPagination();
    }

    renderTable(rows, currentPage);
});
