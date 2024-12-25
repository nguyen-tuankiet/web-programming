<%--
  Created by IntelliJ IDEA.
  User: mr.hung
  Date: 12/24/24
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Search Overlay</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search.css">
</head>
<body>
<div class="overlay">
  <div class="search-container">
    <h2>Chúng tôi có thể giúp bạn tìm kiếm?</h2>
    <div class="search-bar" onclick="toggleDropdown()">
      <input type="text" id="search-input" placeholder="Tủ lạnh samsung" onfocus="toggleDropdown()" />
      <button class="search-icon">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
        </svg>
      </button>
    </div>

    <div class="popular-keywords" id="popular-keywords">
      <p>Từ khóa Phổ biến</p>
      <ul>
        <li>Tủ lạnh</li>
        <li>Máy giặt</li>
        <li>Máy điều hòa</li>
        <li>Máy lọc không khí</li>
      </ul>
    </div>


    <div class="suggestions">
      <h3>Gợi ý dành cho bạn</h3>
      <div class="product-suggestions">
        <div class="product">
          <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rb30n4190bu-sv/gallery/vn-ref-rb4000-387696-rb30n4190bu-sv-thumb-536799673" alt="Product 1">
          <p>307 L Tủ Lạnh Ngăn Đông Dưới với Optimal Fresh+</p>
          <span>13.490.000 VND</span>
        </div>
        <div class="product">
          <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rt31cg5424b1sv/gallery/vn-top-mount-freezer-spacemax-rt31cg5424b1sv-thumb-536303518" alt="Product 2">
          <p>Tủ Lạnh Ngăn Đông Trên RT31CG5424B </p>
          <span>10.489.731 VND</span>
        </div>
        <div class="product">
          <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rt42cg6584b1sv/gallery/vn-top-mount-freezer-smartthings-ai-energy-mode-455269-rt42cg6584b1sv-thumb-536331785" alt="Product 3">
          <p>Tủ Lạnh Ngăn Đông Trên RT42CG6584</p>
          <span>15.490.278 VND</span>
        </div>
        <div class="product">
          <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rt47cb66868asv/gallery/vn-top-mount-freezer-bespoke-design-453404-rt47cb66868asv-thumb-536331958" alt="Product 4">
          <p>Tủ Lạnh Bespoke Ngăn Đông Trên với Dòng Mền</p>
          <span>18.489.518 VND</span>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/static/style-component/style-home/search.js"></script>
</body>

