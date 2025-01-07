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
})