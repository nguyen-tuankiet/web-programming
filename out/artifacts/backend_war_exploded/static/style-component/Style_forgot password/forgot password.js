// // Hàm kiểm tra email và hiển thị hộp nhập OTP
function validateEmail() {
    const emailInput = document.getElementById("emailInput").value.trim();
    const errorMessage = document.getElementById("errorMessage");
    const resetBox = document.getElementById("resetBox");
    const otpBox = document.getElementById("otpBox");

    // Biểu thức chính quy kiểm tra email hợp lệ
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

    // Nếu ô email trống
    if (emailInput === "") {
        errorMessage.textContent = "Vui lòng nhập email của bạn.";
        errorMessage.style.display = "block"; // Hiển thị thông báo lỗi
        return; // Dừng lại ở đây, không tiếp tục kiểm tra email
    }

    // Nếu email không hợp lệ
    if (!emailRegex.test(emailInput)) {
        errorMessage.textContent = "Vui lòng nhập một email hợp lệ.";
        errorMessage.style.display = "block"; // Hiển thị thông báo lỗi
    } else {
        errorMessage.style.display = "none";  // Ẩn thông báo lỗi nếu email hợp lệ
        resetBox.style.display = "none";      // Ẩn hộp nhập email
        otpBox.style.display = "block";       // Hiển thị hộp nhập OTP
        startTimer();                         // Bắt đầu đếm ngược thời gian cho OTP
    }
}
//
// // Chuyển đến ô tiếp theo khi nhập mã OTP
// function moveToNext(current, nextFieldID) {
//     if (current.value.length >= 1) {
//         document.getElementById(nextFieldID).focus();
//     }
// }
//
// // Xác thực OTP và hiển thị hộp nhập mật khẩu mới
// function confirmOTP() {
//     const otpCode = ["otp1", "otp2", "otp3", "otp4", "otp5"].map(id => document.getElementById(id).value).join("");
//     const errorMessage = document.getElementById("errorMessage");
//
//     if (otpCode.length === 5 && otpCode.match(/^\d{5}$/)) { // Kiểm tra mã OTP đủ 5 chữ số
//         errorMessage.style.display = "none";  // Ẩn thông báo lỗi nếu mã OTP hợp lệ
//         document.getElementById("otpBox").style.display = "none";  // Ẩn hộp nhập OTP
//         document.getElementById("passwordBox").style.display = "block"; // Hiển thị hộp nhập mật khẩu
//     } else {
//         errorMessage.textContent = "Vui lòng nhập đủ mã OTP (5 chữ số).";
//         errorMessage.style.display = "block";  // Hiển thị thông báo lỗi nếu mã OTP không đầy đủ
//     }
// }
//
// // Đặt lại mật khẩu
// function submitPassword() {
//     const passwordInput = document.getElementById("passwordInput").value.trim();
//     const confirmPasswordInput = document.getElementById("confirmPasswordInput").value.trim();
//     const errorMessage = document.getElementById("passwordErrorMessage");
//
//     if (passwordInput.length < 8) {
//         errorMessage.textContent = "Mật khẩu phải có ít nhất 8 ký tự.";
//         errorMessage.style.display = "block";
//     } else if (passwordInput !== confirmPasswordInput) {
//         errorMessage.textContent = "Mật khẩu không khớp.";
//         errorMessage.style.display = "block";
//     } else {
//         errorMessage.style.display = "none";
//         // alert("Mật khẩu đã được đặt lại thành công!");
//         // Chuyển hướng đến trang confirm.html
//         window.location.href = '../../../src/component/user_profile_component/confirm_change_pass.html';
//     }
// }

// Điều hướng quay lại
function goBackToReset() {
    document.getElementById("otpBox").style.display = "none";
    document.getElementById("resetBox").style.display = "block";

}

function goBackToOTP() {
    document.getElementById("passwordBox").style.display = "none";
    document.getElementById("otpBox").style.display = "block";
}

function goBackToHome() {
    window.location.href = '../../pages/auth.html';
    document.querySelector('.back-arrow').addEventListener('click', goBackToHome);
}

// Hàm đếm ngược thời gian cho OTP
let timeLeft = 60;

function startTimer() {
    const timerElement = document.getElementById("timer");
    const timerInterval = setInterval(() => {
        if (timeLeft > 0) {
            timeLeft--;
            timerElement.textContent = timeLeft;
        } else {
            clearInterval(timerInterval);
            timerElement.textContent = "0";
        }
    }, 1000);
}
document.addEventListener("DOMContentLoaded", () => {
    const resetBox = document.getElementById("resetBox");
    const otpBox = document.getElementById("otpBox");
    const passwordBox = document.getElementById("passwordBox");
    const errorMessage = document.getElementById("errorMessage");
    const passwordErrorMessage = document.getElementById("passwordErrorMessage");

    function validateEmail() {
        const email = document.getElementById("emailInput").value.trim();
        if (!email) {
            errorMessage.textContent = "Vui lòng nhập email.";
            errorMessage.style.display = "block";
            return;
        }
        fetch("forgot-password", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `email=${encodeURIComponent(email)}`,
        })
            .then((response) => {
                if (response.ok) {
                    resetBox.style.display = "none";
                    otpBox.style.display = "block";
                } else {
                    errorMessage.textContent = "Email không tồn tại trong hệ thống.";
                    errorMessage.style.display = "block";
                }
            })
            .catch(() => {
                errorMessage.textContent = "Đã xảy ra lỗi, vui lòng thử lại.";
                errorMessage.style.display = "block";
            });
    }

    function confirmOTP() {
        const otpInputs = document.querySelectorAll(".otp-inputs input");
        const otp = Array.from(otpInputs).map(input => input.value).join("");
        if (otp.length !== 5) {
            errorMessage.textContent = "Mã OTP không hợp lệ.";
            errorMessage.style.display = "block";
            return;
        }
        fetch("verify-otp", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `otp=${encodeURIComponent(otp)}`,
        })
            .then((response) => {
                if (response.ok) {
                    otpBox.style.display = "none";
                    passwordBox.style.display = "block";
                } else {
                    errorMessage.textContent = "Mã OTP không chính xác.";
                    errorMessage.style.display = "block";
                }
            })
            .catch(() => {
                errorMessage.textContent = "Đã xảy ra lỗi, vui lòng thử lại.";
                errorMessage.style.display = "block";
            });
    }

    function submitPassword() {
        const newPassword = document.getElementById("passwordInput").value;
        const confirmPassword = document.getElementById("confirmPasswordInput").value;
        if (!newPassword || newPassword !== confirmPassword) {
            passwordErrorMessage.textContent = "Mật khẩu không khớp.";
            passwordErrorMessage.style.display = "block";
            return;
        }
        fetch("reset-password", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `newPassword=${encodeURIComponent(newPassword)}&confirmPassword=${encodeURIComponent(confirmPassword)}`,

        })
            .then((response) => {
                if (response.ok) {
                    alert("Mật khẩu của bạn đã được đặt lại thành công!");
                    window.location.href = `/backend_war/login`;
                } else {
                    passwordErrorMessage.textContent = "Không thể đặt lại mật khẩu.";
                    passwordErrorMessage.style.display = "block";
                }
            })
            .catch(() => {
                passwordErrorMessage.textContent = "Đã xảy ra lỗi, vui lòng thử lại.";
                passwordErrorMessage.style.display = "block";
            });
    }

    // Bind events
    window.validateEmail = validateEmail;
    window.confirmOTP = confirmOTP;
    window.submitPassword = submitPassword;
});
