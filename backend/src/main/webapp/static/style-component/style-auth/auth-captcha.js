document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("signInButton");
    if (btn) {
        btn.addEventListener("click", async (e) => {
            e.preventDefault();

            const recaptchaResponse = grecaptcha.getResponse();
            if (!recaptchaResponse) {
                alert("Vui lòng xác nhận bạn không phải là robot.");
                return;
            }

            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            try {
                const res = await fetch(`${contextPath}/login`, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ email, password, recaptcha: recaptchaResponse }),
                });

                const data = await res.json();
                // alert(data.message);
                if (data.status === "success") {
                    location.href = `${contextPath}/home`;
                } else {
                    grecaptcha.reset(); // reset reCAPTCHA nếu lỗi
                }
            } catch (err) {
                alert("Lỗi hệ thống");
                grecaptcha.reset();
            }
        });
    }
});
