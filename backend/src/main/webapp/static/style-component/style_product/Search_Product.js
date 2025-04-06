function addToCart(productId, optionId) {
    fetch("add-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `productId=${productId}&optionId=${optionId}`
    })
        .then(data => {
            console.log(data);
            // alert("Added to cart");

        }).catch(error => console.log(error));

}



function buyNow(productId, optionId) {
    const sessionId = sessionStorage.getItem("sessionId");
    if (!sessionId) {
        alert("Bạn cần đăng nhập trước khi mua hàng!");
        return;
    }

    // Gọi API BuyNowController
    fetch("buy-now", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `productId=${productId}&optionId=${optionId}&sessionId=${sessionId}`
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            window.location.href = "checkout.jsp";
        } else {
            alert(data.message || "Có lỗi xảy ra khi xử lý đơn hàng");
        }
    })
    .catch(error => {
        console.log(error);
        alert("Có lỗi xảy ra. Vui lòng thử lại sau!");
    });
}

