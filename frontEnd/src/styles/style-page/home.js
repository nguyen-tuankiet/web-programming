const menuItems = document.querySelectorAll('.navbar ul li');

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