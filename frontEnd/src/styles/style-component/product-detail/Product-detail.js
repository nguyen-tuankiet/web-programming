// Danh sách ảnh cho carousel
const images = [
    "../../../resource/image/img-detail/carousel1.jpg",
    "../../../resource/image/img-detail/carousel2.jpg",
    "../../../resource/image/img-detail/carousel3.jpg"
];
const mainToggleButton = document.getElementById("toggle-specs-btn");
const bottomToggleButton = document.getElementById("toggle-specs-btn-bottom");
const specsSection = document.getElementById("specification-section");

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


function toggleSpecifications() {
    if (specsSection.style.display === "none") {
        specsSection.style.display = "block";
        mainToggleButton.style.display = "none"; // Hide the main button
    } else {
        specsSection.style.display = "none";
        mainToggleButton.style.display = "inline-block"; // Show the main button
    }
}

// Attach event listeners to both buttons
mainToggleButton.addEventListener("click", toggleSpecifications);
bottomToggleButton.addEventListener("click", toggleSpecifications);


document.addEventListener("DOMContentLoaded", function () {
    const addToCartButtons = document.querySelectorAll(".btn.add");
    const notification = document.getElementById("cart-notification");

    addToCartButtons.forEach(button => {
        button.addEventListener("click", function () {
            notification.classList.remove("hidden");
            notification.classList.add("show");

            setTimeout(() => {
                notification.classList.remove("show");
                notification.classList.add("hidden");
            }, 3000);
        });
    });
});


