
const routes = {
    dashboard: '/backend_war/admin/Dashboard.jsp',
    my_profile: '/backend_war/admin/MyProfile.jsp',
    product_details: '/backend_war/product_detail/Product-detail-admin.jsp',
    list_products: '/backend_war/admin/listProduct.jsp',
    add_product: `/backend_war/admin/addProduct.jsp`,
    orders: '/backend_war/admin/orders.jsp',
    order_detail: '/backend_war/admin/orderDetail.jsp',
    customers: '/backend_war/admin/customers.jsp',
    customers_detail: '/backend_war/admin/customerDetail.jsp',
    setting: '/backend_war/admin/account_settings.jsp',
};

const userPopup = document.querySelector(".user-popup");
const avatar = document.querySelector(".avatar");


function loadPage(pageKey) {
    const pagePath = routes[pageKey];
    console.log("Đang tải URL:", pagePath);

    const iframe = document.querySelector("#content iframe");
    if (!iframe) {
        console.error("Iframe không tồn tại trong DOM.");
        return;
    }

    if (pagePath) {
        iframe.src = pagePath;
    } else {
        console.error("Không tìm thấy trang:", pageKey);
    }
}




document.querySelectorAll('.menu_item').forEach(item => {
    item.addEventListener('click', (event) => {
        // Ngăn chặn các sự kiện không mong muốn
        event.stopPropagation();

        // Xử lý các menu khác: tắt trạng thái active và đóng submenu
        document.querySelectorAll('.menu_item').forEach(otherItem => {
            if (otherItem !== item) {
                otherItem.classList.remove('active');
                const otherSubmenu = otherItem.querySelector('.submenu');
                if (otherSubmenu) {
                    otherSubmenu.style.display = 'none';
                }
            }
        });

        // Toggle trạng thái của menu hiện tại
        const submenu = item.querySelector('.submenu');
        if (submenu) {

            const submenuItems = submenu.querySelectorAll('.submenu_item');
            // Duyệt qua từng phần tử và kiểm tra class `active`
            submenuItems.forEach((submenuItem) => {
                console.log(submenuItem);
                if (submenuItem.classList.contains('active')) {
                    submenuItem.classList.remove('active');
                }
            });

            const isOpen = submenu.style.display === 'flex';
            submenu.style.display = isOpen ? 'none' : 'flex'; // Mở/đóng submenu
            item.classList.toggle('active', !isOpen);
        } else {
            // Nếu không có submenu, chỉ cần tô màu active
            document.querySelectorAll('.menu_item').forEach(otherItem => {
                otherItem.classList.remove('active');
            });
            item.classList.add('active');
        }
    });
});










// Xử lý sự kiện click vào submenu
document.querySelectorAll('.submenu li').forEach(submenuItem => {
    submenuItem.addEventListener('click', (event) => {
        // Ngăn sự kiện click làm đóng menu chính
        event.stopPropagation();

        // Loại bỏ active của tất cả các submenu
        document.querySelectorAll('.submenu li').forEach(link => {
            link.classList.remove('active');
        });

        // Gán trạng thái active cho mục được chọn
        submenuItem.classList.add('active');
    });
});


avatar.addEventListener("mouseenter", () => {
    userPopup.style.display = "block";
});

avatar.addEventListener("mouseleave", () => {
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