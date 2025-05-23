$(document).ready(function () {

    const back_btn = $('#back_btn');
    back_btn.on('click', function () {
        window.history.back();
    })





//     Review


    const stars = $('.star');
    const ratingInput = $('#rating-value');

    stars.on('click', function () {
        if ($(this).hasClass('disabled')) return;

        const selectedValue = $(this).data('value');
        ratingInput.val(selectedValue);

        stars.each(function () {
            const starValue = $(this).data('value');
            if (starValue <= selectedValue) {
                $(this).find('i').css('color', 'gold');
            } else {
                $(this).find('i').css('color', 'gray');
            }


        });
        console.log(ratingInput.val())
    });


    const btn_submit = $('#btn_submit');
    // btn_submit.on('click', function (e) {
    //     e.preventDefault();
    //
    //     const review_text = $('#review-text');
    //     const order_item= $('.order_item');
    //     const productIds =[];
    //
    //     order_item.each(function () {
    //         const product_id = $(this).data('product-id');
    //         productIds.push(product_id);
    //     })
    //
    //
    //
    //     const order_id = $('.order_id').data('order-id');
    //
    //     const data = {
    //         productIds: productIds,
    //         order_id: order_id,
    //         rating: ratingInput.val(),
    //         description: review_text.val(),
    //     };
    //
    //     fetch('add-review', {
    //         method: 'POST',
    //         body: JSON.stringify(data),
    //         headers: {
    //             'Content-Type': 'application/json',
    //         }
    //     }).then(function (response) {
    //
    //         if (response.status === 200) {
    //             stars.each(function () {
    //                 console.log($(this));
    //                 $(this).addClass('disabled');
    //                 $(this).off('click');
    //                 $(this).find('i').css('color', 'gold');
    //             })
    //
    //             review_text.prop('disabled', true);
    //             btn_submit.prop('disabled', true);
    //         }
    //         return response.json();
    //
    //     }) .catch(function (error) {
    //         console.log(error);
    //     })
    //
    //     console.log(review_text.val());
    //     console.log('Rating: ', ratingInput.val());
    //     console.log("productIds: " ,productIds);
    //     console.log("order_item: " ,order_item);
    //     console.log("order_id:",order_id);
    //
    // })

    btn_submit.on('click', function (e) {
        e.preventDefault();

        const review_text = $('#review-text');
        const order_item = $('.order_item');
        const productIds = [];

        order_item.each(function () {
            const product_id = $(this).data('product-id');
            productIds.push(product_id);
        });

        const order_id = $('.order_id').data('order-id');

        const data = {
            productIds: productIds,
            order_id: order_id,
            rating: ratingInput.val(),
            description: review_text.val(),
        };

        console.log('Đánh giá nội dung:', review_text.val());
        console.log('Rating:', ratingInput.val());
        console.log('Product IDs:', productIds);
        console.log('Số sản phẩm:', order_item.length);
        console.log('Order ID:', order_id);

        fetch('add-review', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(function (response) {
            return response.json().then(json => {
                messageBox.empty();
                if (response.status === 200 && json.status === "success") {
                    messageBox.html(`<p style="color: green;">${json.message}</p>`);

                    stars.each(function () {
                        $(this).addClass('disabled');
                        $(this).off('click');
                        $(this).find('i').css('color', 'gold');
                    });

                    review_text.prop('disabled', true);
                    btn_submit.prop('disabled', true);
                } else {
                    messageBox.html(`<p style="color: red;">${json.message || "Đánh giá thất bại."}</p>`);
                }
            });
        }).catch(function (error) {
            console.error(error);
            messageBox.html(`<p style="color: red;">Lỗi kết nối máy chủ.</p>`);
        });
    });



});
