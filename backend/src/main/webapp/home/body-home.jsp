
<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/24/24
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SmartThings</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/homeBody.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<div id="banner">
  <jsp:include page="banner.jsp"/>
</div>


<section class="body">
  <h2>Ngôi nhà thông minh, cuộc sống tiện nghi</h2>
  <p>
    Tận hưởng sự tiện lợi với các thiết bị gia dụng tích hợp công nghệ AI hiện đại. Quản lý năng lượng thông minh, tối ưu hóa sử dụng và tiết kiệm chi phí. Hãy để ngôi nhà của bạn trở thành trung tâm của sự đổi mới với công nghệ SmartThings.
  </p>

  <div class="body-image">
    <img src="${pageContext.request.contextPath}/static/image/dien_gia_dung_5cc56fdc21.png" alt="SmartThings Setup">
  </div>
</section>
<section class="product-showcase">
  <div class="product-grid">
    <!--        <div class="product-item">-->
    <!--            <img src="../../../resource/image/pc-1440x810-t4ref.jpg" alt="Galaxy S23+">-->
    <!--            <h3>Tủ Lạnh Bespoke</h3>-->
    <!--            <a href="#" class="buy-button">Mua ngay</a>-->
    <!--        </div>-->
    <div class="product-item">
      <img src="${pageContext.request.contextPath}/static/image/pc-1440x810bic.jpg" alt="The Frame">
      <h3>Nhà Bếp</h3>
      <a href="Search_KitchenEquipment" class="buy-button">Tìm hiểu thêm</a>
    </div>
    <div class="product-item">
      <img src="${pageContext.request.contextPath}/static/image/pc-achomepage.jpg" alt="WindFree Wall-Mount">
      <h3>Phòng Ngủ</h3>
      <a href="Search_AirConditioner" class="buy-button">Tìm hiểu thêm</a>
    </div>
    <div class="product-item">
      <img src="https://images.samsung.com/is/image/samsung/assets/vn/home/2024/PC_1440x810HA.jpg?$1440_810_JPG$" alt="Tủ Lạnh Bespoke">
      <h3>Phòng Khách</h3>
      <a href="search-refrigerator" class="buy-button">Tìm hiểu thêm</a>
    </div>
  </div>
</section>


<section class="body">
  <h2>Tính năng nổi bật</h2>
</section>

<div class="container1">

  <div class="feature-item">

    <video controls class="item-video">
      <source src="https://images.samsung.com/is/content/samsung/assets/vn/air-conditioners/windfree/windfree-2024-whitedisplay/black/2024-windfree-n05-1-comfort-cooling-pc.mp4" type="video/mp4">
      Trình duyệt của bạn không hỗ trợ thẻ video.
    </video>
    <div class="feature-content">
      <h2>Làn gió mát dịu không gió lạnh khó chịu</h2>
      <div class="feature-text">Mát dịu không gió lạnh khó chịu khi bật chế độ Làm lạnh WindFree™¹. Cấu trúc luồng không khí tiên tiến này thổi đều và nhẹ nhàng qua 23,000 lỗ siêu nhỏ tạo ra luồng không khí tĩnh với tốc độ gió thấp giúp làm mát cả gian phòng mà không gây khó chịu lên da của bạn.
      </div>
    </div>
  </div>

  <div class="feature-item">
    <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/feature/164971773/vn-feature-dw6500amdw60cg550fsgsv-539760689?$FB_TYPE_A_JPG$" alt="Item2">
    <div class="feature-content">
      <h2 class="feature-title">Dụng cụ nhà bếp hiện đại</h2>
      <div class="feature-text">Tận hưởng trải nghiệm làm sạch dụng cụ nấu nướng, bát đĩa với hiệu quả sạch vượt trội, loại bỏ 99.999% * vi khuẩn gây hại, đảm bảo các vật dụng được vệ sinh tối đa và an toàn khi sử dụng, mà không tốn thời gian và công sức.
      </div>

    </div>
  </div>

  <div class="feature-item">
    <img src="https://images.samsung.com/is/image/samsung/assets/vn/may-loc-khong-khi/feature-04.jpg" alt="Item5">
    <div class="feature-content">
      <h2>Cảm biến Laser
        và màn hình hiển thị</h2>
      <div class="feature-text">Cảm biến Laser PM phát hiện chính xác các chất ô nhiễm và bụi siêu mịn nhỏ hơn 1.0㎛ có trong không khí. Màn hình hiển thị chính xác mức độ ô nhiễm không khí ở mức PM1.0/PM2.5/PM10. Máy còn trang bị đèn 4 màu báo hiệu chất lượng không khí.
      </div>
    </div>
  </div>
  <div class="feature-item">
    <img src="https://images.samsung.com/is/image/samsung/assets/vn/washers-and-dryers/bespoke-ai-sustainability/AI-Control-PC-570x304.jpg?$570_N_JPG$" alt="Item3">
    <div class="feature-content">
      <h2>Tự động đề xuất chế độ giặt</h2>
      <div class="feature-text">Nhờ trí tuệ nhân tạo, bảng điều khiển thông minh sẽ ghi nhớ thói quen sử dụng, từ đó tự động đề xuất chế độ mà không cần tùy chỉnh mỗi lần giặt. Màn hình hiển thị tiếng Việt dễ hiểu, hướng dẫn thông minh.
      </div>
    </div>
  </div>

  <div class="feature-item">
    <img src="https://images.samsung.com/is/image/samsung/assets/vn/vacuum-cleaners/570x304-2.jpg?$568_N_JPG$" alt="Item4">
    <div class="feature-content">
      <h2 class="feature-title">Tự động thiết lập bản đồ nhà 3D, làm sạch mọi ngóc ngách</h2>
      <div class="feature-text">Cảm biến LiDAR thông minh sử dụng laser quét 360° tính toán chính xác vị trí các vật cản và tự động thiết lập bản đồ 3D căn nhà của bạn, giúp tối ưu hóa đường đi làm sạch mọi ngóc ngách.
      </div>
    </div>
  </div>



  <div class="feature-item">
    <img src="https://images.samsung.com/is/image/samsung/assets/vn/refrigerators/new/ref_open_min.gif?$568_N_GIF$" alt="Item6">
    <div class="feature-content">
      <h2>Ngăn chuyển đổi nhiệt độ Cool Select+™</h2>
      <div class="feature-text">Ngăn Cool Select+™ dễ dàng chuyển đổi giữa 5 chế độ làm lạnh với nhiệt độ khác nhau từ -23°C đến 4°C, đáp ứng mọi yêu cầu về bảo quản thực phẩm tươi ngon.
      </div>
    </div>
  </div>
</div>


<section class="body">
  <h2>Sản phẩm nổi bật</h2>
</section>
<section class="body">
  <div class="content">

    <div class="navbar-light">
      <a onclick="showImage('image1')" data-text="Bespoke Đẹp Đi Đừng Đợi!">Tủ Lạnh BESPOKE</a>
      <a onclick="showImage('image2')" data-text="Dẫn Đầu Kỷ Nguyên Bếp Thông Minh">Thiết Bị Bếp</a>
      <a onclick="showImage('image3')" data-text="Sạch Thông Minh, Giặt Hoàn Hảo">Máy Giặt BESPOKE AI</a>
      <a onclick="showImage('image4')" data-text="Lạnh Tức Thì, Không Gió Buốt">Điều Hòa Không Khí</a>
    </div>


    <div class="image-container">
      <img id="image1" src="${pageContext.request.contextPath}/static/image/pc-1440x810-t4ref.jpg" alt="Tủ Lạnh BESPOKE" class="active">
      <img id="image2" src=" ${pageContext.request.contextPath}/static/image/pc-1440x810bic.jpg" alt="Thiết Bị Bếp">
      <img id="image3" src="${pageContext.request.contextPath}/static/image/pc-1440x810wm%20(1).jpg" alt="Máy Giặt BESPOKE AI">
      <img id="image4" src="${pageContext.request.contextPath}/static/image/pc-achomepage.jpg" alt="Điều Hòa Không Khí">

      <div class="text-overlay">
        <h1 id="product-description">Bespoke Đẹp Đi Đừng Đợi!</h1>
        <a href="search-refrigerator" class="link">Tìm hiểu thêm</a>
        <!--                <a href="../../component/Checkout/Checkout.html" class="link">Mua ngay</a>-->
      </div>
    </div>
  </div>
</section>



<footer>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/footer.css">
  <div class="footer-container">
    <div class="footer-column">
      <h3>Sản Phẩm & Dịch Vụ</h3>
      <ul>
        <li>Điện Thoại Thông Minh</li>
        <li>Máy Tính Bảng</li>
        <li>Thiết Bị Âm Thanh</li>
        <li>Thiết Bị Đeo</li>
        <li>Smart Switch</li>
        <li>Phụ Kiện</li>
        <li>TVs</li>
        <li>Lifestyle TVs</li>
        <li>Thiết Bị Nghe Nhìn</li>
        <li>Tủ Lạnh</li>
        <li>Máy Giặt & Máy Sấy</li>
        <li>Giải Pháp Không Khí</li>
        <li>Gia Dụng Nhà Bếp</li>
        <li>Màn Hình</li>
      </ul>
    </div>
    <div class="footer-column">
      <h3>Mua Trực Tuyến</h3>
      <ul>
        <li>Ưu Đãi Độc Quyền</li>
        <li>Cửa hàng trực tuyến doanh nghiệp</li>
        <li>Samsung 68</li>
        <li>Cửa Hàng Trải Nghiệm Samsung</li>
        <li>Câu hỏi thường gặp</li>
        <li>Tìm Cửa Hàng</li>
        <li>Điểm tư vấn và nhận hàng trực tiếp</li>
        <li>Samsung Care+</li>
        <li>Samsung Rewards</li>
        <li>Khám Phá</li>
        <li>Điều Khoản & Điều Kiện</li>
      </ul>
    </div>
    <div class="footer-column">
      <h3>Chương trình đặc quyền</h3>
      <ul>
        <li>Ưu đãi sinh viên</li>
        <li>Ưu đãi Nhân viên đối tác (EPP)</li>
        <li>Ưu đãi chính phủ</li>
        <li>VIP Mall</li>
      </ul>
    </div>
    <div class="footer-column">
      <h3>Bạn Cần Hỗ Trợ?</h3>
      <ul>
        <li>Đặt hẹn lịch sửa chữa</li>
        <li>Tư Vấn Trực Tuyến</li>
        <li>Thư điện tử</li>
        <li>Điện Thoại</li>
        <li>Trung Tâm Bảo Hành</li>
        <li>Ngôn ngữ ký hiệu</li>
        <li>Gửi ý kiến phản hồi</li>
        <li>Gửi thư cho Ban Giám đốc</li>
      </ul>
    </div>
    <div class="footer-column">
      <h3>Sự bền vững</h3>
      <ul>
        <li>Môi trường</li>
        <li>Bảo mật & Quyền riêng tư</li>
        <li>Trợ năng</li>
        <li>Đa dạng - Công bằng - Hòa nhập</li>
        <li>Công dân Doanh nghiệp</li>
        <li>Tính bền vững của Doanh nghiệp</li>
      </ul>
      <h3>Giới thiệu về chúng tôi</h3>
      <ul>
        <li>Thông tin về Công ty</li>
        <li>Lĩnh vực kinh doanh</li>
        <li>Nhận diện thương hiệu</li>
        <li>Nghề nghiệp</li>
        <li>Quan hệ với nhà đầu tư</li>
        <li>Newsroom</li>
        <li>Đạo đức</li>
        <li>Samsung Design</li>
      </ul>
    </div>
  </div>
  <div class="footer-bottom">
    <p>Bản quyền ©1995-2024 Samsung bảo lưu mọi quyền.</p>
    <p>Công ty TNHH Điện Tử Samsung Vina</p>
    <p>Giấy CNĐK: 4110430003290, do UBND TP HCM cấp ngày 28/8/2007</p>
    <p>Địa chỉ: Số 2, đường Hải Triều, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh</p>
    <p>Điện thoại: +84-2839157310</p>
  </div>
</footer>

<script src="${pageContext.request.contextPath}/static/style-component/style-home/homeBody.js"></script>
</body>

