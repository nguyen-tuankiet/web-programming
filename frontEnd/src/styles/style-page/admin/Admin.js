const routes = {
    dashboard: '../component/admin_components/Dashboard.html',
    my_profile: '../component/admin_components/myProfile.html',
    product_details: '../component/product_detail/Product-detail.html',
    list_products: '../component/admin_components/listProduct.html',
    add_product: '../component/admin_components/addProduct.html',
    orders: '../component/admin_components/orders.html',
    order_detail: '../component/admin_components/orderDetail.html',
    customers: '../component/admin_components/customers.html',
    customers_detail: '../component/admin_components/customerDetail.html',
    setting: '../component/admin_components/account_settings.html',
};

function loadPage(pageKey) {
    const pagePath = routes[pageKey];
    if (pagePath) {
        document.querySelector("#content iframe").src = pagePath;
    } else {
        console.error("Page not found:", pageKey);
    }
}


document.querySelectorAll('.menu_item').forEach(item => {
    item.addEventListener('click', (event) => {
        // Đóng tất cả các submenu khác, nhưng giữ menu chính đang chọn
        document.querySelectorAll('.menu_item').forEach(otherItem => {
            const otherSubmenu = otherItem.querySelector('.submenu');
            if (otherSubmenu && otherSubmenu !== item.querySelector('.submenu')) {
                otherSubmenu.style.display = 'none';
                otherItem.classList.remove('active'); // Bỏ highlight menu khác
            }
        });

        // Xử lý submenu của mục đang được click
        const submenu = item.querySelector('.submenu');
        if (submenu) {
            const isOpen = submenu.style.display === 'flex';
            submenu.style.display = isOpen ? 'none' : 'flex'; // Chuyển trạng thái hiển thị submenu
            item.classList.toggle('active', !isOpen); // Highlight menu chính
        } else {
            // Nếu không có submenu, chỉ tô màu menu chính
            document.querySelectorAll('.menu_item').forEach(otherItem => {
                otherItem.classList.remove('active'); // Bỏ highlight tất cả các mục
            });
            item.classList.add('active'); // Highlight menu hiện tại
        }
    });
});

// Xử lý sự kiện click vào submenu
document.querySelectorAll('.submenu a').forEach(submenuItem => {
    submenuItem.addEventListener('click', (event) => {
        // Ngăn sự kiện click đóng menu chính
        event.stopPropagation();

        // Xóa highlight của tất cả các submenu
        document.querySelectorAll('.submenu a').forEach(link => {
            link.classList.remove('active');
        });

        // Highlight submenu được chọn
        submenuItem.classList.add('active');
    });
});
