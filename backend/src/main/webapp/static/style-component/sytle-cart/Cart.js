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






//     Increase/Decrease quantity

    const product= $('.product-item')

    window.onload = function () {
        product.each(function () {

        let price = $(this).find('.price');
        let quantity = ($(this).find('.num'));
        let increment = $(this).find('#increment');
        let decrement = $(this).find('#decrement');
        let remove = $(this).find('.remove');

        let stock = $(this).attr('data-stock');
        let product_id = parseInt($(this).attr('data-id'));



        updatePrice(price, quantity);

        increment.on('click', function () {
            increaseQuantity( $(this), quantity, price, stock, product_id);
        })

        decrement.on('click', function () {
            decreaseQuantity( $(this), quantity, price, product_id);
        })

        remove.on('click', function () {

            let productItem = $(this).closest('.product-item');
            let productId = parseInt(productItem.attr('data-id'));

            removeItem(productId, productItem);
        })




       })
    }


    function updatePrice(price, quantity) {
        let price_value = parseInt(price.attr('data-price'));
        let quantity_value = parseInt(quantity.attr('data-quantity'));
        let total = price_value * quantity_value;

        let formatted = new Intl.NumberFormat('vi-VN').format(total);
        price.text(formatted + 'VND');

    }


    function increaseQuantity( product, quantity , price , stock ,product_id) {
        let newQuantity =parseInt(quantity.attr('data-quantity')) ;



        console.log("product_id: " , product_id);

        if (newQuantity < stock){
            newQuantity += 1;
            quantity.attr('data-quantity', newQuantity);
            quantity.text(newQuantity);

            updatePrice(price, quantity);
            updateQuantity(  product_id, newQuantity );
        }

        else {
            console.log("stock: " , stock);
            alert("Đã đạt số lượng tối đa")
        }





    }



    function decreaseQuantity( product, quantity , price  ,product_id) {
        let newQuantity =parseInt(quantity.attr('data-quantity')) ;

        if(newQuantity  > 1){
            newQuantity -= 1;

            quantity.attr('data-quantity', newQuantity);
            quantity.text(newQuantity);

            updateQuantity(  product_id, newQuantity );
            updatePrice(price, quantity);

        }


    }



    function updateQuantity( productId, quantity ) {
        $.ajax({
            url: '/backend_war_exploded/cart/update-quantity' ,
            method: 'POST',
            data: {
                productId: productId,
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



    function removeItem(productId, productItem){

        if (confirm('Bạn có chắc muốn xóa sản phẩm này khỏi giỏ hàng?')) {
            $.ajax({
                url: '/backend_war_exploded/cart/remove',
                method: 'POST',
                data: {
                    productId: productId,
                },
                success: function (result) {
                    console.log(result);
                    productItem.remove();
                    location.reload();
                },
                error: function (xhr, status, error) {
                    console.log(xhr.responseText);
                    alert('Xóa sản phẩm không thành công. Vui lòng thử lại!');
                }
            });
        }



    }







})


