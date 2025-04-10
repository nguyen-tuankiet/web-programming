document.addEventListener("DOMContentLoaded", () => {
    const rowsPerPage = 10;
    let currentPage = 1;
    let currentSortColumn = null;
    let currentSortOrder = "asc";

    const formBox = document.getElementById("add-banner-box");
    const addBtn = document.querySelector(".add-banner-btn");
    const discardBtn = document.getElementById("discard-banner-btn");
    const submitBtn = document.getElementById('submit-banner-btn');
    const rows = Array.from(document.querySelectorAll("#banner-table-body tr"));
    const paginationContainer = document.createElement("div");

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

    // ==== Hiển thị và ẩn form ====
    if (addBtn && formBox && discardBtn) {
        addBtn.addEventListener("click", () => formBox.classList.toggle("hidden"));
        discardBtn.addEventListener("click", () => formBox.classList.add("hidden"));
    }

    // ==== Upload ảnh lên server ====
    async function uploadImage(file) {
        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await fetch(`${contextPath}/admin/uploadImage`, {
                method: 'POST',
                body: formData
            });

            const result = await response.json();
            if (response.ok && result.statusCode === 200 && Array.isArray(result.data) && result.data.length > 0) {
                return result.data[0]; // Trả về { id, url }
            } else {
                throw new Error(result.message || 'Upload ảnh thất bại');
            }
        } catch (error) {
            console.error('Lỗi upload ảnh:', error);
            throw error;
        }
    }

    // ==== Gửi form thêm banner ====
    if (submitBtn) {
        submitBtn.addEventListener('click', async function () {
            const status = document.getElementById('banner-status').value;
            const imageFile = document.getElementById('banner-imageFile').files[0];
            const startDate = document.getElementById('banner-startDate').value;
            const endDate = document.getElementById('banner-endDate').value;
            const isActive = document.getElementById('banner-isActive').value === 'true';

            if (!status || !imageFile || !startDate || !endDate) {
                alert('Vui lòng điền đầy đủ thông tin');
                return;
            }

            try {
                this.disabled = true;
                this.textContent = 'Đang tải lên...';

                const uploadedImage = await uploadImage(imageFile);

                const bannerData = {
                    status,
                    imageId: uploadedImage.id,
                    startDate,
                    endDate,
                    isActive
                };

                const response = await fetch(`${contextPath}/admin/api/banner`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(bannerData)
                });

                const result = await response.json();
                if (response.ok && result.status === "success") {
                    alert('Thêm banner thành công!');
                    location.reload();
                } else {
                    throw new Error(result.message || 'Tạo banner thất bại');
                }
            } catch (error) {
                console.error('Lỗi khi thêm banner:', error);
                alert('Có lỗi xảy ra: ' + error.message);
            } finally {
                this.disabled = false;
                this.textContent = 'Thêm';
            }
        });
    }

    // ==== SẮP XẾP bảng ====
    window.sortTable = function (columnIndex) {
        const headers = document.querySelectorAll("th");
        const header = headers[columnIndex];

        currentSortOrder = (currentSortColumn === columnIndex && currentSortOrder === "asc") ? "desc" : "asc";
        currentSortColumn = columnIndex;

        const sortedRows = [...rows].sort((a, b) => {
            const valA = a.cells[columnIndex].innerText.trim();
            const valB = b.cells[columnIndex].innerText.trim();

            return !isNaN(valA) && !isNaN(valB)
                ? (currentSortOrder === "asc" ? valA - valB : valB - valA)
                : (currentSortOrder === "asc" ? valA.localeCompare(valB) : valB.localeCompare(valA));
        });

        renderTable(sortedRows, currentPage);
        document.querySelectorAll(".sort-arrow").forEach(arrow => arrow.classList.remove("active"));
        header.querySelector(`.sort-arrow.${currentSortOrder}`).classList.add("active");
    };

    // ==== PHÂN TRANG ====
    paginationContainer.className = "pagination";
    document.querySelector(".content").appendChild(paginationContainer);

    function renderPagination() {
        paginationContainer.innerHTML = "";

        const prevBtn = createPageButton("Trước", currentPage > 1, () => {
            currentPage--;
            renderTable(rows, currentPage);
        });

        paginationContainer.appendChild(prevBtn);

        for (let i = 1; i <= Math.ceil(rows.length / rowsPerPage); i++) {
            const btn = createPageButton(i, true, () => {
                currentPage = i;
                renderTable(rows, currentPage);
            }, i === currentPage);
            paginationContainer.appendChild(btn);
        }

        const nextBtn = createPageButton("Tiếp", currentPage < Math.ceil(rows.length / rowsPerPage), () => {
            currentPage++;
            renderTable(rows, currentPage);
        });

        paginationContainer.appendChild(nextBtn);
    }

    function createPageButton(text, enabled, onClick, isActive = false) {
        const btn = document.createElement("button");
        btn.textContent = text;
        btn.disabled = !enabled;
        btn.className = isActive ? "page-number active" : "page-number";
        btn.onclick = onClick;
        return btn;
    }

    function renderTable(allRows, page) {
        const start = (page - 1) * rowsPerPage;
        const end = page * rowsPerPage;
        allRows.forEach((row, i) => {
            row.style.display = (i >= start && i < end) ? "table-row" : "none";
        });
        renderPagination();
    }

    renderTable(rows, currentPage);
});
