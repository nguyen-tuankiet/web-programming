// Hàm thêm nhóm tùy chọn mới vào container cụ thể
function addOptionGroup(containerId) {
    // Tạo các phần tử mới
    const optionGroup = document.createElement('div');
    const select = document.createElement('select');
    const secondSelect = document.createElement('select');
    const removeButton = document.createElement('button');

    // Thêm class và nội dung cho các phần tử
    optionGroup.classList.add('option-group');

    select.classList.add('option-select');
    select.innerHTML = `
        <option value="size">Kích thước</option>
        <option value="color">Màu sắc</option>
        <option value="material">Chất liệu</option>
        <option value="style">Kiểu dáng</option>
        <option value="title">Tiêu đề</option>
    `;

    secondSelect.classList.add('option-select');
    secondSelect.innerHTML = `
        <option value="size">Kích thước</option>
        <option value="color">Màu sắc</option>
        <option value="material">Chất liệu</option>
        <option value="style">Kiểu dáng</option>
        <option value="title">Tiêu đề</option>
    `;

    removeButton.classList.add('remove-option-button');
    removeButton.innerHTML = '×';
    removeButton.onclick = function () {
        removeOptionGroup(removeButton);
    };

    // Thêm các phần tử con vào nhóm tùy chọn
    optionGroup.appendChild(select);
    optionGroup.appendChild(secondSelect);
    optionGroup.appendChild(removeButton);

    // Lấy container cần thao tác và thêm nhóm tùy chọn
    const container = document.getElementById(containerId);
    container.appendChild(optionGroup);
}

// Hàm xóa nhóm tùy chọn
function removeOptionGroup(button) {
    const optionGroup = button.parentElement; // Lấy nhóm tùy chọn
    const container = optionGroup.parentElement; // Xác định container hiện tại
    container.removeChild(optionGroup); // Xóa nhóm tùy chọn khỏi container
}



document.getElementById('fileInput').addEventListener('change', function (event) {
    const files = Array.from(event.target.files);
    const imagePreviewContainer = document.getElementById('imagePreviewContainer');
    const uploadIcon = document.getElementById('uploadIcon');
    const dragDropText = document.getElementById('dragDropText');


    uploadIcon.style.display = 'none';
    dragDropText.style.display = 'none';


    let currentImages = Array.from(imagePreviewContainer.getElementsByTagName('img'));
    let totalImages = currentImages.length + files.length;

    if (totalImages > 10) {
        files.splice(10 - currentImages.length);
    }

    files.forEach((file) => {
        const reader = new FileReader();
        reader.onload = function (e) {
            const wrapper = document.createElement('div');
            wrapper.classList.add('image-wrapper');

            const img = document.createElement('img');
            img.src = e.target.result;
            img.classList.add('preview-image');
            img.onclick = function () {
                showPreview(
                    [...Array.from(imagePreviewContainer.getElementsByTagName('img')).map(img => img.src)],
                    currentImages.length
                );
            };

            wrapper.appendChild(img);


            if (imagePreviewContainer.getElementsByClassName('image-wrapper').length === 0) {
                const primaryIcon = document.createElement('span');
                primaryIcon.className = 'primary-icon';
                primaryIcon.innerHTML = '★';
                primaryIcon.title = 'Primary Image';
                wrapper.appendChild(primaryIcon);
            }

            imagePreviewContainer.appendChild(wrapper);


            wrapper.classList.add('image-wrapper');

            img.src = e.target.result;
            img.classList.add('preview-image');

            const overlay = document.createElement('div');
            overlay.classList.add('image-overlay');

            // Icon View
            const viewIcon = document.createElement('span');
            viewIcon.className = 'icon view-icon fas fa-eye';
            viewIcon.onclick = function () {
                showPreview(
                    [...Array.from(imagePreviewContainer.getElementsByTagName('img')).map(img => img.src)],
                    currentImages.length
                );
            };

            // Icon Delete
            const deleteIcon = document.createElement('span');
            deleteIcon.className = 'icon delete-icon fas fa-trash';
            deleteIcon.onclick = function () {
                const confirmation = confirm('Bạn có chắc chắn muốn xóa hình ảnh này?');
                if (confirmation) {
                    wrapper.remove();
                }
            };

            overlay.appendChild(viewIcon);
            overlay.appendChild(deleteIcon);
            wrapper.appendChild(img);
            wrapper.appendChild(overlay);

            imagePreviewContainer.appendChild(wrapper);
        };
        reader.readAsDataURL(file);
    });


    setTimeout(() => {

        const moreImagesDiv = document.querySelector('.more-images');
        if (moreImagesDiv) {
            moreImagesDiv.remove();
        }


        const maxVisibleImages = 3;
        const totalChildren = imagePreviewContainer.querySelectorAll('.image-wrapper').length;

        if (totalChildren > maxVisibleImages) {
            const excessImages = totalChildren - maxVisibleImages;
            const moreImagesDiv = document.createElement('div');
            moreImagesDiv.className = 'more-images';
            moreImagesDiv.innerText = `+${excessImages}`;
            moreImagesDiv.onclick = function () {
                showPreview(
                    [...Array.from(imagePreviewContainer.getElementsByTagName('img')).map(img => img.src)]
                );
            };


            const lastVisibleImage = imagePreviewContainer.querySelectorAll('.image-wrapper')[maxVisibleImages - 1];
            lastVisibleImage.appendChild(moreImagesDiv);
        }
    }, 0);
});


function showPreview(images, startIndex = 0) {
    const previewModal = document.createElement('div');
    previewModal.className = 'preview-modal';

    const closeButton = document.createElement('span');
    closeButton.className = 'close-preview';
    closeButton.innerHTML = '×';
    closeButton.onclick = function () {
        document.body.removeChild(previewModal);
    };

    const carousel = document.createElement('div');
    carousel.className = 'carousel';

    images.forEach((imageSrc, index) => {
        const img = document.createElement('img');
        img.src = imageSrc;
        img.classList.add('carousel-image');
        if (index === startIndex) {
            img.style.display = 'block';
        } else {
            img.style.display = 'none';
        }

        img.addEventListener('wheel', function (event) {
            event.preventDefault();
            const scaleStep = 0.1;
            let scale = parseFloat(img.getAttribute('data-scale')) || 1;
            scale += event.deltaY < 0 ? scaleStep : -scaleStep;
            scale = Math.min(Math.max(0.5, scale), 3);
            img.style.transform = `scale(${scale})`;
            img.setAttribute('data-scale', scale);
        });
        img.addEventListener('dblclick', function () {
            img.style.transform = 'scale(1)';
            img.setAttribute('data-scale', 1);
        });

        carousel.appendChild(img);
    });

    let currentIndex = startIndex;

    const prevButton = document.createElement('button');
    prevButton.className = 'carousel-button prev';
    prevButton.innerHTML = '◀';
    prevButton.onclick = function () {
        const images = carousel.querySelectorAll('.carousel-image');
        images[currentIndex].style.display = 'none';
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        images[currentIndex].style.display = 'block';
    };

    const nextButton = document.createElement('button');
    nextButton.className = 'carousel-button next';
    nextButton.innerHTML = '▶';
    nextButton.onclick = function () {
        const images = carousel.querySelectorAll('.carousel-image');
        images[currentIndex].style.display = 'none';
        currentIndex = (currentIndex + 1) % images.length;
        images[currentIndex].style.display = 'block';
    };

    previewModal.appendChild(closeButton);
    previewModal.appendChild(prevButton);
    previewModal.appendChild(carousel);
    previewModal.appendChild(nextButton);
    document.body.appendChild(previewModal);
}

function addVariant() {
    const optionsContainer = document.getElementById('optionsContainer1');
    const variantGroup = document.querySelector('.variant-group'); // Lấy phần tử mẫu
    const newVariant = variantGroup.cloneNode(true); // Tạo bản sao

    // Reset các giá trị trong bản sao
    const inputs = newVariant.querySelectorAll('input');
    inputs.forEach(input => input.value = ''); // Xóa giá trị input

    // Gắn bản sao vào container
    optionsContainer.appendChild(newVariant)
    console.log(optionsContainer, variantGroup);

}
window.addVariant = addVariant;

function removeVariant(button) {
    const variantGroup = button.closest('.variant-group'); // Tìm phần tử cha gần nhất
    if (variantGroup) {
        variantGroup.remove();
    }
}

document.getElementById('fileInput').addEventListener('change', function (event) {
    const formData = new FormData();
    formData.append('file', event.target.files[0]);

    fetch(`${pageContext.request.contextPath}/uploadImage`, {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            const imagePreviewContainer = document.getElementById('imagePreviewContainer');
            imagePreviewContainer.innerHTML = `<p>${data}</p>`;
        })
        .catch(error => console.error('Error:', error));
});

document.addEventListener("DOMContentLoaded", function () {
    // Lấy phần tử dropdown
    const categoryDropdown = document.getElementById("categoryDropdown");

    // Kiểm tra phần tử dropdown có tồn tại
    if (!categoryDropdown) {
        console.error("Phần tử dropdown không tồn tại");
        return;
    }

    // Gọi API để lấy danh mục
    fetch('/backend_war/api/categories')
        .then((response) => {
            if (!response.ok) {
                throw new Error('Không thể lấy danh mục');
            }
            return response.json();
        })
        .then((data) => {
            if (data.statusCode === 200 && data.data) {
                // Xóa các option cũ (nếu cần)
                categoryDropdown.innerHTML = '<option>Chọn danh mục</option>';

                // Thêm các danh mục mới
                data.data.forEach((category) => {
                    const option = document.createElement('option');
                    option.value = category.id;
                    option.textContent = category.name;
                    categoryDropdown.appendChild(option);
                });
            } else {
                console.error("Dữ liệu danh mục không hợp lệ");
            }
        })
        .catch((err) => {
            console.error("Error fetching categories:", err);
        });
});

// nha cung cap

document.addEventListener("DOMContentLoaded", function () {
    // Lấy phần tử dropdown
    const vendor = document.getElementById("vendor");

    // Kiểm tra phần tử dropdown có tồn tại
    if (!vendor) {
        console.error("Phần tử dropdown không tồn tại");
        return;
    }

    // Gọi API để lấy danh mục
    fetch('/backend_war/api/brand')
        .then((response) => {
            if (!response.ok) {
                throw new Error('Không thể lấy nhà cung cấp');
            }
            return response.json();
        })
        .then((data) => {
            if (data.statusCode === 200 && data.data) {
                // Xóa các option cũ (nếu cần)
                vendor.innerHTML = '<option>Chọn nhà cung cấp</option>';

                // Thêm các danh mục mới
                data.data.forEach((brand) => {
                    const option = document.createElement('option');
                    option.value = brand.id;
                    option.textContent = brand.name;
                    vendor.appendChild(option);
                });
            } else {
                console.error("Dữ liệu nhà cung cấp không hợp lệ");
            }
        })
        .catch((err) => {
            console.error("Error fetching brand:", err);
        });
});

//  variant
// Base API URL
const API_BASE_URL = "/api";

// Load all variants when the page is loaded
document.addEventListener("DOMContentLoaded", () => {
    loadVariants();
});

// Fetch and populate the variants in the dropdown
async function loadVariants() {
    try {
        const response = await fetch(`/backend_war/api/variants`);
        const data = await response.json();

        if (data.statusCode === 200) {
            populateVariants(data.data);
        } else {
            alert(data.message);
        }
    } catch (error) {
        console.error("Error fetching variants:", error);
    }
}

// Populate the variants dropdown
function populateVariants(variants) {
    const variantSelect = document.getElementById("variant-select");
    variantSelect.innerHTML = `<option value="">Chọn biến thể</option>`;
    variants.forEach(variant => {
        const option = document.createElement("option");
        option.value = variant.id; // Lưu ID để fetch Variant-Values
        option.textContent = variant.name; // Hiển thị tên
        variantSelect.appendChild(option);
    });
}

// Hàm tải danh sách variant-values dựa trên variantId
function fetchVariantValues(variantId) {
    if (!variantId) {
        // Reset danh sách khi không có giá trị được chọn
        document.getElementById("variant-value-select").innerHTML = '<option value="">Chọn giá trị</option>';
        return;
    }

    fetch(`/backend_war/api/variants/${variantId}`)
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch variant values");
            return response.json();
        })
        .then(data => {
            const variantValueSelect = document.getElementById("variant-value-select");
            variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';
            if (data.statusCode === 200) {
                if (data.data && data.data.length > 0) {
                    data.data.forEach(value => {
                        const option = document.createElement("option");
                        option.value = value.id;
                        option.textContent = value.value; // Gán giá trị hiển thị
                        variantValueSelect.appendChild(option);
                    });
                } else {
                    console.warn("No variant values found for the selected variant.");
                }
            } else {
                console.error("Error fetching variant values:", data.message);
            }
        })
        .catch(error => console.error("Error:", error.message));
}


// Lắng nghe sự kiện khi chọn variant
document.getElementById("variant-select").addEventListener("change", function () {
    const variantId = this.value;
    fetchVariantValues(variantId);
});

// Gọi hàm để tải danh sách variants khi trang được load
document.addEventListener("DOMContentLoaded", fetchVariants);
