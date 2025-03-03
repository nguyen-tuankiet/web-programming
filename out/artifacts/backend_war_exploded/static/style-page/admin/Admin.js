
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




