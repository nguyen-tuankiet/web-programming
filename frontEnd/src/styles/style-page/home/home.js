const menuItems = document.querySelectorAll('.navbar ul li');
const userLoginIcon = document.querySelector('.user-login');
const userPopup = document.querySelector('.user-popup');

menuItems.forEach(item => {
    let timeout;

    item.addEventListener('mouseenter', () => {
        clearTimeout(timeout);
        menuItems.forEach(i => i.classList.remove('active'));
        item.classList.add('active');
    });

    item.addEventListener('mouseleave', () => {
        timeout = setTimeout(() => {
            item.classList.remove('active');
        }, 200);
    });

    const submenu = item.querySelector('.submenu');
    if (submenu) {
        submenu.addEventListener('mouseenter', () => {
            clearTimeout(timeout);
        });

        submenu.addEventListener('mouseleave', () => {
            timeout = setTimeout(() => {
                item.classList.remove('active');
            }, 200);
        });
    }
});


$(document).ready(() => {
    const nav_items= $('.nav_item');
    const content = $('#body iframe')


    Array.from(nav_items).forEach(nav_item => {
        nav_item.addEventListener('click', (event) => {
            event.preventDefault();
            console.log(nav_item);
           content.attr('src', $(nav_item).attr('data-src'));
        })
    })


    window.addEventListener('message', (event) => {
        if (event.data.type === 'navigate'){
            content.attr('src', event.data.data);
        }
    })

})


userLoginIcon.addEventListener('mouseenter', () => {
    userPopup.style.display = 'block';
});

userLoginIcon.addEventListener('mouseleave', () => {
    setTimeout(() => {
        userPopup.style.display = 'none';
    }, 200);
});

userPopup.addEventListener('mouseenter', () => {
    userPopup.style.display = 'block';
});

userPopup.addEventListener('mouseleave', () => {
    userPopup.style.display = 'none';
});
document.getElementById("search-icon").addEventListener("click", function() {
    document.getElementById("search-overlay").style.display = "flex";
});

document.getElementById("close-search-overlay").addEventListener("click", function() {
    document.getElementById("search-overlay").style.display = "none";
});

let isLoggedIn = false;


function loadPage(page) {
    const content = document.getElementById('content');
    switch (page) {
        case 'user-profile':
            content.innerHTML = '<h1>Trang của tôi</h1><p>Thông tin người dùng...</p>';
            break;
        case 'user-orders':
            content.innerHTML = '<h1>Đơn hàng</h1><p>Danh sách đơn hàng...</p>';
            break;
        case 'cart':
            content.innerHTML = '<h1>Giỏ hàng</h1><p>Thông tin giỏ hàng...</p>';
            break;
        default:
            content.innerHTML = '<h1>Trang chủ</h1><p>Chào mừng bạn đến với trang web của chúng tôi.</p>';
    }
}

document.getElementById('my-page-link').addEventListener('click', function(event) {
    event.preventDefault();
    if (!isLoggedIn) {
        alert("Bạn cần đăng nhập trước!");
    } else {
        loadPage('user-profile');
        history.pushState({ page: 'user-profile' }, 'Trang của tôi', '/user-profile');
    }
});

document.getElementById('orders-link').addEventListener('click', function(event) {
    event.preventDefault();
    if (!isLoggedIn) {
        alert("Bạn cần đăng nhập trước!");
    } else {
        loadPage('user-orders');
        history.pushState({ page: 'user-orders' }, 'Đơn Hàng', '/user-orders');
    }
});

document.getElementById('cart-link').addEventListener('click', function(event) {
    event.preventDefault();
    loadPage('cart');
    history.pushState({ page: 'cart' }, 'Giỏ hàng', '/cart');
});
window.onload = function() {
    // Lấy giá trị trạng thái đăng nhập từ localStorage
    const savedLoginStatus = localStorage.getItem('isLoggedIn');

    // Nếu trạng thái đăng nhập là true, hiển thị "Đăng xuất", nếu không thì "Đăng nhập/Đăng ký"
    if (savedLoginStatus === 'true') {
        isLoggedIn = true;
        document.getElementById('login-link').textContent = "Đăng xuất";
    } else {
        isLoggedIn = false;
        document.getElementById('login-link').textContent = "Đăng nhập/Đăng ký";
    }
};

// Đăng nhập/Đăng xuất
document.getElementById('login-link').addEventListener('click', function(event) {
    event.preventDefault();

    if (!isLoggedIn) {
        // Khi chưa đăng nhập, chuyển hướng tới trang đăng nhập
        window.location.href = "../../../frontEnd/src/pages/auth.html";
    } else {
        // Khi đã đăng nhập, thực hiện đăng xuất
        isLoggedIn = false;
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('userEmail');
        alert("Bạn đã đăng xuất!");
        localStorage.clear();
        document.getElementById('login-link').textContent = "Đăng nhập/Đăng ký";
        window.location.reload(); // Tải lại trang để cập nhật trạng thái
    }
});
