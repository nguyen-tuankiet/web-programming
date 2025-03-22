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
document.querySelectorAll('.order-row').forEach(row => {
    row.addEventListener('click', function (e) {
        const url = this.getAttribute('data-url');
        if (url) {
            window.location.href = url;
        }
    });
});
