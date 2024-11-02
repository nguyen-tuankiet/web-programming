document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM fully loaded and parsed");

    const colorOptions = document.querySelectorAll('.color-option');
    colorOptions.forEach(option => {
        option.addEventListener('click', () => {
            colorOptions.forEach(o => o.classList.remove('selected'));
            option.classList.add('selected');
        });
    });

    const capacityOptions = document.querySelectorAll('.capacity-option');
    capacityOptions.forEach(option => {
        option.addEventListener('click', () => {
            capacityOptions.forEach(o => o.classList.remove('selected'));
            option.classList.add('selected');
        });
    });
});
