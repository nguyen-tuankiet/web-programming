
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Tìm kiếm sản phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>

</head>
<body>
<div class="overlay">
    <div class="search-container">
        <h2 class="search-container-title">Chúng tôi có thể giúp bạn tìm kiếm?</h2>
        <div class="search-bar">
            <input type="text" id="search-input" placeholder="Nhập từ khoá ..." />
            <button class="search-icon" onclick="performSearch()">
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </div>

        <!-- Dropdown gợi ý -->
        <div class="suggestion-box" id="suggestion-box">
            <ul id="suggestion-list"></ul>
            <h3 class="suggestion-title">Gợi ý dành cho bạn</h3>
            <div class="product-suggestions">


                <div class="product">
                    <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rb30n4190bu-sv/gallery/vn-ref-rb4000-387696-rb30n4190bu-sv-thumb-536799673"
                         alt="Product 1">
                    <div class="product-content">
                        <p class="product-name">307 L Tủ Lạnh Ngăn Đông Dưới với Optimal Fresh+</p>
                        <span class="product-price" >13.490.000 VND</span>
                    </div>
                </div>


                <div class="product">
                    <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rt31cg5424b1sv/gallery/vn-top-mount-freezer-spacemax-rt31cg5424b1sv-thumb-536303518"
                         alt="Product 2">
                        <div class="product-content">
                            <p class="product-name" >307 L Tủ Lạnh Ngăn Đông Dưới với Optimal Fresh+</p>
                            <span class="product-price" >13.490.000 VND</span>
                        </div>
                    </div>
                </div>





                <div class="product">
                    <img src="https://images.samsung.com/is/image/samsung/p6pim/vn/rt42cg6584b1sv/gallery/vn-top-mount-freezer-smartthings-ai-energy-mode-455269-rt42cg6584b1sv-thumb-536331785"
                         alt="Product 3">
                    <div class="product-content">
                        <p class="product-name" >Tủ Lạnh Ngăn Đông Trên RT42CG6584</p>
                        <span class="product-price" >15.490.278 VND</span>
                    </div>
                </div>





            </div>
        </div>


    </div>
 <script src="${pageContext.request.contextPath}/static/style-component/style-home/search.js"></script>
</body>
