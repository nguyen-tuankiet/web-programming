document.addEventListener('DOMContentLoaded', () => {


    const items = Array.from(document.querySelectorAll('.item'));

    const myAccount = items[0];
    console.log(myAccount);
    const subMenu = document.querySelector(".menu_sub_item");
    const subItem = document.querySelectorAll(".sub_items");


    items.forEach(item => {
        item.addEventListener('click', (e) => {


            items.forEach((i) => {
                i.classList.remove('selected');
            })


            if (item === myAccount && !subMenu.classList.contains('open')) {
                console.log(item);
                subMenu.classList.add('open');
            }
            else {
                subMenu.classList.remove('open');
            }
            item.classList.add('selected');
        }
        )

        })



        subItem.forEach((item) => {
            Array.from(subItem)[0].classList.add('selected');

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
                    window.parent.postMessage({type: 'openUserProfile', src: dataSrc}, '*');
                }


            })

        })


    })

