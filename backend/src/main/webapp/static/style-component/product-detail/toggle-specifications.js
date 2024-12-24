const mainToggleButton = document.getElementById("toggle-specs-btn");
const bottomToggleButton = document.getElementById("toggle-specs-btn-bottom");
const specsSection = document.getElementById("specification-section");

function toggleSpecifications() {
    if (specsSection.style.display === "none") {
        specsSection.style.display = "block";
        mainToggleButton.style.display = "none"; // Hide the main button
    } else {
        specsSection.style.display = "none";
        mainToggleButton.style.display = "inline-block"; // Show the main button
    }
}

// Attach event listeners to both buttons
mainToggleButton.addEventListener("click", toggleSpecifications);
bottomToggleButton.addEventListener("click", toggleSpecifications);
