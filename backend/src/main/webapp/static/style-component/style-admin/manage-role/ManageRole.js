document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("roleModal");
    const addBtn = document.getElementById("addRoleBtn");
    const closeBtn = document.getElementById("closeRoleModal");
    const cancelBtn = document.getElementById("cancelAddRole");
    const form = document.getElementById("roleForm");

    addBtn.addEventListener("click", () => {
        modal.classList.remove("hidden");
    });

    closeBtn.addEventListener("click", () => {
        modal.classList.add("hidden");
    });

    cancelBtn.addEventListener("click", () => {
        modal.classList.add("hidden");
    });

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        alert("Role added!");
        modal.classList.add("hidden");
    });
});
