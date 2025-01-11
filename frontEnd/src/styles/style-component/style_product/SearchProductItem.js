document.addEventListener("DOMContentLoaded", function () {
    const addToCartButtons = document.querySelectorAll(".btn.add");
    const notification = document.getElementById("cart-notification");

    addToCartButtons.forEach(button => {
        button.addEventListener("click", function () {
            notification.classList.remove("hidden");
            notification.classList.add("show");

            setTimeout(() => {
                notification.classList.remove("show");
                notification.classList.add("hidden");
            }, 3000);
        });
    });
});

// document.getElementById('buy-now-btn').addEventListener('click', function () {
//     const mainIframe = window.top.document.querySelector('#body iframe');
//     if (mainIframe) {
//         mainIframe.src = '/web-programming/frontEnd/src/component/Checkout/checkout.html';
//     }
// });

document.getElementById('name').addEventListener('click', function () {
    const mainIframe = window.top.document.querySelector('#body iframe');
    if (mainIframe) {
        mainIframe.src = '/web-programming/frontEnd/src/component/product_detail/Product-detail.html';
    }
});


// const isLoggedIn = localStorage.getItem("isLoggedIn");
// document.getElementById("buy-now-btn").addEventListener("click", (event) => {
//     const mainIframe = window.top.document.querySelector('#body iframe');
//     event.preventDefault();
//     if (!isLoggedIn) {
//         alert("Bạn cần đăng nhập trước!");
//     } else {
//         mainIframe.src = '/web-programming/frontEnd/src/component/Checkout/checkout.html';
//         history.pushState({ page: "user-checkout" }, "Thanh toán", "/checkout");
//     }
// });