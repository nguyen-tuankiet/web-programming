// Danh sách ảnh cho carousel
const images = [
    "../../../resource/image/img-detail/carousel1.jpg",
    "../../../resource/image/img-detail/carousel2.jpg",
    "../../../resource/image/img-detail/carousel3.jpg"
];

let currentIndex = 0;

function showSlide(index) {
    currentIndex = index;
    const carouselImage = document.getElementById("carousel-image");
    carouselImage.src = images[currentIndex];
    updateDots();
}

function nextSlide() {
    currentIndex = (currentIndex + 1) % images.length;
    showSlide(currentIndex);
}

function prevSlide() {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    showSlide(currentIndex);
}

function updateDots() {
    const dots = document.querySelectorAll(".dot");
    dots.forEach((dot, index) => {
        dot.classList.toggle("active", index === currentIndex);
    });
}
