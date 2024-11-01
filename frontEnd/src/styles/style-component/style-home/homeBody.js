function showImage(imageId) {
    const images = document.querySelectorAll('.content img');
    images.forEach(img => {
        img.classList.remove('active');
        img.style.display = 'none';
    });
    const activeImage = document.getElementById(imageId);
    activeImage.classList.add('active');
    activeImage.style.display = 'block';

    const productText = document.querySelector(`.navbar a[onclick="showImage('${imageId}')"]`).getAttribute('data-text');
    document.getElementById('product-description').innerText = productText;
}