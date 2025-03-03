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
    fetch("buy-now", {
        method: "POST",
        headers: {  "Content-Type": "application/x-www-form-urlencoded"},
        body: `productId=${productId}&optionId=${optionId}`
    })
    .then(data => {
        console.log(data);
        if (data.ok){
            // window.location.href = "cart";
        }
        // window.location.href= "cart";


    }).catch(error => console.log(error));

}
