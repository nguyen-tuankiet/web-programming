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

window.onload = () => {
    if (!localStorage.getItem("isLoggedIn")) {
        localStorage.setItem("isLoggedIn", false);
        localStorage.setItem("userEmail", "");
    }
};


// Xử lý logic đăng nhập
const signInForm = document.querySelector(".sign-in-container form");
signInForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const email = document.querySelector("#email").value.trim();
    const password = document.querySelector("#password").value.trim();

    // Giả lập tài khoản
    const accounts = {
        "admin@gmail.com": {
            password: "admin123",
            role: "admin",
            redirect: "../../../frontEnd/src/pages/Admin.html",
        },
        "user@gmail.com": {
            password: "user123",
            role: "user",
            redirect: "../../../frontEnd/src/pages/Home.html",
        },
    };


    if (accounts[email] && accounts[email].password === password) {
        // Lưu trạng thái đăng nhập
        localStorage.setItem("isLoggedIn", "true");
        localStorage.setItem("userEmail", email);
        localStorage.setItem("userRole", accounts[email].role);


        window.location.href = accounts[email].redirect;
    } else {
        alert("Email hoặc mật khẩu không đúng!");
    }
});
