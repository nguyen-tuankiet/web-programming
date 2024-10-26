let slideIndex = 0;
let slideInterval;

// Hiển thị slide đầu tiên khi trang tải
document.addEventListener("DOMContentLoaded", function () {
    showSlides(slideIndex);
    startAutoSlide();
});

// Hàm hiển thị slide
function showSlides(n) {
    let slides = document.getElementsByClassName("slide");
    let dots = document.getElementsByClassName("progress");

    // Kiểm tra và lặp lại nếu vượt qua số lượng slide
    if (n >= slides.length) { slideIndex = 0; }
    if (n < 0) { slideIndex = slides.length - 1; }

    // Ẩn tất cả slide và đặt lại hoạt ảnh của các chấm
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].style.animation = "none"; // Dừng hoạt ảnh
    }

    // Hiển thị slide hiện tại và khởi động lại hoạt ảnh cho chấm tương ứng
    slides[slideIndex].style.display = "block";
    dots[slideIndex].style.animation = "loading 3s linear infinite"; // Khởi động lại hoạt ảnh
}

// Hàm tự động chuyển slide và chấm mỗi 3 giây
function startAutoSlide() {
    slideInterval = setInterval(function() {
        slideIndex++;
        showSlides(slideIndex);
    }, 3000);
}

// Hàm chuyển slide khi nhấn nút
function plusSlides(n) {
    clearInterval(slideInterval); // Dừng auto slide khi nhấn nút
    slideIndex += n;
    showSlides(slideIndex);
    startAutoSlide(); // Khởi động lại auto slide
}
