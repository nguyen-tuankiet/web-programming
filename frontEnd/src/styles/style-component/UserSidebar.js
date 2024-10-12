document.addEventListener('DOMContentLoaded', () => {
    const myAccount = document.querySelector(".edit_profile ");
    const subMenu = document.querySelector(".menu_sub_item");
    const subItem  = document.querySelectorAll(".sub_items");



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
            subItem.forEach((item) => {item.classList.remove('selected');})
            item.classList.add('selected');

        })

    })


})

