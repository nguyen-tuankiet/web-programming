

document.addEventListener("DOMContentLoaded", () => {
    const menuItems = document.querySelectorAll(".navbar ul li");
    const userLoginIcon = document.querySelector(".user-login");
    const userPopup = document.querySelector(".user-popup");
    const iframe = document.querySelector("#body iframe");

    let isLoggedIn = sessionStorage.getItem("userId") && sessionStorage.getItem("sessionId");

    // Hiệu ứng hover cho menu
    menuItems.forEach((item) => {
        let timeout;

        item.addEventListener("mouseenter", () => {
            clearTimeout(timeout);
            menuItems.forEach((i) => i.classList.remove("active"));
            item.classList.add("active");
        });

        item.addEventListener("mouseleave", () => {
            timeout = setTimeout(() => {
                item.classList.remove("active");
            }, 200);
        });

        const submenu = item.querySelector(".submenu");
        if (submenu) {
            submenu.addEventListener("mouseenter", () => {
                clearTimeout(timeout);
            });

            submenu.addEventListener("mouseleave", () => {
                timeout = setTimeout(() => {
                    item.classList.remove("active");
                }, 200);
            });
        }
    });

    userLoginIcon.addEventListener("mouseenter", () => {
        userPopup.style.display = "block";
    });

    userLoginIcon.addEventListener("mouseleave", () => {
        setTimeout(() => {
            userPopup.style.display = "none";
        }, 200);
    });

    userPopup.addEventListener("mouseenter", () => {
        userPopup.style.display = "block";
    });

    userPopup.addEventListener("mouseleave", () => {
        userPopup.style.display = "none";
    });

    // Xử lý nút tìm kiếm
    document.getElementById("search-icon").addEventListener("click", () => {
        document.getElementById("search-overlay").style.display = "flex";
    });

    document.getElementById("close-search-overlay").addEventListener("click", () => {
        document.getElementById("search-overlay").style.display = "none";
    });

    // Xử lý nút "Trang của tôi"
    document.getElementById("my-page-link").addEventListener("click", (event) => {
        event.preventDefault();
        if (!isLoggedIn) {
            alert("Bạn cần đăng nhập trước!");
        } else {
            // iframe.src = "/web-programming/frontEnd/src/pages/UserProfile.html";
            history.pushState({ page: "user-profile" }, "Trang của tôi", "profile");
        }
    });

    window.addEventListener("message", (event) => {
        if (event.data.type === "navigate") {
            iframe.src = event.data.url;
            history.pushState({page:"checkout"}, "Thanh toán", "checkout");
        }
    });

    // Xử lý trạng thái đăng nhập
    const loginLink = document.getElementById("login-link");
    if (loginLink) {
        if (isLoggedIn) {
            loginLink.textContent = "Đăng xuất";
            loginLink.addEventListener("click", (event) => {
                event.preventDefault();
                sessionStorage.removeItem("userId");
                sessionStorage.removeItem("sessionId");
                sessionStorage.removeItem("role");
                alert("Bạn đã đăng xuất!");
                window.location.href = 'login';
            });
        } else {
            loginLink.textContent = "Đăng nhập/Đăng ký";
            loginLink.addEventListener("click", (event) => {
                event.preventDefault();
                window.location.href = 'login';
            });
        }
    }
});










$(document).ready(function () {


    const pay = $('#pay')

    pay.on('click', function () {

        let isLoggedIn = sessionStorage.getItem("userId") && sessionStorage.getItem("sessionId");

        if (!isLoggedIn) {
            alert("Bạn cần đăng nhập trước!");
            event.preventDefault(); // Ngừng hành động (không chuyển hướng)
            return;
        }


        const products =[]

        $('.product_checked:checked').each(function () {
            products.push(($(this).val()))
        })


        if (products.length === 0) {
            alert('Chọn ít nhất một sản phẩm để thanh toán!');
            return;
        }

        const body= products.join(',');

        window.location.href="checkout?productIds=" + encodeURIComponent(body);


        });

    //xu  ly chua dang nhap




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

        updateBill();




    }


    function updatePrice(price, quantity) {
        let price_value = parseInt(price.attr('data-price'));
        let quantity_value = parseInt(quantity.attr('data-quantity'));
        let total = price_value * quantity_value;

        let formatted = new Intl.NumberFormat('vi-VN').format(total);
        price.text(formatted + ' VND');

        console.log("updatePrice: ", price);


        updateBill();

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

        updateBill();


    }



    function updateQuantity( productId, quantity ) {
        $.ajax({
            url: '/cart/update-quantity' ,
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


        updateBill();



    }



    function updateBill(){
        const priceList = $('.price')

        console.log("updateBill: ", priceList);


        let totalPrice = 0;
        let total = $('#total')
        let VAT = $('#VAT')
        let before_tax = $('#before_tax')

        priceList.each(function () {

            console.log("price item: ", this);

            let price =  $(this).text().replace(' VND', '').replaceAll('.', '');
            console.log("price:", price);
            totalPrice += parseInt(price);
        })


        const tax = totalPrice * 10 /100;
        const b_t = totalPrice - tax;

        console.log("tax  : " ,tax);
        console.log("b_t  : " ,b_t);




        total.text(Intl.NumberFormat('vi-VN').format(totalPrice) + ' VND');
        VAT.text(Intl.NumberFormat('vi-VN').format(tax) + ' VND');
        before_tax.text(Intl.NumberFormat('vi-VN').format(b_t) + ' VND');




    }



})


