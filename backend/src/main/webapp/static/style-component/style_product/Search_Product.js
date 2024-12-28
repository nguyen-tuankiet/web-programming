function addToCart(productId, optionId) {
    fetch("/backend_war_exploded/add-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `productId=${productId}&optionId=${optionId}`
    })
        .then(data => {
            console.log(data);
            alert("Added to cart");

        }).catch(error => console.log(error));

}

