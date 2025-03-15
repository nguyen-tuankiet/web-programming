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

//Basic-information
document.addEventListener('DOMContentLoaded', function() {
    const selectedOption = document.getElementById('selected-option');
    const optionsContainer = document.getElementById('options-container');

    // Hiển thị và ẩn danh sách tùy chọn khi click vào selected-option
    selectedOption.addEventListener('click', () => {
        optionsContainer.style.display = optionsContainer.style.display === 'block' ? 'none' : 'block';
    });

    // Khi chọn một tùy chọn
    document.querySelectorAll('.option').forEach(option => {
        option.addEventListener('click', function() {
            // Cập nhật selected option với cờ và tên quốc gia đã chọn
            const selectedFlag = this.querySelector('.flag-icon').src;
            const selectedText = this.textContent.trim();
            selectedOption.innerHTML = `<img src="${selectedFlag}" alt="Flag Icon" class="flag-icon"> ${selectedText}`;

            // Ẩn danh sách tùy chọn sau khi chọn
            optionsContainer.style.display = 'none';
        });
    });

    // Ẩn danh sách tùy chọn nếu click ra ngoài dropdown
    document.addEventListener('click', function(event) {
        if (!event.target.closest('#custom-select') && !event.target.closest('.location-select-container')) {
            optionsContainer.style.display = 'none';
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    // Get the avatar container
    const avatarContainer = document.querySelector('.avatar');
    const avatarImage = document.querySelector('.avatar-image');
    const fileInput = document.getElementById('file_avatar');

    if (avatarContainer && avatarImage && fileInput) {
        // Create the upload icon element
        const uploadIcon = document.createElement('div');
        uploadIcon.className = 'upload-icon';
        uploadIcon.innerHTML = '<i class="fas fa-camera"></i>';
        uploadIcon.style.display = 'none';

        // Add the upload icon to the avatar container
        avatarContainer.appendChild(uploadIcon);

        // Style the upload icon
        uploadIcon.style.position = 'absolute';
        uploadIcon.style.top = '50%';
        uploadIcon.style.left = '50%';
        uploadIcon.style.transform = 'translate(-50%, -50%)';
        uploadIcon.style.backgroundColor = 'rgba(0, 0, 0, 0.3)'; // Lighter background
        uploadIcon.style.color = 'rgba(255, 255, 255, 0.9)'; // Slightly lighter icon
        uploadIcon.style.borderRadius = '50%';
        uploadIcon.style.width = '40px';
        uploadIcon.style.height = '40px';
        uploadIcon.style.display = 'flex';
        uploadIcon.style.alignItems = 'center';
        uploadIcon.style.justifyContent = 'center';
        uploadIcon.style.cursor = 'pointer';
        uploadIcon.style.fontSize = '18px'; // Adjust icon size
        uploadIcon.style.zIndex = '1'; // Ensure it's above the image

        // Make the avatar container position relative
        avatarContainer.style.position = 'relative';
        avatarContainer.style.display = 'inline-block'; // Ensure proper positioning

        // Add hover event listeners
        avatarContainer.addEventListener('mouseenter', function() {
            uploadIcon.style.display = 'flex';
            avatarImage.style.opacity = '0.7';
            avatarImage.style.transition = 'opacity 0.3s ease';
        });

        avatarContainer.addEventListener('mouseleave', function() {
            uploadIcon.style.display = 'none';
            avatarImage.style.opacity = '1';
        });

        // Add click event to the upload icon
        uploadIcon.addEventListener('click', function() {
            fileInput.click();
        });

        // Add change event to the file input
        fileInput.addEventListener('change', function() {
            if (this.files && this.files[0]) {
                const reader = new FileReader();

                reader.onload = function(e) {
                    avatarImage.src = e.target.result;
                };

                reader.readAsDataURL(this.files[0]);
            }
        });
    }
});