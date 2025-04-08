document.addEventListener("DOMContentLoaded", () => {
    // === Toggle trạng thái banner ===
    document.querySelectorAll(".toggle-icon").forEach(icon => {
        icon.addEventListener("click", async () => {
            const bannerId = icon.dataset.id;
            const currentStatus = icon.dataset.status;

            const newStatus = currentStatus === "active" ? "inactive" : "active";

            try {
                const response = await fetch(`${contextPath}/admin/api/banner/${bannerId}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        status: newStatus,
                        imageId: icon.dataset.imageid, // cần gắn thêm data-imageid nếu muốn giữ ảnh cũ
                        startDate: icon.dataset.startdate, // tương tự
                        endDate: icon.dataset.enddate
                    })
                });

                const result = await response.json();
                if (result.status === "success") {
                    location.reload(); // cập nhật lại giao diện
                } else {
                    alert("Cập nhật trạng thái thất bại!");
                }
            } catch (err) {
                console.error("Lỗi:", err);
                alert("Có lỗi xảy ra khi cập nhật banner.");
            }
        });
    });

    // === Mở form thêm banner mới (nếu có) ===
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
});
