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


