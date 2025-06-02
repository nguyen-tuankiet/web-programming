let slideIndex = 0;
let slideInterval;
let isPlaying = true;

document.addEventListener("DOMContentLoaded", function () {
    showSlide(slideIndex);
    startAutoSlide();

    document.getElementById("pausePlayBtn").addEventListener("click", function () {
        if (isPlaying) {
            stopAutoSlide();
        } else {
            startAutoSlide();
        }
    });

    document.querySelector(".prev-btn").addEventListener("click", function () {
        changeSlide(-1);
    });

    document.querySelector(".next-btn").addEventListener("click", function () {
        changeSlide(1);
    });

    const progressLoaders = document.querySelectorAll('.progress-loader');
    progressLoaders.forEach((loader, index) => {
        const title = loader.getAttribute('data-title');
        const tooltip = document.createElement('div');
        tooltip.className = 'tooltip';
        tooltip.textContent = title || `Slide ${index + 1}`;
        loader.appendChild(tooltip);

        loader.addEventListener('mouseover', function (event) {
            tooltip.style.display = 'block';
            tooltip.style.left = `${event.offsetX}px`;
            tooltip.style.top = `-30px`;
        });

        loader.addEventListener('mouseout', function () {
            tooltip.style.display = 'none';
        });

        loader.addEventListener('click', function () {
            stopAutoSlide();
            slideIndex = index;
            showSlide(slideIndex);
            startAutoSlide();
        });
    });
});

function showSlide(index) {
    const slides = document.querySelectorAll(".banner-slide");
    const progressBars = document.querySelectorAll(".progress-loader .progress");

    if (slides.length === 0) return;

    if (index >= slides.length) slideIndex = 0;
    if (index < 0) slideIndex = slides.length - 1;

    slides.forEach((slide, i) => {
        slide.classList.remove("active");
        slide.style.display = "none";

        if (progressBars[i]) {
            progressBars[i].style.animation = "none";
            progressBars[i].style.width = "0";
        }
    });

    slides[slideIndex].classList.add("active");
    slides[slideIndex].style.display = "flex";

    if (progressBars[slideIndex]) {
        progressBars[slideIndex].style.animation = "loading 3s linear forwards";
    }
}

function startAutoSlide() {
    slideInterval = setInterval(() => {
        slideIndex++;
        showSlide(slideIndex);
    }, 3000);
    isPlaying = true;
    updateProgressBars();
    document.getElementById("pausePlayBtn").innerHTML = '<i class="fas fa-pause"></i>';
}

function stopAutoSlide() {
    clearInterval(slideInterval);
    isPlaying = false;
    document.getElementById("pausePlayBtn").innerHTML = '<i class="fas fa-play"></i>';

    const progressBars = document.querySelectorAll(".progress-loader .progress");
    progressBars.forEach(bar => {
        bar.style.animation = "none";
    });
}

function updateProgressBars() {
    const progressBars = document.querySelectorAll(".progress-loader .progress");
    if (progressBars[slideIndex]) {
        progressBars[slideIndex].style.animation = "loading 3s linear forwards";
    }
}

function changeSlide(n) {
    stopAutoSlide();
    slideIndex += n;
    showSlide(slideIndex);
    startAutoSlide();
}
