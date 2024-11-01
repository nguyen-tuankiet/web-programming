let slideIndices = [0, 0]; // Indices for each slideshow
let slideIntervals = [];

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
    slideIndices[slideshowIndex] = (slideIndex >= slides.length) ? 0 : (slideIndex < 0 ? slides.length - 1 : slideIndex);

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

// Change slide when arrow is clicked
function plusSlides(n, slideshowIndex) {
    clearInterval(slideIntervals[slideshowIndex]); // Pause auto slide when navigating manually
    showSlides(slideIndices[slideshowIndex] += n, slideshowIndex);
    startAutoSlide(slideshowIndex); // Restart auto slide
}
