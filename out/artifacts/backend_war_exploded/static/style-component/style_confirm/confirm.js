// Tự động tạo họa tiết trang trí
const container = document.querySelector('.confirmation-container');
for (let i = 0; i < 25; i++) {
    const shape = document.createElement('div');
    shape.classList.add('decor', 'shape');

    // Ngẫu nhiên chọn kiểu dáng và kích thước
    const shapes = ['circle', 'triangle', 'cross'];
    const sizes = ['small', 'medium', 'large'];
    shape.classList.add(shapes[Math.floor(Math.random() * shapes.length)]);
    shape.classList.add(sizes[Math.floor(Math.random() * sizes.length)]);

    // Ngẫu nhiên vị trí
    shape.style.top = `${Math.random() * 80}%`;
    shape.style.left = `${Math.random() * 100}%`;

    container.appendChild(shape);
}

// Gắn sự kiện click cho nút ĐĂNG NHẬP
document.getElementById('retryButton').addEventListener('click', function () {
    window.location.href = '../../pages/auth.html'; //
});
