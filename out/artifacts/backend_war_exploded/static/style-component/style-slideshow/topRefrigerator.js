let currentSlideIndex = 0;
let slides = document.getElementsByClassName("slide");
let dots = document.getElementsByClassName("dot");
let isPlaying = true;
let playInterval;

// Hàm hiển thị slide hiện tại
function showSlide(index) {
    const listWrap = document.querySelector(".list-wrap");
    const slideWidth = slides[0].clientWidth;

    // Cập nhật vị trí của list-wrap để hiển thị slide tương ứng
    listWrap.style.transform = `translateX(-${index * slideWidth}px)`;

    // Cập nhật các chấm điều hướng
    updateDots();
}

// Hàm thay đổi slide
function changeSlide(step) {
    currentSlideIndex += step;
    if (currentSlideIndex >= slides.length) {
        currentSlideIndex = 0;
    }
    if (currentSlideIndex < 0) {
        currentSlideIndex = slides.length - 1;
    }
    showSlide(currentSlideIndex);
}

// Đặt slide khi nhấn vào chấm điều hướng
function setSlide(index) {
    currentSlideIndex = index;
    showSlide(currentSlideIndex);
    // Nếu slideshow đang chạy, dừng lại và sau đó tiếp tục từ slide mới
    if (isPlaying) {
        clearInterval(playInterval);
        startSlideShow();
    }
}

// Cập nhật các chấm điều hướng
function updateDots() {
    for (let i = 0; i < dots.length; i++) {
        dots[i].classList.remove("active");
    }
    dots[currentSlideIndex].classList.add("active");
}

// Tự động chuyển slide
function startSlideShow() {
    playInterval = setInterval(() => {
        changeSlide(1);
    }, 3000);
}

// Hàm toggle play/pause
function togglePlayPause() {
    const icon = document.querySelector(".pause-button div");
    if (isPlaying) {
        clearInterval(playInterval);
        icon.classList.remove("pause-icon");
        icon.classList.add("play-icon");
    } else {
        startSlideShow();
        icon.classList.remove("play-icon");
        icon.classList.add("pause-icon");
    }
    isPlaying = !isPlaying;
}

// Bắt đầu slideshow khi tải trang
showSlide(currentSlideIndex);
startSlideShow();
