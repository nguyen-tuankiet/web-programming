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


    // // Xử lý nút tìm kiếm
    // document.getElementById("search-icon").addEventListener("click", () => {
    //     document.getElementById("search-overlay").style.display = "flex";
    // });

    document.getElementById("close-search-overlay").addEventListener("click", () => {
        document.getElementById("search-overlay").style.display = "none";
    });

    document.addEventListener("DOMContentLoaded", () => {
        const searchIcon = document.getElementById("search-icon");
        const searchOverlay = document.getElementById("search-overlay");
        const closeSearchOverlay = document.getElementById("close-search-overlay");


        // // Khi bấm vào biểu tượng tìm kiếm, hiển thị overlay tìm kiếm
        // searchIcon.addEventListener("click", () => {
        //     // Gửi yêu cầu AJAX để tải nội dung từ search.jsp
        //
        //     fetch("search.jsp")
        //         .then(response => response.text())
        //          .then(data => {
        //             document.getElementById("search-content").innerHTML = data;
        //             searchOverlay.style.display = "flex";
        //         })
        //         .catch(error => console.error("Lỗi khi tải search.jsp:", error));
        // });

        // Khi bấm nút đóng, ẩn overlay tìm kiếm
        closeSearchOverlay.addEventListener("click", () => {
            searchOverlay.style.display = "none";
        });
    });











    // Xử lý nút "Trang của tôi"
    document.getElementById("my-page-link").addEventListener("click", (event) => {
        event.preventDefault();
        if (!isLoggedIn) {
            alert("Bạn cần đăng nhập trước!");
        } else {
            window.location.href = 'user-profile';
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
                // alert("Bạn đã đăng xuất!");
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


function showSearchOverlay() {
    document.getElementById("search-overlay").style.display = "flex";



        fetch("/suggest")
            .then(response => response.json())
            .then(products => {
                const suggestionsContainer = document.querySelector(".product-suggestions");
                suggestionsContainer.innerHTML = "";

                if (products.length === 0) {
                    suggestionsContainer.innerHTML = "<p>Không có sản phẩm gợi ý.</p>";
                    return;
                }

                products.forEach(product => {
                    const productItem = document.createElement("div");
                    productItem.className = "product";
                    productItem.innerHTML = `
                        <img src="${product.imageUrl}" alt="${product.name}">
                        <div class="product-content">
<!--                            <p class="product-name">${product.name}</p>-->
                            <a href="product-detail?id=${encodeURIComponent(product.id)}"  class="product-name">
                                   ${product.name}
                                   
                            </a>
                            <span class="product-price">${formatCurrency(product.price)}</span>
                        </div>
                    `;
                    suggestionsContainer.appendChild(productItem);
                });
            })
            .catch(error => console.error("Lỗi khi lấy danh sách gợi ý sản phẩm:", error));
}



// Đóng overlay tìm kiếm khi bấm vào nút đóng
document.getElementById("close-search-overlay").addEventListener("click", function() {
    document.getElementById("search-overlay").style.display = "none";
});

function formatCurrency(amount) {
    return new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND"
    }).format(amount);
}

function updateCartCount(count) {
    const cartLink = document.getElementById('cart-link');
    if (!cartLink) return;

    // Remove existing count badge if any
    const existingBadge = cartLink.querySelector('.cart-count-badge');
    if (existingBadge) {
        existingBadge.remove();
    }

    // Add new count badge if count > 0
    if (count > 0) {
        const badge = document.createElement('span');
        badge.className = 'cart-count-badge';
        badge.textContent = count;
        cartLink.appendChild(badge);
    }
}


