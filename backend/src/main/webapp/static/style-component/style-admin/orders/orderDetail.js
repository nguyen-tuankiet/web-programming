$(document).ready(function () {

        const before_tax = $('#before_tax');
        const VAT = $('#VAT');
        const total = $('#total_charge').attr('data-total');
        const vat_value = total * 10/100;
        const bt_value = total - vat_value ;


        before_tax.text(Intl.NumberFormat('vi-VN').format(bt_value) + 'VND');
        VAT.text(Intl.NumberFormat('vi-VN').format(vat_value) + 'VND');



})