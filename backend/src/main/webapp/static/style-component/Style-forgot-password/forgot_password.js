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
    window.location.href = 'login';
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
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        
        if (!email) {
            errorMessage.textContent = "Vui lòng nhập email.";
            errorMessage.style.display = "block";
            return;
        }

        if (!emailRegex.test(email)) {
            errorMessage.textContent = "Vui lòng nhập email hợp lệ.";
            errorMessage.style.display = "block";
            return;
        }

        // Show loading message
        errorMessage.textContent = "Đang gửi mã OTP...";
        errorMessage.style.display = "block";
        errorMessage.style.color = "#1565c0";
        errorMessage.style.backgroundColor = "#e3f2fd";
        errorMessage.style.padding = "10px";
        errorMessage.style.borderRadius = "4px";

        fetch("forgot-password", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `email=${encodeURIComponent(email)}`,
        })
            .then((response) => {
                if (response.ok) {
                    errorMessage.style.display = "none";
                    resetBox.style.display = "none";
                    otpBox.style.display = "block";
                    startTimer();
                } else {
                    return response.text().then(text => {
                        errorMessage.textContent = text || "Email không tồn tại trong hệ thống.";
                        errorMessage.style.display = "block";
                        errorMessage.style.color = "#d32f2f";
                        errorMessage.style.backgroundColor = "#ffebee";
                    });
                }
            })
            .catch(() => {
                errorMessage.textContent = "Đã xảy ra lỗi, vui lòng thử lại.";
                errorMessage.style.display = "block";
                errorMessage.style.color = "#d32f2f";
                errorMessage.style.backgroundColor = "#ffebee";
            });
    }

    function confirmOTP() {
        const otpInputs = document.querySelectorAll(".otp-inputs input");
        const otpValues = Array.from(otpInputs).map(input => input.value);
        const otp = otpValues.join(''); // Ghép các số OTP lại thành một chuỗi
        const otpErrorMessage = document.getElementById("otpErrorMessage");
        const email = document.getElementById("emailInput").value.trim();
        
        // Ẩn thông báo lỗi khi bắt đầu xác thực
        otpErrorMessage.style.display = "none";
        
        // Kiểm tra xem tất cả các ô OTP đã được nhập chưa
        if (otpValues.some(value => !value)) {
            showOtpError("Vui lòng nhập đầy đủ mã OTP");
            return;
        }

        if (otp.length !== 5) {
            showOtpError("Mã OTP phải có đủ 5 chữ số");
            return;
        }

        // Show loading state
        showOtpMessage("Đang xác thực OTP...", "info");

        fetch("verify-otp", {
            method: "POST",
            headers: { 
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `otpCode=${encodeURIComponent(otp)}&email=${encodeURIComponent(email)}`,
        })
            .then((response) => {
                if (response.ok) {
                    // Show success message
                    showOtpMessage("Xác thực OTP thành công!", "success");
                    
                    // Hide OTP box and show password box after a short delay
                    setTimeout(() => {
                        otpErrorMessage.style.display = "none";
                        document.getElementById("otpBox").style.display = "none";
                        document.getElementById("passwordBox").style.display = "block";
                    }, 1500);
                } else {
                    return response.text().then(text => {
                        showOtpError(text || "Mã OTP không chính xác");
                        // Clear OTP inputs
                        otpInputs.forEach(input => input.value = '');
                        // Focus on first OTP input
                        otpInputs[0].focus();
                    });
                }
            })
            .catch(() => {
                showOtpError("Đã xảy ra lỗi, vui lòng thử lại");
            });
    }

    // Thêm hàm để di chuyển focus giữa các ô OTP
    function moveToNext(current, nextFieldID) {
        if (current.value.length === 1) {
            const nextField = document.getElementById(nextFieldID);
            if (nextField) {
                nextField.focus();
            }
        }
    }

    // Hàm hiển thị thông báo lỗi OTP
    function showOtpError(message) {
        const otpErrorMessage = document.getElementById("otpErrorMessage");
        otpErrorMessage.textContent = message;
        otpErrorMessage.style.display = "block";
        otpErrorMessage.style.color = "#d32f2f";
        otpErrorMessage.style.backgroundColor = "#ffebee";
        otpErrorMessage.style.padding = "10px 15px";
        otpErrorMessage.style.borderRadius = "4px";
        otpErrorMessage.style.marginTop = "10px";
        otpErrorMessage.style.marginBottom = "10px";
        otpErrorMessage.style.border = "1px solid #ffcdd2";
    }

    // Hàm hiển thị thông báo OTP với trạng thái khác nhau
    function showOtpMessage(message, type) {
        const otpErrorMessage = document.getElementById("otpErrorMessage");
        otpErrorMessage.textContent = message;
        otpErrorMessage.style.display = "block";
        otpErrorMessage.style.padding = "10px 15px";
        otpErrorMessage.style.borderRadius = "4px";
        otpErrorMessage.style.marginTop = "10px";
        otpErrorMessage.style.marginBottom = "10px";
        
        switch(type) {
            case "success":
                otpErrorMessage.style.color = "#2e7d32";
                otpErrorMessage.style.backgroundColor = "#e8f5e9";
                otpErrorMessage.style.border = "1px solid #c8e6c9";
                break;
            case "info":
                otpErrorMessage.style.color = "#1565c0";
                otpErrorMessage.style.backgroundColor = "#e3f2fd";
                otpErrorMessage.style.border = "1px solid #bbdefb";
                break;
            default:
                otpErrorMessage.style.color = "#d32f2f";
                otpErrorMessage.style.backgroundColor = "#ffebee";
                otpErrorMessage.style.border = "1px solid #ffcdd2";
        }
    }

    function submitPassword() {
        const newPassword = document.getElementById("passwordInput").value;
        const confirmPassword = document.getElementById("confirmPasswordInput").value;
        const passwordErrorMessage = document.getElementById("passwordErrorMessage");
        
        if (!newPassword || newPassword.length < 8) {
            passwordErrorMessage.textContent = "Mật khẩu phải có ít nhất 8 ký tự.";
            passwordErrorMessage.style.display = "block";
            return;
        }

        if (newPassword !== confirmPassword) {
            passwordErrorMessage.textContent = "Mật khẩu không khớp.";
            passwordErrorMessage.style.display = "block";
            return;
        }

        // Show loading state
        passwordErrorMessage.textContent = "Đang cập nhật mật khẩu...";
        passwordErrorMessage.style.display = "block";
        passwordErrorMessage.style.color = "#1565c0";
        passwordErrorMessage.style.backgroundColor = "#e3f2fd";
        passwordErrorMessage.style.padding = "10px";
        passwordErrorMessage.style.borderRadius = "4px";

        fetch("reset-password", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `newPassword=${encodeURIComponent(newPassword)}&confirmPassword=${encodeURIComponent(confirmPassword)}`,
        })
        .then((response) => {
            return response.text().then(text => ({
                ok: response.ok,
                text: text
            }));
        })
        .then((result) => {
            if (result.ok) {
                // Show success message
                passwordErrorMessage.textContent = result.text;
                passwordErrorMessage.style.color = "#2e7d32";
                passwordErrorMessage.style.backgroundColor = "#e8f5e9";
                
                // Redirect to login page after a short delay
                setTimeout(() => {
                    window.location.href = "login";
                }, 2000);
            } else {
                // Show error message
                passwordErrorMessage.textContent = result.text;
                passwordErrorMessage.style.color = "#d32f2f";
                passwordErrorMessage.style.backgroundColor = "#ffebee";
            }
        })
        .catch(() => {
            passwordErrorMessage.textContent = "Đã xảy ra lỗi, vui lòng thử lại.";
            passwordErrorMessage.style.color = "#d32f2f";
            passwordErrorMessage.style.backgroundColor = "#ffebee";
        });
    }

    // Bind events
    window.validateEmail = validateEmail;
    window.confirmOTP = confirmOTP;
    window.submitPassword = submitPassword;
    window.moveToNext = moveToNext;
});
