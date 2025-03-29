
const addBtn = document.querySelector(".add_btn");
const formContainer = document.getElementById("addAddressFormContainer");
 const overlay = document.querySelector(".overlay");


document.addEventListener("DOMContentLoaded", function () {

    // Hiển thị form thêm địa chỉ và overlay
    addBtn.addEventListener("click", function () {
        formContainer.style.display = "block";
        overlay.style.display = "block"; // Hiển thị lớp phủ mờ
    });


    // Ẩn popup nếu bấm ra ngoài overlay
    overlay.addEventListener("click", function () {
        console.log("overlay");
        formContainer.style.display = "none";
        overlay.style.display = "none";
    });
});



document.addEventListener("DOMContentLoaded", function () {
    const formContainer = document.getElementById("addAddressFormContainer");
    const closeIcon = document.querySelector(".close-icon");

    closeIcon.addEventListener("click", function () {
        formContainer.style.display = "none";
        overlay.style.display = "none";
    });
});
$(document).ready(function () {
    $("#province, #district, #commune").select2({ width: '100%' });

    // Load danh sách tỉnh
    fetch("https://provinces.open-api.vn/api/?depth=1")
        .then(response => response.json())
        .then(data => {
            $("#province").append(new Option("Chọn tỉnh", ""));
            data.forEach(province => {
                $("#province").append(new Option(province.name, province.code));
            });
        });

    // Khi chọn tỉnh -> Load huyện
    $("#province").on("change", function () {
        let provinceID = $(this).val();
        let provinceName = $("#province option:selected").text();
        $("#province").attr("data-name", provinceName);

        $("#district").empty().append(new Option("Chọn huyện", ""));
        $("#commune").empty().append(new Option("Chọn xã", ""));

        if (provinceID) {
            fetch(`https://provinces.open-api.vn/api/p/${provinceID}?depth=2`)
                .then(response => response.json())
                .then(data => {
                    data.districts.forEach(district => {
                        $("#district").append(new Option(district.name, district.code));
                    });
                });
        }
    });

    // Khi chọn huyện -> Load xã
    $("#district").on("change", function () {
        let districtID = $(this).val();
        let districtName = $("#district option:selected").text();
        $("#district").attr("data-name", districtName);

        $("#commune").empty().append(new Option("Chọn xã", ""));

        if (districtID) {
            fetch(`https://provinces.open-api.vn/api/d/${districtID}?depth=2`)
                .then(response => response.json())
                .then(data => {
                    data.wards.forEach(commune => {
                        $("#commune").append(new Option(commune.name, commune.code));
                    });
                });
        }
    });

    // Khi chọn xã -> Lưu tên xã
    $("#commune").on("change", function () {
        let communeName = $("#commune option:selected").text();
        $("#commune").attr("data-name", communeName);
    });


// Kiểm tra số điện thoại
$("#phone").on("input", function () {
    this.value = this.value.replace(/\D/g, "").slice(0, 10);
});

});



document.querySelector("form").addEventListener("submit", function(event) {
    event.preventDefault(); // Chặn chuyển trang
    let userId = sessionStorage.getItem("userId");
    let formData = {
        userId: userId,
        province: document.getElementById("province").getAttribute("data-name"),
        district: document.getElementById("district").getAttribute("data-name"),
        commune: document.getElementById("commune").getAttribute("data-name"),
        detail: document.getElementById("detail").value,
        name: document.getElementById("name").value,
        phone: document.getElementById("phone").value,
        type: document.querySelector('input[name="addressType"]:checked').value,
        isDefault: document.getElementById("default").checked
    };

    fetch("/add-address", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                alert("Địa chỉ đã được thêm thành công!");
                location.reload();
            } else {
                alert("Lỗi: " + data.message);
            }
        })
        .catch(error => console.error("Lỗi:", error));
});
