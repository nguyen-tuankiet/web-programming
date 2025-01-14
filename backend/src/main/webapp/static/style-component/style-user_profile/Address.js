document.addEventListener("DOMContentLoaded", function () {
    const addBtn = document.querySelector(".add_btn");
    const formContainer = document.getElementById("addAddressFormContainer");
    const saveBtn = document.querySelector("#saveAddress");
    const cancelBtn = document.querySelector("#cancelAddressForm");

    // Hiển thị form thêm địa chỉ
    addBtn.addEventListener("click", function () {
        formContainer.style.display = "block";
    });

    // Ẩn form khi nhấn Hủy
    cancelBtn.addEventListener("click", function () {
        formContainer.style.display = "none";
    });

    // Lưu địa chỉ (Thêm logic tại đây)
    saveBtn.addEventListener("click", function () {
        const province = document.getElementById("province").value.trim();
        const district = document.getElementById("district").value.trim();
        const commune = document.getElementById("commune").value.trim();
        const name = document.getElementById("name").value.trim();
        const phone = document.getElementById("phone").value.trim();

        // Kiểm tra thông tin
        if (!province || !district || !commune || !name || !phone) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        console.log({ province, district, commune, name, phone });

        // Reset form
        document.getElementById("addAddressForm").reset();

        // Ẩn form
        formContainer.style.display = "none";
    });
});
