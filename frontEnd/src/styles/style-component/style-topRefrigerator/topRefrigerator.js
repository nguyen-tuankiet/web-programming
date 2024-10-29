let slideIndices = [0, 0]; // Chỉ số slide cho mỗi slideshow
let slideIntervals = [];

// Hiển thị slide đầu tiên khi trang tải cho từng slideshow
document.addEventListener("DOMContentLoaded", function () {
    startAutoSlide(0); // Khởi động auto slide cho Slideshow 1
    startAutoSlide(1); // Khởi động auto slide cho Slideshow 2
});

// Hàm hiển thị slide cho từng slideshow
function showSlides(slideIndex, slideshowIndex) {
    let slideshow = document.getElementsByClassName("slideshow-container")[slideshowIndex];
    let slides = slideshow.getElementsByClassName("slide");
    let dots = document.getElementById(`indicator${slideshowIndex + 1}`).getElementsByClassName("progress");

    // Kiểm tra và lặp lại nếu vượt qua số lượng slide
    if (slideIndex >= slides.length) { slideIndices[slideshowIndex] = 0; }
    if (slideIndex < 0) { slideIndices[slideshowIndex] = slides.length - 1; }

    // Ẩn tất cả slide và đặt lại hoạt ảnh của các chấm trong slideshow cụ thể
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].style.animation = "none"; // Dừng hoạt ảnh
    }

    // Hiển thị slide hiện tại và khởi động lại hoạt ảnh cho chấm tương ứng
    slides[slideIndices[slideshowIndex]].style.display = "block";
    dots[slideIndices[slideshowIndex]].style.animation = "loading 3s linear infinite"; // Khởi động lại hoạt ảnh
}

// Hàm tự động chuyển slide và chấm mỗi 3 giây cho từng slideshow
function startAutoSlide(slideshowIndex) {
    slideIntervals[slideshowIndex] = setInterval(function() {
        slideIndices[slideshowIndex]++;
        showSlides(slideIndices[slideshowIndex], slideshowIndex);
    }, 3000);
}

// Hàm chuyển slide khi nhấn nút cho từng slideshow
function plusSlides(n, slideshowIndex) {
    clearInterval(slideIntervals[slideshowIndex]); // Dừng auto slide khi nhấn nút
    slideIndices[slideshowIndex] += n;
    showSlides(slideIndices[slideshowIndex], slideshowIndex);
    startAutoSlide(slideshowIndex); // Khởi động lại auto slide
}
