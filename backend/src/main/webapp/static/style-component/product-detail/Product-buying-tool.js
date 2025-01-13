
// Sử lý variant
$(document).ready(function () {
    const wrap_variant = $('.wrap_variant');
    wrap_variant.each( function() {
        const option_item = $(this).find('.option-item');
        option_item.each(function() {

            $(this).on( 'click',function () {
                const optionId = $(this).attr('data-option-id');
                 $('.option-item').removeClass('selected');
                 $('.option-item[data-option-id="' + optionId + '"]').addClass('selected');


                 //Update price
                 const price = $('#price');
                 const formatedPrice = Number($(this).attr("data-price")).toLocaleString('vi-VN');
                 price.text(formatedPrice +' VND');

                // Update option id cho nút buy now và add to cart




            })

        })
    })

})


