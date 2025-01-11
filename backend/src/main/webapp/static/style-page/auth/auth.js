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
//
// window.onload = () => {
//     if (!localStorage.getItem("isLoggedIn")) {
//         localStorage.removeItem("isLoggedIn");
//         localStorage.removeItem("userEmail");
//     }
// };
//
//
// // Xử lý logic đăng nhập
// const signInForm = document.querySelector(".sign-in-container form");
// signInForm.addEventListener("submit", (e) => {
//     e.preventDefault();
//     const email = document.querySelector("#email").value.trim();
//     const password = document.querySelector("#password").value.trim();
//
//     // Giả lập tài khoản
//     const accounts = {
//         "admin@gmail.com": {
//             password: "admin123",
//             role: "admin",
//             redirect: "../../../frontEnd/src/pages/Admin.html",
//         },
//         "user@gmail.com": {
//             password: "user123",
//             role: "user",
//             redirect: "../../../frontEnd/src/pages/Home.html",
//         },
//     };
//
//
//     if (accounts[email] && accounts[email].password === password) {
//         // Lưu trạng thái đăng nhập
//         localStorage.setItem("isLoggedIn", "true");
//         localStorage.setItem("userEmail", email);
//         localStorage.setItem("userRole", accounts[email].role);
//
//
//         window.location.href = accounts[email].redirect;
//     } else {
//         alert("Email hoặc mật khẩu không đúng!");
//     }
// });
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
    e.preventDefault(); // Ngăn gửi form truyền thống

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
            alert("Đăng nhập thành công!");
            window.location.href = "home";
        } else {
            const errorData = await response.json();
            alert("Lỗi đăng nhập: " + errorData.message);
        }
    } catch (error) {
        console.error("Lỗi khi đăng nhập:", error);
        alert("Đã xảy ra lỗi! Vui lòng thử lại.");
    }
});


