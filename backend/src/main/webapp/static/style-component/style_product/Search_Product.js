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
    else {

        window.location.href = `buy-now?productId=${productId}&optionId=${optionId}`;
    }


}

