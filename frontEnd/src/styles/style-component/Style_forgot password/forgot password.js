// Hàm kiểm tra email và hiển thị hộp nhập OTP
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

// Chuyển đến ô tiếp theo khi nhập mã OTP
function moveToNext(current, nextFieldID) {
    if (current.value.length >= 1) {
        document.getElementById(nextFieldID).focus();
    }
}

// Xác thực OTP và hiển thị hộp nhập mật khẩu mới
function confirmOTP() {
    const otpCode = ["otp1", "otp2", "otp3", "otp4", "otp5"].map(id => document.getElementById(id).value).join("");
    const errorMessage = document.getElementById("errorMessage");

    if (otpCode.length === 5 && otpCode.match(/^\d{5}$/)) { // Kiểm tra mã OTP đủ 5 chữ số
        errorMessage.style.display = "none";  // Ẩn thông báo lỗi nếu mã OTP hợp lệ
        document.getElementById("otpBox").style.display = "none";  // Ẩn hộp nhập OTP
        document.getElementById("passwordBox").style.display = "block"; // Hiển thị hộp nhập mật khẩu
    } else {
        errorMessage.textContent = "Vui lòng nhập đủ mã OTP (5 chữ số).";
        errorMessage.style.display = "block";  // Hiển thị thông báo lỗi nếu mã OTP không đầy đủ
    }
}

// Đặt lại mật khẩu
function submitPassword() {
    const passwordInput = document.getElementById("passwordInput").value.trim();
    const confirmPasswordInput = document.getElementById("confirmPasswordInput").value.trim();
    const errorMessage = document.getElementById("passwordErrorMessage");

    if (passwordInput.length < 8) {
        errorMessage.textContent = "Mật khẩu phải có ít nhất 8 ký tự.";
        errorMessage.style.display = "block";
    } else if (passwordInput !== confirmPasswordInput) {
        errorMessage.textContent = "Mật khẩu không khớp.";
        errorMessage.style.display = "block";
    } else {
        errorMessage.style.display = "none";
        alert("Mật khẩu đã được đặt lại thành công!");
    }
}

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
    window.location.href = '../../../pages/auth/auth.html';
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
