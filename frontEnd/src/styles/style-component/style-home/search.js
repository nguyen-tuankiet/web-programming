

function toggleDropdown() {
    const dropdown = document.getElementById("popular-keywords");
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
}

document.addEventListener("click", function(event) {
    const searchContainer = document.querySelector(".search-container");
    const dropdown = document.getElementById("popular-keywords");
    if (!searchContainer.contains(event.target)) {
        dropdown.style.display = "none";
    }
});
