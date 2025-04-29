
//Chuyen trang
document.addEventListener("DOMContentLoaded", function() {
    const rows = document.querySelectorAll("tbody tr");
    const rowsPerPage = 8;
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    const tableContainer = document.querySelector(".table-container");
    const paginationLinks = document.querySelectorAll(".pagination .page-link");
    let currentPage = 1;

    function showPage(page) {
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        let visibleRowCount = 0;

        rows.forEach((row, index) => {
            if (index >= start && index < end) {
                row.style.display = "";
                visibleRowCount++;
            } else {
                row.style.display = "none";
            }
        });

        document.querySelector('.pagination .page-link[data-action="prev"]').parentElement.classList.toggle("disabled", page === 1);
        document.querySelector('.pagination .page-link[data-action="next"]').parentElement.classList.toggle("disabled", page === totalPages);

        paginationLinks.forEach(link => link.classList.remove("active"));
        paginationLinks.forEach(link => {
            if (!link.getAttribute('data-action') && parseInt(link.textContent) === page) {
                link.classList.add("active");
            }
        });
    }

    paginationLinks.forEach((link) => {
        link.addEventListener("click", function(event) {
            event.preventDefault();
            const action = link.getAttribute('data-action');

            if (action === "prev" && currentPage > 1) {
                currentPage--;
            } else if (action === "next" && currentPage < totalPages) {
                currentPage++;
            } else if (!action) {
                currentPage = parseInt(link.textContent);
            }

            showPage(currentPage);
        });
    });
    showPage(1);
});


document.querySelectorAll('.actions-btn').forEach(button => {
    button.addEventListener('click', function (event) {
        event.stopPropagation();
        const menu = this.nextElementSibling;
        menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
    });
});


document.addEventListener('click', function () {
    document.querySelectorAll('.actions-menu').forEach(menu => {
        menu.style.display = 'none';
    });
});

// filter
document.querySelector('.status-select').addEventListener('change', function () {
    const selectedStatus = this.value;
    const rows = document.querySelectorAll('tbody tr');

    rows.forEach(row => {
        const statusElement = row.querySelector('.order-status-shipped') || row.querySelector('.order-status-in-progress') || row.querySelector('.status-paid');
        const statusText = statusElement ? statusElement.textContent.trim() : '';

        if (selectedStatus === "Mặc Định" || statusText === selectedStatus) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});

// Lấy tất cả các hàng
document.querySelectorAll('.order-row').forEach(row => {
    row.addEventListener('click', function (e) {
        const url = this.getAttribute('data-url');
        if (url) {
            window.location.href = url;
        }
    });
});

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
