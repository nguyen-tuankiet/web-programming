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
    // console.log(content)
    // console.log($(nav_items[0]).attr('data-src'));

    Array.from(nav_items).forEach(nav_item => {
        // console.log($(nav_item).attr('data-src'));
        nav_item.addEventListener('click', () => {
           content.attr('src', $(nav_item).attr('data-src'));
        })
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