$(document).ready(function () {

    let fee= 0;

    window.onload = async function () {


        const productList = $('.product-item');
        let totalPrice = 0;
        let total = $('#total')
        let VAT = $('#VAT')
        let before_tax = $('#before_tax')
        let ship_fee = $('#ship_fee')


        //for shipping
        const items = []


        productList.each(function () {
            const price = $(this).find('#price').attr('data-price');
            const quantity = $(this).find('#quantity').attr('data-quantity');

            console.log(price);
            totalPrice += quantity * price;


            const height = $(this).data('height');
            const width = $(this).data('width');
            const length = $(this).data('length');
            const weight = $(this).data('weight');

            items.push({
                name: "item",
                quantity: parseFloat(quantity),
                height: height,
                weight: weight,
                width: width,
                length: length,
            })

        })


        address = {
            districtId: $('#address').data('district-id'),
            communeId: $('#address').data('commune-id'),
        }

        console.log("Ship items: ", items);
        console.    log("Address : ", address);
        fee = await getShipFee(items, address);
        console.log("ship_fee: ",  fee);

        totalPrice +=  fee;


        const checkout_tax = totalPrice * 10 / 100;
        const checkout_before_tax = totalPrice - checkout_tax;

        console.log("tax  : ", checkout_tax);
        console.log("b_t  : ", checkout_before_tax);


        total.text(Intl.NumberFormat('vi-VN').format(totalPrice) + ' VND');
        VAT.text(Intl.NumberFormat('vi-VN').format(checkout_tax) + ' VND');
        before_tax.text(Intl.NumberFormat('vi-VN').format(checkout_before_tax) + ' VND');
        ship_fee.text(Intl.NumberFormat('vi-VN').format(fee) + ' VND');
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
        console.log("ship_fee", fee);



        const form = {
            address_id: address_id,
            card: card,
            products: products,
            ship_fee: fee,

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


async function getShipFee(items, address) {
    const payload = {
        service_type_id: 5,
        to_district_id: parseInt(address.districtId),
        to_ward_code: String(address.communeId),
        weight: 1,
        items: items,
    };

    if (items.length > 0) {
        try {
            const response = await fetch(`https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    "Token": "676e7671-116a-11f0-95d0-0a92b8726859",
                    "ShopId": 196324
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                console.error("HTTP Error:", response.status);
                return 0;
            }

            const data = await response.json();

            if (data.code === 200) {
                const total = data.data.total;
                console.log("Tổng phí vận chuyển:", total);
                return total;
            } else {
                console.error("GHN trả về lỗi:", data.message);
                return 0;
            }
        } catch (error) {
            console.error("Lỗi khi gọi API GHN:", error);
            return 0;
        }
    } else {
        return 0;
    }

}

// Bổ sung code chọn địa chỉ bằng POST
$(document).ready(function() {
    // Sự kiện click 'Thay đổi' gọi showChooseAddressPopup_POST
    $(document).on('click', '.change', function(e) {
        e.preventDefault();
        showChooseAddressPopup_POST();
    });

    // Đóng popup khi click vào dấu X
    $(document).on('click', '#close-address-popup', function() {
        $('#choose-address-popup').hide();
    });
});

async function showChooseAddressPopup_POST() {
    let userId = sessionStorage.getItem("userId");
    if (!userId) {
        alert("Không tìm thấy userId!");
        return;
    }

    try {
        const response = await fetch(`/backend_war/address?userId=${encodeURIComponent(userId)}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: null // hoặc body: ""
        });

        const res = await response.json();

        if (res.success && res.data && res.data.length > 0) {
            let html = '';
            res.data.forEach(function(addr) {
                html += `
                  <div class="popup-address-item">
                    <div>
                        <b>${addr.name}</b> | ${addr.phone}<br>
                        <span>${addr.detail}, ${addr.commune}, ${addr.district}, ${addr.province}</span>
                    </div>
                    <button type="button" class="select-address-btn" 
                        data-address-id="${addr.id}" 
                        data-province-id="${addr.provinceId}"
                        data-district-id="${addr.districtId}"
                        data-commune-id="${addr.communeId}"
                        data-name="${addr.name}"
                        data-phone="${addr.phone}"
                        data-detail="${addr.detail}"
                        data-commune="${addr.commune}"
                        data-district="${addr.district}"
                        data-province="${addr.province}"
                    >Chọn</button>
                  </div>
                `;
            });
            $('#address-list-popup').html(html);
            $('#choose-address-popup').show();
        } else {
            $('#address-list-popup').html('<div>Không có địa chỉ nào.</div>');
            $('#choose-address-popup').show();
        }
    } catch (error) {
        $('#address-list-popup').html('<div>Lỗi khi tải danh sách địa chỉ!</div>');
        $('#choose-address-popup').show();
    }
}




$(document).on('click', '.select-address-btn', async function() {
    let $btn = $(this);

    $('#address').attr('data-address-id', $btn.data('address-id'))
        .attr('data-province-id', $btn.data('province-id'))
        .attr('data-district-id', $btn.data('district-id'))
        .attr('data-commune-id', $btn.data('commune-id'));

    $('#address .name').text($btn.data('name'));
    $('#address .phone').text($btn.data('phone'));
    $('.address_detail span').text(
        $btn.data('detail') + ', ' + $btn.data('commune') + ', ' + $btn.data('district') + ', ' + $btn.data('province')
    );

    $('#choose-address-popup').hide();

    // Gọi lại tính phí ship mới (nếu có hàm này)
    if (typeof recalcShipFee === 'function') {
        await recalcShipFee();
    }
});
