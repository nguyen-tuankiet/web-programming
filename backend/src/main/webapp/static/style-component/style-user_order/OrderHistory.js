$(document).ready(function(){

    const back_btn = $('#back_btn');
    back_btn.on('click', function () {

        const message = {
            type: 'openUserProfile',
            src: $(back_btn).attr('data-src'),
        }

        console.log(message);
        window.parent.postMessage(message, '*')
    })



    const price = $('#product_price');
    const VAT = $('#VAT');
    const total_charge = $('#total_charge');
    const total_charge_value= parseInt(total_charge.text().replaceAll('.','').replaceAll('VND',''));


    const newVat = total_charge_value *10/100;
    const newPrice = total_charge_value - newVat;

    price.text(Intl.NumberFormat('vi-VN').format(newPrice) + ' VND');

    VAT.text(Intl.NumberFormat('vi-VN').format(newVat) + ' VND');

    console.log(total_charge_value)
    console.log(newVat)
    console.log(newPrice)



});
