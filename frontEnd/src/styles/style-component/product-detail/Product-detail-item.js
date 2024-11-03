const images = [
    "../../../resource/image/img-detail/image1.jpg",
    "../../../resource/image/img-detail/image2.jpg",
    "../../../resource/image/img-detail/image3.jpg",
    "../../../resource/image/img-detail/image4.jpg",
    "../../../resource/image/img-detail/image5.jpg",
    "../../../resource/image/img-detail/image6.jpg",
    "../../../resource/image/img-detail/image7.jpg"
];
let currentIndex = 0;

function showImage(index) {
    const mainImage = document.getElementById('mainImage');
    const thumbnails = document.querySelectorAll('.thumbnail');

    // Update the main image
    mainImage.src = images[index];
    currentIndex = index;

    // Update active thumbnail
    thumbnails.forEach((thumbnail, i) => {
        if (i === index) {
            thumbnail.classList.add('active');
        } else {
            thumbnail.classList.remove('active');
        }
    });
}

function nextImage() {
    currentIndex = (currentIndex + 1) % images.length;
    showImage(currentIndex);
}

function prevImage() {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    showImage(currentIndex);
}
