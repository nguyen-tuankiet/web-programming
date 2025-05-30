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
            alert("Đăng ký thành công! Vui lòng vào mail để xác nhận.");
            window.location.reload();
            // document.querySelector(".sign-in-container form").reset(); // Reset form đăng nhập
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
                credentials: "include",
                email,
                password,
                recaptcha: recaptchaResponse
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

                // Lưu vào sessionStorage - Handle role object
                sessionStorage.setItem("sessionId", data.data.sessionId);
                sessionStorage.setItem("userId", data.data.id);

                // Handle role as object - store roleType for comparison
                let roleType = '';
                if (data.data.role && typeof data.data.role === 'object') {
                    roleType = data.data.role.roleType;
                    sessionStorage.setItem("role", JSON.stringify(data.data.role)); // Store full role object
                    sessionStorage.setItem("roleType", roleType); // Store roleType for easy access
                } else if (typeof data.data.role === 'string') {
                    // Fallback for backward compatibility
                    roleType = data.data.role;
                    sessionStorage.setItem("role", data.data.role);
                    sessionStorage.setItem("roleType", roleType);
                }

                console.log("Role Type:", roleType);

                // Route based on role type
                if (roleType !== "USER") {
                    window.location.href = "admin/dashboard";
                }  else {
                    window.location.href = "home";
                }
            } else {
                alert("Email hoặc mật khẩu không chính xác!");
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


// Hàm kiểm tra mật khẩu
function validatePassword(password) {
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(password);
}

// Hàm kiểm tra khi người dùng nhập mật khẩu
document.getElementById('passwordd').addEventListener('input', function () {
    const password = document.getElementById('passwordd').value;
    if (!validatePassword(password)) {
        document.getElementById('password-error').style.display = 'block';
    } else {
        document.getElementById('password-error').style.display = 'none';
    }
});

document.getElementById('conf').addEventListener('input', function () {
    const password = document.getElementById('passwordd').value;
    const confirmPassword = document.getElementById('conf').value;
    if (confirmPassword && confirmPassword !== password) {
        document.getElementById('conf-error').style.display = 'block';
    } else {
        document.getElementById('conf-error').style.display = 'none';
    }
});

// Hàm kiểm tra khi người dùng cố gắng đăng ký
function validateForm() {
    const password = document.getElementById('passwordd').value;
    const confirmPassword = document.getElementById('conf').value;

    // Kiểm tra mật khẩu hợp lệ
    if (!validatePassword(password)) {
        document.getElementById('password-error').style.display = 'block';
        return false;
    }
    // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp không
    if (password !== confirmPassword) {
        document.getElementById('conf-error').style.display = 'block';
        return false;
    }

    return true;
}

// Thêm sự kiện submit để kiểm tra form khi người dùng nhấn nút đăng ký
document.querySelector('form').addEventListener('submit', function(event) {
    if (!validateForm()) {
        event.preventDefault(); // Ngừng gửi form nếu không hợp lệ
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.sign-in-container form');
    const rememberCheckbox = document.getElementById('remember-checkbox');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const signInButton = document.getElementById('signInButton');

    checkSavedCredentials();

    function saveCredentials(email, password) {
        const encodedPassword = btoa(password);
        localStorage.setItem('remembered_email', email);
        localStorage.setItem('remembered_password', encodedPassword);
        localStorage.setItem('remember_me', 'true');
    }

    function clearSavedCredentials() {
        localStorage.removeItem('remembered_email');
        localStorage.removeItem('remembered_password');
        localStorage.removeItem('remember_me');
    }

    function checkSavedCredentials() {
        const rememberedEmail = localStorage.getItem('remembered_email');
        const rememberedPassword = localStorage.getItem('remembered_password');
        const rememberMe = localStorage.getItem('remember_me');

        if (rememberMe === 'true' && rememberedEmail && rememberedPassword) {
            emailInput.value = rememberedEmail;
            // Decode the password
            passwordInput.value = atob(rememberedPassword);
            rememberCheckbox.checked = true;
        }
    }

    const togglePasswordButtons = document.querySelectorAll('.toggle-password');
    togglePasswordButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            const targetId = this.getAttribute('data-toggle');
            const passwordInput = document.querySelector(targetId);

            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                this.classList.remove('fa-eye');
                this.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                this.classList.remove('fa-eye-slash');
                this.classList.add('fa-eye');
            }
        });
    });

    // Helper function to get role type from session storage
    window.getUserRoleType = function() {
        const roleType = sessionStorage.getItem('roleType');
        if (roleType) {
            return roleType;
        }

        // Fallback: try to parse role object
        const roleObj = sessionStorage.getItem('role');
        if (roleObj) {
            try {
                const parsed = JSON.parse(roleObj);
                return parsed.roleType || roleObj; // Return roleType or the string itself
            } catch (e) {
                return roleObj; // Return as string if parsing fails
            }
        }

        return null;
    };

    // Helper function to check if user has specific role
    window.hasRole = function(requiredRole) {
        const userRoleType = getUserRoleType();
        return userRoleType === requiredRole;
    };

    // Helper function to check if user is admin (any admin role)
    window.isAdmin = function() {
        const userRoleType = getUserRoleType();
        const adminRoles = [
            'SUPPER_ADMIN',
            'PRODUCT_MANAGER',
            'ORDER_MANAGER',
            'CUSTOMER_SUPPORT',
            'CONTENT_EDITOR',
            'INVENTORY_MANAGER',
            'MARKET_SPECIALIST'
        ];
        return adminRoles.includes(userRoleType);
    };
});