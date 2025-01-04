$(document).ready(function () {
    const pay = $('#pay')
    pay.on('click', function () {

        const message ={
            type:'navigate',
            url: pay.attr('data-src')
        }

        console.log(message);
        window.parent.postMessage(message, '*');
    })






//     Increase quantity

    const product= $('.product-item')

    window.onload = function () {
        product.each(function () {

        let price = $(this).find('.price');
        let quantity = ($(this).find('.num'));
        let increment = $(this).find('#increment');
        let decrement = $(this).find('#decrement');


        updatePrice(price, quantity);



        increment.on('click', function () {
            increaseQuantity( $(this), quantity, price);
        })









       })
    }


    function updatePrice(price, quantity) {


        let price_value = parseInt(price.attr('data-price'));
        let quantity_value = parseInt(quantity.attr('data-quantity'));
        let total = price_value * quantity;

        let formatted = new Intl.NumberFormat('vi-VN').format(total);
        price.text(formatted + 'VND');





    }


    function increaseQuantity( product, quantity , price ) {


        let newQuantity =quantity +1;

        updatePrice(price, newQuantity);
        quantity.text(newQuantity);

    }



    function updateQuantity( product, quantity ) {
        $.ajax({
            url: '/backend_war_exploded/update-quantity' ,
            method: 'POST',
            data: {
                product: product,
                quantity: quantity
            },
            success: function (result) {
                console.log(result);
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
            }


        })
    }








    // decrement.on('click', function () {
    //     if (parseInt(quantity.text()) > 1) {
    //         let newQuantity = parseInt($('#quantity').text()) -1;
    //         quantity.text(newQuantity);
    //     }
    //
    // })

})


