<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/27/24
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Title</title>
</head>
<body>

<div id="search_body">

    <div class="wrap_img">
        <img id="image" src="${pageContext.request.contextPath}/static/image/tulanh.jpeg" alt="" height="225"
             width="225"/>
    </div>

    <div class="infor mid_align col ">
        <div id="name" class="bold f16">
            <a href="#"> Tủ Lạnh 4 Cửa RF48A4000B4 với Twin Cooling Plus™ 488 L</a>
        </div>



        <div id="price" class="bold f22">
            20.490.000 ₫

            <span id="ratting" class="" style="padding: 0 5px">
                5 (153)
              <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
            </span>

        </div>


        <div id="description">
            <ul class="list_descriptions">
                <li class="desc_item f12">Ngăn Đông Mềm -1 độ giữ thịt cá tươi ngon</li>
                <li class=" desc_item f12">Làm đá tự động nhanh chóng, tiện lợi</li>
                <li class="desc_item f12">Công nghệ làm lạnh vòm All Around Cooling</li>
            </ul>
        </div>

    </div>


    <div class="operation col">
        <button id="buy_now" onclick="checkLoginAndBuy(event)">Mua ngay</button>
        <button class="btn add">Thêm vào giỏ hàng</button>
    </div>
    <div id="cart-notification" class="notification hidden">
        <i class="fa fa-check-circle"></i>
        <span>Thêm vào giỏ hàng thành công</span>
    </div>


</div>
<script>
function checkLoginAndBuy(event) {
    event.preventDefault();
    const sessionId = sessionStorage.getItem("sessionId");
    if (!sessionId) {
        alert("Bạn cần đăng nhập trước khi mua hàng!");
        return;
    }

    // Gọi API BuyNowController
    fetch("buy-now", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `productId=${product.id}&optionId=${product.optionId}&sessionId=${sessionId}`
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            window.location.href = "checkout.jsp";
        } else {
            alert(data.message || "Có lỗi xảy ra khi xử lý đơn hàng");
        }
    })
    .catch(error => {
        console.log(error);
        alert("Có lỗi xảy ra. Vui lòng thử lại sau!");
    });
}
</script>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/TopProduct.js"></script>


</body>
