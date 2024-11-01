let slideIndices = [0, 0]; // Indices for each slideshow
let slideIntervals = [];
let isPaused = [false, false]; // Trạng thái `pause` cho từng slideshow

// Start auto slide for each slideshow when the page loads
document.addEventListener("DOMContentLoaded", function () {
    startAutoSlide(0);
    startAutoSlide(1);
});

// Show the current slide for each slideshow
function showSlides(slideIndex, slideshowIndex) {
    let slideshow = document.getElementsByClassName("slideshow-container")[slideshowIndex];
    let slides = slideshow.getElementsByClassName("slide");
    let dots = document.getElementById(`indicator${slideshowIndex + 1}`).getElementsByClassName("progress");

    // Wrap around the slides
    slideIndices[slideshowIndex] = (slideIndex >= slides.alength) ? 0 : (slideIndex < 0 ? slides.length - 1 : slideIndex);

    // Hide all slides and reset dot animations
    Array.from(slides).forEach(slide => slide.style.display = "none");
    Array.from(dots).forEach(dot => dot.style.animation = "none");

    // Show current slide and start animation for the corresponding dot
    slides[slideIndices[slideshowIndex]].style.display = "block";
    dots[slideIndices[slideshowIndex]].style.animation = "loading 3s linear infinite";
}

// Auto slide function, changes slide every 3 seconds
function startAutoSlide(slideshowIndex) {
    slideIntervals[slideshowIndex] = setInterval(() => {
        showSlides(++slideIndices[slideshowIndex], slideshowIndex);
    }, 3000);
}

function toggleSlideshow(slideshowIndex) {
    const button = document.querySelector(`#indicator${slideshowIndex + 1} .pause-button`);
    const dots = document.getElementById(`indicator${slideshowIndex + 1}`).getElementsByClassName("progress");

    if (isPaused[slideshowIndex]) {
        startAutoSlide(slideshowIndex); // Khởi động lại slideshow nếu đang dừng
        button.textContent = '▶'; // Đổi biểu tượng về "pause" khi đang chạy
        Array.from(dots).forEach(dot => dot.style.animationPlayState = 'running'); // Tiếp tục hiệu ứng indicator
    } else {
        clearInterval(slideIntervals[slideshowIndex]); // Dừng slideshow
        button.textContent = '▶'; // Đổi biểu tượng về "play" khi dừng
        Array.from(dots).forEach(dot => dot.style.animationPlayState = 'paused'); // Dừng hiệu ứng indicator
    }
    isPaused[slideshowIndex] = !isPaused[slideshowIndex]; // Đổi trạng thái pause cho slideshow đó
}

function plusSlides(n, slideshowIndex) {
    clearInterval(slideIntervals[slideshowIndex]); // Dừng tự động khi nhấn prev/next
    // Cập nhật vị trí slide
    showSlides(slideIndices[slideshowIndex] += n, slideshowIndex);
    // Kiểm tra trạng thái `isPaused`
    if (!isPaused[slideshowIndex]) {
        startAutoSlide(slideshowIndex); // Khởi động lại slideshow nếu không ở trạng thái `pause`
    }
}
