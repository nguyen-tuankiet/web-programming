<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 12/27/2024
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Feature</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/style-component/product-detail/Product-Feature.css">
</head>
<body>

<!-- Feature benefit 1-->
<div class="feature-benefit1">
    <div class="feature-benefit1__img">
        <img src="<%= request.getContextPath() %>/static/image/img-detail/feature1.jpg" alt="Carousel Image1" class="imgFeature1">
    </div>
    <div class="feature-benefit1__text">
        <h2>+20L dung tích với cùng kích thước</h2>
        <h3>Công nghệ SpaceMax™</h3>
        <p>Mở rộng không gian lưu trữ tối đa với công nghệ SpaceMax™ tối ưu vật liệu cách nhiệt sử dụng bên trong, mang
            đến lớp vách mỏng hơn thông thường mà vẫn đảm bảo hiệu suất hoạt động tối ưu. Dung tích tăng thêm 20L cho
            bạn thoải mái lưu trữ mọi loại thực phẩm yêu thích.</p>
        <p class="note">*Dựa trên RT50K6351BS và có thể thay đổi tuỳ theo mỗi dòng sản phẩm.</p>
    </div>
</div>

<!-- Feature benefit 2-->
<div class="feature-benefit2">
    <div class="feature-benefit2__text">
        <h2>Khử mùi, làm sạch không khí</h2>
        <h3>Hệ thống lọc than hoạt tính</h3>
        <p>Than hoạt tính giúp làm sạch không khí bên trong, loại bỏ vi khuẩn gây mùi hôi khó chịu và giữ lại không khí
            trong lành cho thức ăn luôn tươi ngon, trọn vị.</p>
    </div>
    <div class="feature-benefit2__img">
        <img src="<%= request.getContextPath() %>/static/image/img-detail/feature2.jpg" alt="Carousel Image 2" class="imgFeature2">
    </div>
</div>

<div class="two-column">
    <div class="column">
        <img src="<%= request.getContextPath() %>/static/image/img-detail/two-collum1.jpg" alt="two-collum1" class="column-img">
        <h2>Làm lạnh thực phẩm, đồ uống tức thì</h2>
        <h3>Chế độ Power Cool</h3>
        <p>Với chế độ Power Cool, một luồng khí mát lạnh sẽ được thổi ra nhanh chóng, giúp cho thực phẩm hay đồ uống của
            bạn mát lạnh tức thì.</p>
    </div>
    <div class="column">
        <img src="<%= request.getContextPath() %>/static/image/img-detail/two-collum2.jpg" alt="two-collum2" class="column-img">
        <h2>Nhiệt độ đồng đều, giữ trọn tươi ngon</h2>
        <h3>Hệ thống làm lạnh vòm All-around Cooling</h3>
        <p>Hệ thống làm lạnh vòm All-around Cooling kiểm soát chặt chẽ sự thay đổi nhiệt độ và luân chuyển hơi lạnh đều
            đặn thông qua hệ thống lỗ khí bố trí thông minh, duy trì ổn định nhiệt độ và độ ẩm. Không khí lạnh lan tỏa
            đều khắp tủ, giúp thực phẩm tươi ngon trong thời gian lâu hơn.</p>
    </div>
</div>

<!-- Feature benefit 3-->
<div class="feature-benefit3">
    <div class="feature-benefit3__img">
        <img src="<%= request.getContextPath() %>/static/image/img-detail/feature3.jpg" alt="Carousel Image3" class="imgFeature1">
    </div>
    <div class="feature-benefit3__text">
        <h2>Thoải mái tận hưởng đá lạnh</h2>
        <h3>Khay đá xoay di động</h3>
        <p>Chỉ cần xoay nhẹ khay đá, dễ dàng có ngay những viên đá tinh khiết. Khay đá xoay di động có thể được tháo rời
            để mở rộng không gian cho ngăn đông.</p>
        <p class="note">* Chỉ có ở một số mẫu sản phẩm nhất định.</p>
    </div>
</div>

</body>
</html>