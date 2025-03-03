$(document).ready(function () {
    window.onload = function () {


        const productList = $('.product-item');
        let totalPrice = 0;
        let total = $('#total')
        let VAT = $('#VAT')
        let before_tax = $('#before_tax')

        productList.each(function () {
            const price = $(this).find('#price').attr('data-price');
            const quantity = $(this).find('#quantity').attr('data-quantity');

            console.log(price);
            totalPrice +=  quantity * price;



        })

        const checkout_tax = totalPrice * 10 /100;
        const checkout_before_tax = totalPrice - checkout_tax;

        console.log("tax  : " ,checkout_tax);
        console.log("b_t  : " ,checkout_before_tax);




        total.text(Intl.NumberFormat('vi-VN').format(totalPrice) + ' VND');
        VAT.text(Intl.NumberFormat('vi-VN').format(checkout_tax) + ' VND');
        before_tax.text(Intl.NumberFormat('vi-VN').format(checkout_before_tax) + ' VND');
    }


    const pay = $('#pay')
    pay.on('click', function () {
        const address_id = $('#address').attr('data-address-id');
        const card =$('input[name="payment-method"]:checked').attr('data-payment');
        const product_item  = $('.product-item');
        const products= [];

        product_item.each(function () {
            const id = $(this).data('id');
            const quantity = $(this).data('quantity');
            const optionId = $(this).data('option-id');
            const price = $(this).data('price');
            const total = parseInt(price) * parseInt(quantity);


            products.push({
                id: id,
                quantity: quantity,
                optionId: optionId,
                total: total,
            })


        })

        console.log("products: ", products);
        console.log("product_item: ",product_item);
        console.log("payment method: " ,card);
        console.log("address_id: ", address_id);



        const form = {
            address_id: address_id,
            card: card,
            products: products,
        }

        fetch(`checkout`,
            {
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(form),
            }
        ).then(function (response) {
            if (!response.ok) {
                console.error(response);
            }
            return response.json();
        }).then(function (data) {
            if (data.success) {
                window.location.href = "success";
            } else {
                alert('Có lỗi xảy ra trong quá trình xử lý đơn hàng.');
            }
        })


     })






})