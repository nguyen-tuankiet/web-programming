let slideIndex = 0;
let slideInterval;
let isPlaying = true;

document.addEventListener("DOMContentLoaded", function () {
    showSlide(slideIndex);
    startAutoSlide();
});

function showSlide(index) {
    const slides = document.getElementsByClassName("banner-slide");
    const progressBars = document.querySelectorAll(".progress-loader .progress");

    if (index >= slides.length) slideIndex = 0;
    if (index < 0) slideIndex = slides.length - 1;


    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        progressBars[i].style.animation = "none";
    }

    slides[slideIndex].style.display = "flex";


    if (slideIndex === 0) {
        progressBars[slideIndex].style.animation = "loading 3s linear infinite";
    } else {
        progressBars[slideIndex].style.animation = "loading 3s linear infinite";
    }
}

function startAutoSlide() {
    slideInterval = setInterval(function () {
        slideIndex++;
        showSlide(slideIndex);
        document.getElementById("pausePlayBtn").textContent = "⏸️";
    }, 3000);
    isPlaying = true;
}

function stopAutoSlide() {
    clearInterval(slideInterval);
    document.getElementById("pausePlayBtn").textContent = "▶️";
    isPlaying = false;
}

document.getElementById("pausePlayBtn").addEventListener("click", function () {
    if (isPlaying) {
        stopAutoSlide();
    } else {
        startAutoSlide();
    }
});

function plusSlides(n) {
    stopAutoSlide();
    slideIndex += n;
    showSlide(slideIndex);
    startAutoSlide();
}

function changeSlide(n) {
    clearInterval(slideInterval);
    slideIndex += n;
    showSlide(slideIndex);
    startAutoSlide();
}

document.querySelector(".prev-btn").addEventListener("click", function () {
    changeSlide(-1);
});
document.querySelector(".next-btn").addEventListener("click", function () {
    changeSlide(1);
});
