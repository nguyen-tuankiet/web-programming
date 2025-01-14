// JavaScript cho trang User Address
document.addEventListener("DOMContentLoaded", function () {
    // Mở bảng (modal)
    const addBtn = document.querySelector(".add_btn");
    const modal = document.getElementById("addAddressModal");
    const closeBtn = document.querySelector(".close_modal");
    const saveBtn = document.querySelector("#saveAddress");

    addBtn.addEventListener("click", function () {
        modal.style.display = "block";
    });

    // Đóng bảng
    closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
    });

    // Lưu địa chỉ
    saveBtn.addEventListener("click", function () {
        const province = document.getElementById("province").value;
        const district = document.getElementById("district").value;
        const commune = document.getElementById("commune").value;
        const name = document.getElementById("name").value;
        const phone = document.getElementById("phone").value;
        // const type = document.querySelector('input[name="type"]:checked')?.value;

        // if (!province || !district || !commune || !name || !phone || !type) {
        if (!province || !district || !commune || !name || !phone ) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        // Gửi dữ liệu đến backend hoặc xử lý tại đây
        // console.log({ province, district, commune, name, phone, type });
        console.log({ province, district, commune, name, phone });

        // Đóng bảng
        modal.style.display = "none";
    });
});