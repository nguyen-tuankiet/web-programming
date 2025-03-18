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
    window.location.href = `buy-now?productId=${productId}&optionId=${optionId}`;


    // fetch(`buy-now?productId=${productId}&optionId=${optionId}`, {
    //     method: "POST",
    //     headers: {  "Content-Type": "application/x-www-form-urlencoded"},
    //     // body: `productId=${productId}&optionId=${optionId}`
    // })
    //     .then(response => response.json())
    // .then(data => {
    //     console.log(data);
    //     if (data.ok){
    //         window.location.href = `buy-now?productId=${productId}&optionId=${optionId}`;
    //     }
    //     // window.location.href= "cart";
    //
    //
    // }).catch(error => console.log(error));

}
