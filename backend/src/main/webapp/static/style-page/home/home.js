document.addEventListener("DOMContentLoaded", () => {
    const menuItems = document.querySelectorAll(".navbar ul li");
    const userLoginIcon = document.querySelector(".user-login");
    const userPopup = document.querySelector(".user-popup");
    const iframe = document.querySelector("#body iframe");

    let isLoggedIn = sessionStorage.getItem("userId") && sessionStorage.getItem("sessionId");

    // Hiệu ứng hover cho menu
    menuItems.forEach((item) => {
        let timeout;

        item.addEventListener("mouseenter", () => {
            clearTimeout(timeout);
            menuItems.forEach((i) => i.classList.remove("active"));
            item.classList.add("active");
        });

        item.addEventListener("mouseleave", () => {
            timeout = setTimeout(() => {
                item.classList.remove("active");
            }, 200);
        });

        const submenu = item.querySelector(".submenu");
        if (submenu) {
            submenu.addEventListener("mouseenter", () => {
                clearTimeout(timeout);
            });

            submenu.addEventListener("mouseleave", () => {
                timeout = setTimeout(() => {
                    item.classList.remove("active");
                }, 200);
            });
        }
    });

    userLoginIcon.addEventListener("mouseenter", () => {
        userPopup.style.display = "block";
    });

    userLoginIcon.addEventListener("mouseleave", () => {
        setTimeout(() => {
            userPopup.style.display = "none";
        }, 200);
    });

    userPopup.addEventListener("mouseenter", () => {
        userPopup.style.display = "block";
    });

    userPopup.addEventListener("mouseleave", () => {
        userPopup.style.display = "none";
    });

    // Xử lý nút tìm kiếm
    document.getElementById("search-icon").addEventListener("click", () => {
        document.getElementById("search-overlay").style.display = "flex";
    });

    document.getElementById("close-search-overlay").addEventListener("click", () => {
        document.getElementById("search-overlay").style.display = "none";
    });

    // Xử lý nút "Trang của tôi"
    document.getElementById("my-page-link").addEventListener("click", (event) => {
        event.preventDefault();
        if (!isLoggedIn) {
            alert("Bạn cần đăng nhập trước!");
        } else {
            // iframe.src = "/web-programming/frontEnd/src/pages/UserProfile.html";
            history.pushState({ page: "user-profile" }, "Trang của tôi", "profile");
        }
    });

    window.addEventListener("message", (event) => {
        if (event.data.type === "navigate") {
            iframe.src = event.data.url;
            history.pushState({page:"checkout"}, "Thanh toán", "checkout");
        }
    });

    // Xử lý trạng thái đăng nhập
    const loginLink = document.getElementById("login-link");
    if (loginLink) {
        if (isLoggedIn) {
            loginLink.textContent = "Đăng xuất";
            loginLink.addEventListener("click", (event) => {
                event.preventDefault();
                sessionStorage.removeItem("userId");
                sessionStorage.removeItem("sessionId");
                sessionStorage.removeItem("role");
                alert("Bạn đã đăng xuất!");
                window.location.href = 'login';
            });
        } else {
            loginLink.textContent = "Đăng nhập/Đăng ký";
            loginLink.addEventListener("click", (event) => {
                event.preventDefault();
                window.location.href = 'login';
            });
        }
    }
});
