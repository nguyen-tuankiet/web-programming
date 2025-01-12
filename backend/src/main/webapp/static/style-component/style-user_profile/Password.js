
document.querySelectorAll('.toggle_pass')
    .forEach( function(eyeIcon) {
        eyeIcon.addEventListener('click', function (e) {
            e.preventDefault();

            const pass = eyeIcon.parentElement.getElementsByTagName('input')[0];
            if (pass && pass.type === 'password') {
                pass.type = 'text';
                eyeIcon.classList.remove('fa-eye');
                eyeIcon.classList.add('fa-eye-slash');
            } else if (pass) {
                pass.type = 'password';
                eyeIcon.classList.remove('fa-eye-slash');
                eyeIcon.classList.add('fa-eye');
            }
        })
});

document.querySelector(".form").addEventListener("submit", async function (event) {
    event.preventDefault(); // Ngăn hành vi submit mặc định

    // Lấy giá trị từ các ô input
    const currentPassword = document.getElementById("current_pass").value;
    const newPassword = document.getElementById("new_pass").value;
    const confirmPassword = document.getElementById("confirm_pass").value;

    // Kiểm tra mật khẩu mới và mật khẩu xác nhận có khớp không
    if (newPassword !== confirmPassword) {
        alert("Mật khẩu mới và mật khẩu xác nhận không khớp.");
        return;
    }

    try {
        // Gọi API đổi mật khẩu
        const response = await fetch("change-password", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                currentPassword: currentPassword,
                newPassword: newPassword,
                confirmPassword: confirmPassword,
            }),
        });

        // Xử lý kết quả trả về từ API
        const result = await response.json();

        if (response.ok) {
            alert(result.message || "Đổi mật khẩu thành công!");
            // Reset form sau khi đổi mật khẩu thành công
            document.querySelector(".form").reset();
        } else {
            alert(result.message || "Đổi mật khẩu thất bại.");
        }
    } catch (error) {
        console.error("Lỗi:", error);
        alert("Đã xảy ra lỗi. Vui lòng thử lại sau.");
    }
});


