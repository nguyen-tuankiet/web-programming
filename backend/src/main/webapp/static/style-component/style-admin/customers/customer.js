// document.addEventListener("DOMContentLoaded", function() {
//     const rows = document.querySelectorAll("tbody tr");
//     const rowsPerPage = 8;
//     const totalPages = Math.ceil(rows.length / rowsPerPage);
//     const tableContainer = document.querySelector(".table-container");
//     const paginationLinks = document.querySelectorAll(".pagination .page-link");
//     let currentPage = 1;
//
//     function showPage(page) {
//         const start = (page - 1) * rowsPerPage;
//         const end = start + rowsPerPage;
//         let visibleRowCount = 0;
//
//         rows.forEach((row, index) => {
//             if (index >= start && index < end) {
//                 row.style.display = "";
//                 visibleRowCount++;
//             } else {
//                 row.style.display = "none";
//             }
//         });
//
//         document.querySelector('.pagination .page-link[data-action="prev"]').parentElement.classList.toggle("disabled", page === 1);
//         document.querySelector('.pagination .page-link[data-action="next"]').parentElement.classList.toggle("disabled", page === totalPages);
//
//         paginationLinks.forEach(link => link.classList.remove("active"));
//         paginationLinks.forEach(link => {
//             if (!link.getAttribute('data-action') && parseInt(link.textContent) === page) {
//                 link.classList.add("active");
//             }
//         });
//     }
//
//     paginationLinks.forEach((link) => {
//         link.addEventListener("click", function(event) {
//             event.preventDefault();
//             const action = link.getAttribute('data-action');
//
//             if (action === "prev" && currentPage > 1) {
//                 currentPage--;
//             } else if (action === "next" && currentPage < totalPages) {
//                 currentPage++;
//             } else if (!action) {
//                 currentPage = parseInt(link.textContent);
//             }
//
//             showPage(currentPage);
//         });
//     });
//     showPage(1);
// });
//
//
// document.querySelectorAll('.actions-btn').forEach(button => {
//     button.addEventListener('click', function (event) {
//         event.stopPropagation();
//         const menu = this.nextElementSibling;
//         menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
//     });
// });
//
//
// document.addEventListener('click', function () {
//     document.querySelectorAll('.actions-menu').forEach(menu => {
//         menu.style.display = 'none';
//     });
// });
// document.querySelectorAll('.order-row').forEach(row => {
//     row.addEventListener('click', function (e) {
//         const url = this.getAttribute('data-url');
//         if (url) {
//             window.location.href = url;
//         }
//     });
// });
document.addEventListener("DOMContentLoaded", function () {
    const rows = document.querySelectorAll("tbody tr");
    const rowsPerPage = 8;
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    const paginationContainer = document.getElementById("pagination");
    let currentPage = 1;

    function renderPagination() {
        paginationContainer.innerHTML = "";

        // Prev
        const prev = document.createElement("li");
        prev.className = "page-item";
        prev.innerHTML = `<a class="page-link" href="#" data-action="prev">Quay lại</a>`;
        paginationContainer.appendChild(prev);

        // Page numbers
        for (let i = 1; i <= totalPages; i++) {
            const pageItem = document.createElement("li");
            pageItem.className = `page-item ${i === 1 ? "active" : ""}`;
            pageItem.innerHTML = `<a class="page-link" href="#">${i}</a>`;
            paginationContainer.appendChild(pageItem);
        }

        // Next
        const next = document.createElement("li");
        next.className = "page-item";
        next.innerHTML = `<a class="page-link" href="#" data-action="next">Tiếp theo</a>`;
        paginationContainer.appendChild(next);
    }

    function showPage(page) {
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;

        rows.forEach((row, index) => {
            row.style.display = (index >= start && index < end) ? "" : "none";
        });

        const pageItems = paginationContainer.querySelectorAll("li");
        pageItems.forEach(item => item.classList.remove("disabled", "active"));

        // Disable prev/next if needed
        if (page === 1) pageItems[0].classList.add("disabled");
        if (page === totalPages) pageItems[pageItems.length - 1].classList.add("disabled");

        // Active current
        if (page >= 1 && page <= totalPages) {
            pageItems[page].classList.add("active"); // +1 offset vì prev ở đầu
        }

        currentPage = page;
    }

    paginationContainer.addEventListener("click", function (e) {
        e.preventDefault();
        if (!e.target.classList.contains("page-link")) return;

        const action = e.target.getAttribute("data-action");
        const pageItems = paginationContainer.querySelectorAll("li");

        if (action === "prev" && currentPage > 1) {
            showPage(currentPage - 1);
        } else if (action === "next" && currentPage < totalPages) {
            showPage(currentPage + 1);
        } else if (!action) {
            const page = parseInt(e.target.textContent);
            if (!isNaN(page)) showPage(page);
        }
    });

    renderPagination();
    showPage(1);
});
