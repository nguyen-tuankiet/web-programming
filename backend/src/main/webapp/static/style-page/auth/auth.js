const registerButton = document.querySelector(".register-button");
const loginButton = document.querySelector(".login-button");
const container = document.querySelector(".container");
const togglePasswords = document.querySelectorAll(".toggle-password");

// Chuyển đổi giao diện giữa đăng ký và đăng nhập
registerButton.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});
loginButton.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

// Hiển thị/Ẩn mật khẩu
togglePasswords.forEach((togglePassword) => {
    togglePassword.addEventListener("click", function () {
        const passwordInput = document.querySelector(
            this.getAttribute("data-toggle")
        );

        const type =
            passwordInput.getAttribute("type") === "password" ? "text" : "password";
        passwordInput.setAttribute("type", type);

        this.classList.toggle("fa-eye-slash");
    });
});

document.querySelector(".sign-up-container form").addEventListener("submit", async (e) => {
    e.preventDefault(); // Ngăn gửi form truyền thống

    const fullName = document.getElementById("fullName").value;
    const displayName = document.getElementById("displayname").value;
    const email = document.getElementById("emails").value;
    const password = document.getElementById("passwordd").value;
    const confirmPassword = document.getElementById("conf").value;

    if (password !== confirmPassword) {
        alert("Mật khẩu và xác nhận mật khẩu không khớp!");
        return;
    }

    try {
        const response = await fetch("register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                fullName,
                displayName,
                email,
                password,
                confirmPassword
            }),
        });

        if (response.ok) {
            const data = await response.json();
            alert("Đăng ký thành công! Vui lòng đăng nhập.");
            document.querySelector(".sign-in-container form").reset(); // Reset form đăng nhập
        } else {
            const errorData = await response.json();
            alert("Lỗi đăng ký: " + errorData.message);
        }
    } catch (error) {
        console.error("Lỗi khi đăng ký:", error);
        alert("Đã xảy ra lỗi! Vui lòng thử lại.");
    }
});

document.querySelector(".sign-in-container form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email,
                password,
            }),
        });

        if (response.ok) {
            const data = await response.json();
            console.log("Dữ liệu trả về từ server:", data);  // Kiểm tra dữ liệu trả về

            // Kiểm tra xem data có chứa "data" và các thuộc tính cần thiết không
            if (data && data.data) {
                console.log("Session ID:", data.data.sessionId);
                console.log("User ID:", data.data.id);
                console.log("Role:", data.data.role);

                // Lưu vào sessionStorage
                sessionStorage.setItem("sessionId", data.data.sessionId);
                sessionStorage.setItem("userId", data.data.id);
                sessionStorage.setItem("role", data.data.role);

                window.location.href = "home";
            } else {
                alert("Dữ liệu không hợp lệ từ server");
            }
        } else {
            const errorData = await response.json();
            alert("Lỗi đăng nhập: " + errorData.message);
        }
    } catch (error) {
        console.error("Lỗi khi đăng nhập:", error);
        alert("Đã xảy ra lỗi! Vui lòng thử lại.");
    }
});


