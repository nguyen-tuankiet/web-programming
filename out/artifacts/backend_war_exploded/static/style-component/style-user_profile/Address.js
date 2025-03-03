document.addEventListener("DOMContentLoaded", function () {
    const addBtn = document.querySelector(".add_btn");
    const formContainer = document.getElementById("addAddressFormContainer");
    const cancelBtn = document.querySelector("button[type='reset']");

    // Hiển thị form thêm địa chỉ
    addBtn.addEventListener("click", function () {
        formContainer.style.display = "block";
    });

    // Ẩn form khi nhấn Hủy
    cancelBtn.addEventListener("click", function (event) {
        // Ngăn hành vi mặc định (nếu không cần reset form)
        event.preventDefault();

        // Đóng form thêm địa chỉ
        formContainer.style.display = "none";

        // Nếu cần reset form, gọi reset method
        const form = formContainer.querySelector("form");
        if (form) form.reset();
    });
});
