document.addEventListener("DOMContentLoaded", function () {
    const addBtn = document.querySelector(".add_btn");
    const formContainer = document.getElementById("addAddressFormContainer");
    const cancelBtn = document.querySelector("#cancelAddressForm");

    // Hiển thị form thêm địa chỉ
    addBtn.addEventListener("click", function () {
        formContainer.style.display = "block";
    });

    // Ẩn form khi nhấn Hủy
    cancelBtn.addEventListener("click", function () {
        formContainer.style.display = "none";
    });
});
