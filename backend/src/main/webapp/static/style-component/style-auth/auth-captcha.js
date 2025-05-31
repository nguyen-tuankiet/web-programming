// document.addEventListener("DOMContentLoaded", () => {
//     const btn = document.getElementById("signInButton");
//     if (!btn) return;
//
//     btn.addEventListener("click", async (e) => {
//         e.preventDefault();
//
//         const recaptchaResponse = grecaptcha.getResponse();
//         if (!recaptchaResponse) {
//             alert("Vui lòng xác nhận bạn không phải là robot.");
//             return;
//         }
//
//         const email = document.getElementById("email").value.trim();
//         const password = document.getElementById("password").value;
//         const rememberCheckbox = document.getElementById("remember-checkbox");
//
//         // Gọi hàm lưu nếu user chọn "Ghi nhớ đăng nhập"
//         if (rememberCheckbox && rememberCheckbox.checked) {
//             saveCredentials(email, password);
//         } else {
//             clearSavedCredentials();
//         }
//
//         try {
//             const res = await fetch(`${contextPath}/login`, {
//                 method: "POST",
//                 headers: { "Content-Type": "application/json" },
//                 credentials: "include", // bắt buộc để nhận session
//                 body: JSON.stringify({ email, password, recaptcha: recaptchaResponse }),
//             });
//
//             const data = await res.json();
//             if (data.status === "success") {
//                 sessionStorage.setItem("sessionId", data.data.sessionId);
//                 sessionStorage.setItem("userId", data.data.id);
//                 sessionStorage.setItem("role", data.data.role);
//
//                 if (data.data.role !== "USER") {
//                     window.location.href = `${contextPath}/admin/dashboard`;
//                 } else {
//                     window.location.href = `${contextPath}/home`;
//                 }
//             } else {
//                 alert("Lỗi đăng nhập: " + data.message);
//                 grecaptcha.reset();
//             }
//         } catch (err) {
//             alert("Lỗi hệ thống khi đăng nhập.");
//             grecaptcha.reset();
//         }
//     });
// });
//
// // Cho phép gọi lại các hàm từ auth.js
// function saveCredentials(email, password) {
//     localStorage.setItem('remembered_email', email);
//     localStorage.setItem('remembered_password', btoa(password));
//     localStorage.setItem('remember_me', 'true');
// }
//
// function clearSavedCredentials() {
//     localStorage.removeItem('remembered_email');
//     localStorage.removeItem('remembered_password');
//     localStorage.removeItem('remember_me');
// }
