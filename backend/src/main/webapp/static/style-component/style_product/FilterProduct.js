$(document).ready(function () {
    const categoryId = $('#sidebar').data('category');
    const apply_btn = $('#apply_btn');
    apply_btn.on('click', function () {

        let optionsId = $('.section_type .item input[type = "checkbox"]:checked').map(
            function () {
                return $(this).data('options-id');
            }
        ).get()


        let selectedPrice = $('.section_price .item input[type = "radio"]:checked').first();

        let minPrice = selectedPrice.length ? selectedPrice.data('min') : null;
        let maxPrice = selectedPrice.length ? selectedPrice.data('max') : null;

        fetch(`product/filter`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            body: JSON.stringify({
                categoryId: categoryId,
                optionsId: optionsId,
                minPrice: minPrice,
                maxPrice: maxPrice,

            })
        }) .then(response => response.json())
            .then(data => {
                console.log("Filtered products: ", data);
                if (!Array.isArray(data)) {
                    console.error("API response is not an array:", data);
                    return;
                }
                reRenderProducts(data);
            })
            .catch(error => {
                console.error("Error fetching filtered products:", error);
            });

    })
})



function reRenderProducts(products) {
    const product_list= $('#product_list');
    product_list.empty();

    if (product_list.length === 0){
        product_list.append('<p>Không có sản phẩm nào phù hợp.</p>');
        return;
    }

    products.forEach( p=> {

        const itemHtml =`
                    <div class="product_item col" data-stock="${p.stock}">

                            <div class="wrap mid_align row">

                                <div class="img_section">
                                    ${p.imageUrl ? `<img src="${p.imageUrl}" alt=""/>` : ''}
                                </div>


                                <div class="infor_section">

                                    <div class="infor_name bold f22" id="name">
                                        <a href="product-detail?id=${p.id}"> ${p.name}</a>
                                    </div>


                                    <div class="infor_color col">
                                        <span class="bold f16">Màu Sắc: <span class="normal f16"> Đen Starry</span></span>

                                        <div class="choose_color row">
                                            <div class="col_item" id="pink"></div>
                                            <div class="col_item" id="gray"></div>
                                            <div class="col_item" id="yellow"></div>
                                        </div>


                                    </div>

                                    <div class="rating row mid_align">
                            <span id="noOfRatting" class="bold" style="padding: 0 5px">
                                4.7 (153)
                                <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                            </span>
                                    </div>


                                    <div id="description">
                                        <ul class="list_descriptions">
                                            <li class="desc_item f14">Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                                            <li class=" desc_item f14">Làm đá tự động nhanh chóng, tiện lợi</li>
                                            <li class="desc_item f14">Công nghệ làm lạnh vòm All Around Cooling</li>
                                        </ul>
                                    </div>

                                </div>


                                <div class="rec_vertical"></div>


                                <div class="section_right col">
                                    <div class="price">
                                        <span class="bold f22">${p.price.toLocaleString()} VND</span>
                                    </div>

                                    <div class="service">
                                        <div class="service_item">
                                            <i class="fa-solid fa-gift"></i>
                                            <span>Ưu đãi thêm 5% (đến 1TRIỆU đồng)</span>
                                        </div>

                                        <div class="service_item">
                                            <i class="fa-solid fa-truck"></i>
                                            <span>Miễn Phí Vận Chuyển Toàn Quốc</span>
                                        </div>

                                        <div class="service_item">
                                            <i class="fa-solid fa-box-open"></i>
                                            <span>Đổi trả trong 14 ngày nếu phát sinh lỗi</span>
                                        </div>

                                        <div class="service_item">
                                            <i class="fa-solid fa-wallet"></i>
                                            <span>Trả Góp 0% Linh Hoạt Đến 24 Tháng</span>
                                        </div>

                                    </div>


                                    <div class="wrap_btn col">
                                         <a href="buy-now?productId=${p.id}&optionId=${p.optionId}" class="btn buy"
                                           id="buy-now-btn">Mua Ngay</a>

                                        <button onclick="addToCart(${p.id},${p.optionId})" class="btn add">
                                            Thêm vào giỏ hàng
                                        </button>

                                    </div>

                                    <div id="cart-notification" class="notification hidden">
                                        <i class="fa fa-check-circle"></i>
                                        <span>Thêm vào giỏ hàng thành công</span>
                                    </div>

                                </div>


                            </div>`


        product_list.append(itemHtml);

    })

}