// Toggle Password Visibility
document.addEventListener('DOMContentLoaded', () => {
    // Toggle password visibility for all password fields
    document.querySelectorAll('.toggle_pass').forEach(icon => {
        icon.addEventListener('click', () => {
            // Get the input element associated with the icon
            const input = icon.previousElementSibling;

            if (input.type === 'password') {
                input.type = 'text'; // Show password
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                input.type = 'password'; // Hide password
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });
    });
});

//Toggle-switch
document.addEventListener("DOMContentLoaded", () => {
    // Lấy tất cả các phần tử switch
    const switchElements = document.querySelectorAll(".switch");

    // Lặp qua từng phần tử và gắn sự kiện click
    switchElements.forEach(switchElement => {
        switchElement.addEventListener("click", () => {
            // Toggle lớp active cho phần tử được nhấn
            switchElement.classList.toggle("active");
        });
    });
});
