// Sử lý variant
$(document).ready(function () {

    const buy_now = $('#buy-now');
    const add_to_cart = $('#add-to-cart');
    const product = $('#product');
    const product_id = product.attr('data-id');
    const option_id_default = product.attr('data-option-default');

    const wrap_variant = $('.wrap_variant');


    // Mac dinh select option dau tien

    const option_item = $(wrap_variant).find('.option-item');
    const firstOption = option_item.first();

    wrap_variant.each(function () {
        const option_item = $(this).find('.option-item');
        const firstOption = option_item.first();
        option_item.removeClass('selected');
        firstOption.addClass('selected');


        const price = $('#price');
        const formatedPrice = Number(firstOption.attr("data-price")).toLocaleString('vi-VN');
        price.text(formatedPrice + ' VND');

    })
    // set default cho 2 nut nay
    buy_now.attr('href', 'buy-now?productId=' + product_id + '&optionId=' + firstOption.attr('data-option-id'));
    add_to_cart.on('click', function (e) {
        e.preventDefault();
        addToCart(product_id, firstOption.attr('data-option-id'));
    })




    wrap_variant.each(function () {
        const option_item = $(this).find('.option-item');
        option_item.each(function () {

            $(this).on('click', function () {
                const optionId = $(this).attr('data-option-id');
                $('.option-item').removeClass('selected');
                $('.option-item[data-option-id="' + optionId + '"]').addClass('selected');


                //Update price
                const price = $('#price');
                const formatedPrice = Number($(this).attr("data-price")).toLocaleString('vi-VN');
                price.text(formatedPrice + ' VND');

                // Update option id cho nút buy now và add to cart

                buy_now.attr('href', 'buy-now?productId=' + product_id + '&optionId=' + optionId);


            })
        })

    })


})


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
            // alert("Sản Phẩm đã");

        }).catch(error => console.log(error));

}


