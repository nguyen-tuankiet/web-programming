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
                        statusEl.classList.toggle("active", !isActive);
                        statusEl.classList.toggle("deactive", isActive);
                        statusEl.textContent = !isActive ? "Hoạt động" : "Không hoạt động";

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

    // ==== Hiển thị form thêm tag ====
    document.getElementById("show-add-tag-box").addEventListener("click", () => {
        document.getElementById("add-category-box").classList.remove("hidden");
    });

    // ==== Ẩn form khi bấm nút Hủy ====
    document.getElementById("discard-tag-btn").addEventListener("click", () => {
        document.getElementById("add-category-box").classList.add("hidden");
        document.getElementById("tag-name").value = "";
        document.getElementById("tag-status").value = "true";
        document.getElementById("tag-error-message").classList.add("hidden");
    });

    // ==== Xử lý khi nhấn nút "Thêm" ====
    document.getElementById("add-tag-btn").addEventListener("click", async () => {
        const tagNameInput = document.getElementById("tag-name");
        const statusSelect = document.getElementById("tag-status");
        const tagName = tagNameInput.value.trim();
        const isActive = statusSelect.value === "true";
        const errorMessage = document.getElementById("tag-error-message");

        if (!tagName) {
            errorMessage.classList.remove("hidden");
            return;
        } else {
            errorMessage.classList.add("hidden");
        }

        try {
            const response = await fetch(`${contextPath}/admin/api/tag`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name: tagName, isActive: isActive })
            });

            const result = await response.json();

            if (response.ok) {
                const tag = result.data;
                appendTagToTable(tag);
                tagNameInput.value = "";
                statusSelect.value = "true";
                document.getElementById("add-category-box").classList.add("hidden");
            } else {
                alert(result.message || "Thêm tag thất bại.");
            }
        } catch (err) {
            console.error("Lỗi:", err);
            alert("Không thể kết nối đến máy chủ.");
        }
    });

    // ==== Thêm tag mới vào bảng (append vào cuối bảng) ====
    function appendTagToTable(tag) {
        const tbody = document.getElementById("tag-table-body");
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${tag.id}</td>
            <td><div class="product"><p>${tag.name}</p></div></td>
            <td>0</td>
            <td>
                <div class="status tag-status-toggle ${tag.isActive ? 'active' : 'deactive'}" data-id="${tag.id}">
                    ${tag.isActive ? 'Hoạt động' : 'Không hoạt động'}
                </div>
            </td>
            <td>
                <span class="icon toggle-icon" data-id="${tag.id}">
                    <i class="fa-solid ${tag.isActive ? 'fa-trash' : 'fa-eye-slash'}" style="padding: 5px;"></i>
                </span>
            </td>
        `;

        tbody.appendChild(row);

        row.querySelector(".toggle-icon").addEventListener("click", () => {
            const statusEl = row.querySelector(".tag-status-toggle");
            const isActive = statusEl.classList.contains("active");

            fetch(`${contextPath}/admin/api/tag/${tag.id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ isActive: !isActive })
            })
                .then(res => res.json())
                .then(data => {
                    if (data.status === "success") {
                        statusEl.classList.toggle("active", !isActive);
                        statusEl.classList.toggle("deactive", isActive);
                        statusEl.textContent = !isActive ? "Hoạt động" : "Không hoạt động";

                        const iconEl = row.querySelector(".toggle-icon i");
                        iconEl.className = `fa-solid ${!isActive ? 'fa-trash' : 'fa-eye-slash'}`;
                    } else {
                        alert("Cập nhật trạng thái thất bại!");
                    }
                })
                .catch(err => {
                    console.error("Lỗi khi cập nhật trạng thái icon:", err);
                });
        });
    }
});
