function loadPage(page) {
    document.querySelector("#content iframe").src = "../component/admin_components/" + page;
}



document.querySelectorAll('.menu_item').forEach(item => {
    item.addEventListener('click', () => {
        const submenu = item.querySelector('.submenu');
        const arrow = item.querySelector('.toggle-arrow');


        if (submenu) {
            submenu.style.display = submenu.style.display === 'none' || submenu.style.display === '' ? 'flex' : 'none';
            item.classList.toggle('active');
        }
    });
});
