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
let isExpanded = false;

function resizeIframe(iframe) {
    // Lấy chiều cao ban đầu của iframe
    const initialHeight = 500; // Chiều cao khi chưa mở rộng (bạn có thể chỉnh sửa)
    const expandedHeight = iframe.contentWindow.document.body.scrollHeight; // Chiều cao khi mở rộng

    // Cập nhật chiều cao dựa trên trạng thái
    iframe.style.height = isExpanded ? expandedHeight + 'px' : initialHeight + 'px';
}

function toggleSpecification() {
    const iframe = document.querySelector('.specification .iframe');
    isExpanded = !isExpanded; // Thay đổi trạng thái mở rộng

    resizeIframe(iframe); // Điều chỉnh chiều cao iframe

    document.querySelector('.show-more-btn').textContent = isExpanded ? "Thu gọn" : "Xem thêm";
}
document.addEventListener("DOMContentLoaded", function() {
    const mainToggleButton = document.getElementById("toggle-specs-btn");
    const bottomToggleButton = document.getElementById("toggle-specs-btn-bottom");
    const specsSection = document.getElementById("specification-section");

    if (mainToggleButton && bottomToggleButton && specsSection) {
        mainToggleButton.addEventListener("click", toggleSpecifications);
        bottomToggleButton.addEventListener("click", toggleSpecifications);
    }
});

function toggleSpecifications() {
    if (specsSection.classList.contains("expanded")) {
        specsSection.classList.remove("expanded");
        mainToggleButton.style.display = "inline-block";
        bottomToggleButton.style.display = "none";
    } else {
        specsSection.classList.add("expanded");
        mainToggleButton.style.display = "none";
        bottomToggleButton.style.display = "inline-block";
    }
}
