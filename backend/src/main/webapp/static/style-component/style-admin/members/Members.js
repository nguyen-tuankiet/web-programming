document.addEventListener("DOMContentLoaded", function () {
    const gearIcons = document.querySelectorAll(".more_action i.fa-gear");
    gearIcons.forEach(function(icon) {
        icon.addEventListener("click", function(event) {
            event.stopPropagation();
            toggleMoreActionMenu(this);
        });
    });

    // Đóng tất cả popup khi click ngoài
    document.addEventListener("click", function() {
        const menus = document.querySelectorAll(".more_action_menu");
        menus.forEach(function(menu) {
            menu.classList.add("hidden");
        });
    });
});

function toggleMoreActionMenu(icon) {
    // Tìm đối tượng menu trong cùng thẻ cha
    const container = icon.parentElement;
    const menu = container.querySelector(".more_action_menu");
    if (menu) {
        menu.classList.toggle("hidden");
    }
}

// Popup Invite Member
document.addEventListener("DOMContentLoaded", function () {
    const inviteBtn = document.getElementById("invite");
    const inviteModal = document.getElementById("inviteModal");
    const closeBtn = document.querySelector(".close_btn");
    const cancelInvite = document.getElementById("cancelInvite");
    const inviteForm = document.getElementById("inviteForm");

    inviteBtn.addEventListener("click", function () {
        inviteModal.classList.remove("hidden");
    });

    closeBtn.addEventListener("click", function () {
        inviteModal.classList.add("hidden");
    });

    cancelInvite.addEventListener("click", function (e) {
        e.preventDefault();
        inviteModal.classList.add("hidden");
    });

    inviteForm.addEventListener("submit", function (e) {
        e.preventDefault();
        alert("Invitation sent!");
        inviteModal.classList.add("hidden");
    });
});

