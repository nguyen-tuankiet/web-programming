document.addEventListener('DOMContentLoaded', () => {


    const myAccount = document.querySelector(".edit_profile");
    const subMenu = document.querySelector(".menu_sub_item");
    const subItem = document.querySelectorAll(".sub_items");
    const mainContent = document.getElementById('main_content');


    myAccount.addEventListener("click", () => {

        if (subMenu.classList.contains('open')) {
            subMenu.classList.remove('open');
        } else {
            subMenu.classList.add('open');
        }
    })

    subItem.forEach((item) => {

        item.addEventListener("click", (event) => {
            event.stopPropagation();
            subItem.forEach((item) => {
                item.classList.remove('selected');
            })
            item.classList.add('selected');

            console.log(item);

            const link = item.querySelector('.item_link[data-src]');
            if (link) {
                const dataSrc = link.getAttribute('data-src'); //    Lấy giá trị tu data-src
                event.preventDefault();
                window.parent.postMessage({ type: 'openUserProfile', src: dataSrc }, '*');
            }





        })

    })


})

