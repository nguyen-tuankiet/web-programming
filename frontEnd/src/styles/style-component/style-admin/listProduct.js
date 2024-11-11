let currentSortColumn = null;
let currentSortOrder = 'asc';

function sortTable(columnIndex) {
    const tableBody = document.getElementById("product-table-body");
    const rows = Array.from(tableBody.rows);
    const columnType = document.querySelector(`th:nth-child(${columnIndex + 1})`).getAttribute("data-sort");
    const header = document.querySelectorAll("th")[columnIndex];

    // Remove the active class from all sort arrows
    document.querySelectorAll(".sort-arrow").forEach(arrow => arrow.classList.remove("active"));

    // Toggle the sort order if the current column is selected again
    if (currentSortColumn === columnIndex) {
        currentSortOrder = currentSortOrder === 'asc' ? 'desc' : 'asc';
    } else {
        currentSortOrder = 'asc';
    }
    currentSortColumn = columnIndex;

    // Set the active class for the appropriate arrow
    const sortArrows = header.querySelector(".sort-arrows");
    if (currentSortOrder === 'asc') {
        sortArrows.querySelector(".asc").classList.add("active");
    } else {
        sortArrows.querySelector(".desc").classList.add("active");
    }

    // Sort the rows based on column type
    let sortedRows;
    if (columnType === "number") {
        sortedRows = rows.sort((a, b) => {
            const valA = parseFloat(a.cells[columnIndex].innerText.replace('$', ''));
            const valB = parseFloat(b.cells[columnIndex].innerText.replace('$', ''));
            return currentSortOrder === 'asc' ? valA - valB : valB - valA;
        });
    } else if (columnType === "date") {
        sortedRows = rows.sort((a, b) => {
            const dateA = new Date(a.cells[columnIndex].innerText);
            const dateB = new Date(b.cells[columnIndex].innerText);
            return currentSortOrder === 'asc' ? dateA - dateB : dateB - dateA;
        });
    } else {
        sortedRows = rows.sort((a, b) => {
            const textA = a.cells[columnIndex].innerText;
            const textB = b.cells[columnIndex].innerText;
            return currentSortOrder === 'asc' ? textA.localeCompare(textB) : textB.localeCompare(textA);
        });
    }

    // Update the table with sorted rows
    tableBody.innerHTML = "";
    sortedRows.forEach(row => tableBody.appendChild(row));
}

// function  dropdown

function toggleDropdown(button) {
    const dropdownContent = button.nextElementSibling;
    dropdownContent.style.display = dropdownContent.style.display === "block" ? "none" : "block";
}

// Đóng dropdown khi nhấn ra ngoài
window.onclick = function(event) {
    if (!event.target.matches('.dropdown button') && !event.target.closest('.dropdown')) {
        const dropdowns = document.querySelectorAll(".dropdown-content");
        dropdowns.forEach(dropdown => {
            dropdown.style.display = "none";
        });
    }
}
