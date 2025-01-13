const variantValueSelect = document.getElementById("variant-value-select");

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

async function addOptionGroup(containerId) {
    const optionGroup = document.createElement('div');
    const select = document.createElement('select');
    const secondSelect = document.createElement('select');
    const removeButton = document.createElement('button');

    // Thêm class và nội dung cho các phần tử
    optionGroup.classList.add('option-group');
    select.classList.add('option-select');
    secondSelect.classList.add('option-select');
    removeButton.classList.add('remove-option-button');
    removeButton.innerHTML = '×';
    removeButton.onclick = function () {
        removeOptionGroup(removeButton);
    };

    // Thêm id và sự kiện onchange cho các select mới
    select.id = "variant-select";  // Tạo id giống như phần tử gốc
    select.setAttribute("onchange", "fetchVariantValues(this.value, this.nextElementSibling)"); // Thêm sự kiện onchange

    secondSelect.id = "variant-value-select"; // Tạo id giống như phần tử gốc

    // Lấy danh sách các biến thể (variants) và điền vào dropdowns
    const categoryId = getSelectedCategoryId();
    if (categoryId) {
        try {
            const variants = await loadVariantsByCategory(categoryId); // Chờ đợi kết quả
            populateSelect(select, variants);
        } catch (error) {
            console.error("Lỗi khi tải biến thể:", error);
        }
    } else {
        console.warn("Chưa chọn danh mục.");
    }

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

// Hàm lấy categoryId đã chọn (ví dụ: từ dropdown)
function getSelectedCategoryId() {
    const categorySelect = document.getElementById("categoryDropdown");
    return categorySelect ? categorySelect.value : null;
}

// Hàm tải danh sách biến thể dựa trên categoryId
async function loadVariantsByCategory(categoryId) {
    const variantSelect = document.getElementById('variant-select'); // Hoặc chọn select khác nếu cần
    const url = categoryId ? `api/variants?categoryId=${categoryId}` : `api/variants`;
    const response = await fetch(url);
    const data = await response.json();
    if (data.statusCode === 200) {
        return data.data; // Trả về danh sách các biến thể
    } else {
        throw new Error("Không thể tải biến thể");
    }
}

// Hàm gọi API lấy giá trị cho variant và cập nhật select "variant-value-select"
async function fetchVariantValues(variantId) {
    // Lấy dropdown "variant-value-select" trong cùng nhóm tùy chọn
    const variantGroup = event.target.closest('.option-group');
    const variantValueSelect = variantGroup.querySelector('#variant-value-select');

    if (!variantValueSelect) {
        console.error("Không tìm thấy 'variant-value-select'!");
        return;
    }

    if (!variantId) {
        variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';
        return;
    }

    try {
        const response = await fetch(`api/variants/${variantId}`);
        const data = await response.json();

        if (data.statusCode === 200) {
            variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';
            if (data.data && data.data.length > 0) {
                data.data.forEach(value => {
                    const option = document.createElement("option");
                    option.value = value.id;
                    option.textContent = value.value; // Gán giá trị hiển thị
                    variantValueSelect.appendChild(option);
                });
            } else {
                console.warn("Không tìm thấy giá trị cho biến thể đã chọn.");
            }
        } else {
            console.error("Lỗi khi lấy giá trị cho biến thể:", data.message);
        }
    } catch (error) {
        console.error("Lỗi khi gọi API:", error.message);
    }
}



// Hàm hiển thị dữ liệu vào một select element
function populateSelect(selectElement, variants) {
    selectElement.innerHTML = ''; // Xóa các option cũ
    variants.forEach(variant => {
        const option = document.createElement('option');
        option.value = variant.id;
        option.textContent = variant.name; // Hiển thị tên biến thể
        selectElement.appendChild(option);
    });
}








//get category
document.addEventListener("DOMContentLoaded", function () {
    // Lấy phần tử dropdown
    const categoryDropdown = document.getElementById("categoryDropdown");

    // Kiểm tra phần tử dropdown có tồn tại
    if (!categoryDropdown) {
        console.error("Phần tử dropdown không tồn tại");
        return;
    }

    // Gọi API để lấy danh mục
    fetch('api/categories')
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
    fetch(' api/brand')
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
document.addEventListener("DOMContentLoaded", () => {
    const categorySelect = document.getElementById("categoryDropdown");
    const variantSelect = document.getElementById("variant-select");
    const variantValueSelect = document.getElementById("variant-value-select");

    if (!variantSelect || !variantValueSelect) {
        console.error("Các phần tử dropdown không tồn tại");
        return;
    }

    if (!categorySelect){
        loadVariantsByCategory(null);
    }
    // Lắng nghe sự kiện thay đổi của category
    else {
        categorySelect.addEventListener("change", function () {
            const categoryId = this.value;
            if (categoryId) {
                loadVariantsByCategory(categoryId);
            } else {
                // Reset danh sách variant nếu không chọn category
                variantSelect.innerHTML = '<option value="">Chọn biến thể</option>';
                variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';
            }
        });
    }

    // Lắng nghe sự kiện thay đổi của variant
    variantSelect.addEventListener("change", function () {
        const variantId = this.value;
        if (variantId) {
            fetchVariantValues(variantId);
        } else {
            // Reset danh sách variant values nếu không chọn variant
            variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';
        }
    });

    // Hàm tải danh sách variants dựa trên categoryId
    async function loadVariantsByCategory(categoryId) {
        try {
            variantSelect.innerHTML = '<option>Đang tải...</option>'; // Thông báo trạng thái



            const url = categoryId
                ? `api/variants?categoryId=${categoryId}`
                : `api/variants`;

            const response = await fetch(url);
            const data = await response.json();



            if (data.statusCode === 200) {
                populateVariants(data.data);
            } else {
                alert("Không thể tải danh sách biến thể!");
                variantSelect.innerHTML = '<option value="">Chọn biến thể</option>'; // Reset khi lỗi
            }
        } catch (error) {
            console.error("Error fetching variants by category:", error);
            variantSelect.innerHTML = '<option value="">Chọn biến thể</option>'; // Reset khi lỗi
        }
    }

    // Hàm hiển thị danh sách variants
    function populateVariants(variants) {
        variantSelect.innerHTML = `<option value="">Chọn biến thể</option>`;
        variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>'; // Reset variant values
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
            variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';
            return;
        }

        fetch(`api/variants/${variantId}`)
            .then(response => {
                if (!response.ok) throw new Error("Failed to fetch variant values");
                return response.json();
            })
            .then(data => {
                if (!variantValueSelect) {
                    console.error("variantValueSelect không tồn tại!");
                    return;
                }

                variantValueSelect.innerHTML = '<option value="">Chọn giá trị</option>';

                if (data.statusCode === 200) {
                    if (data.data && data.data.length > 0) {
                        data.data.forEach(value => {
                            const option = document.createElement("option");
                            option.value = value.id;
                            option.textContent = value.value;
                            variantValueSelect.appendChild(option);
                        });
                    } else {
                        console.warn("Không tìm thấy giá trị cho biến thể đã chọn.");
                    }
                } else {
                    console.error("Error fetching variant values:", data.message);
                }
            })
            .catch(error => {
                console.error("Error:", error.message);
            });
    }

});


document.addEventListener("DOMContentLoaded", function () {
    const uploadButton = document.getElementById("uploadButton");
    const fileInput = document.getElementById("fileInput");
    const previewImage = document.getElementById("previewImage");
    const saveButton = document.getElementById("saveButton");

    // Mở hộp thoại chọn file khi bấm nút "Tải ảnh lên"
    uploadButton.addEventListener("click", function () {
        fileInput.click();
    });

    // Xử lý khi file được chọn
    fileInput.addEventListener("change", function () {
        const file = fileInput.files[0];
        if (file) {
            // Hiển thị ảnh xem trước
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    });

    saveButton.addEventListener("click", function (event) {
        event.preventDefault(); // Ngăn hành động mặc định của button

        const productName = document.getElementById("productName").value.trim();
        const sku = document.getElementById("sku").value.trim();
        const category = document.getElementById("categoryDropdown").value;
        const brand = document.getElementById("vendor").value;
        const description = document.getElementById("description").value.trim();
        const price = document.getElementById("price").value.trim();
        const stock = document.getElementById("total").value.trim();
        const tags = document.getElementById("tags").value.trim();
        const imageUpload = document.getElementById("fileInput").files;

        if (!productName || !category || !price || !stock) {
            alert("Vui lòng điền đầy đủ các trường bắt buộc!");
            return;
        }

        if (imageUpload.length === 0) {
            alert("Vui lòng tải lên ảnh sản phẩm!");
            return;
        }

        // Upload ảnh trước
        const formData = new FormData();
        Array.from(imageUpload).forEach(file => formData.append("file", file));

        fetch(`uploadImage`, {
            method: "POST",
            body: formData,
        })
            .then(response => response.json())
            .then(data => {
                if (data.statusCode === 200 && data.data && data.data.length > 0) {
                    const imageId = data.data[0].id; // Lấy ID của ảnh chính

                    // Tạo sản phẩm
                    const productData = {
                        name: productName,
                        sku: sku,
                        categoryId: category,
                        description: description,
                        price: parseFloat(price),
                        stock: parseInt(stock),
                        brandId: brand,
                        tags: tags.split(",").map(tag => tag.trim()),
                        isActive: true, // Mặc định là true
                        primaryImage: imageId, // Gán ID của ảnh chính
                    };

                    return fetch(`products`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(productData),
                    })
                        .then(response => response.json())
                        .then(product => {
                            if (product.data && product.data.id) {
                                const productId = product.data.id; // Lấy productId sau khi thêm sản phẩm thành công

                                // Gán các ảnh còn lại cho sản phẩm
                                const imageIds = data.data.slice(1).map(image => image.id); // Lấy các ảnh còn lại, trừ ảnh chính

                                return Promise.all(imageIds.map(imageId => {
                                    return fetch(`ImageDetailDao`, {
                                        method: "POST",
                                        headers: {
                                            "Content-Type": "application/x-www-form-urlencoded",
                                        },
                                        body: `productId=${productId}&imageId=${imageId}`,
                                    })
                                        .then(response => response.json())
                                        .then(result => {
                                            if (result.statusCode !== 200) {
                                                console.error("Không thể thêm ảnh vào sản phẩm");
                                            }
                                        })
                                        .catch(error => console.error("Lỗi khi gán ảnh:", error));
                                }))
                                    .then(() => {
                                        // Tạo option cho sản phẩm
                                        const optionData = {
                                            productId: productId,
                                            price: parseFloat(price), // Giá option lấy từ giá sản phẩm
                                            stock: parseInt(stock),   // Stock option lấy từ stock sản phẩm
                                        };

                                        return fetch(`options/create`, {
                                            method: "POST",
                                            headers: {
                                                "Content-Type": "application/json",
                                            },
                                            body: JSON.stringify(optionData),
                                        });
                                    });
                            } else {
                                throw new Error("Không thể thêm sản phẩm.");
                            }
                        })
                        .then(response => {
                            if (response.ok) {
                                alert("Thêm sản phẩm và option thành công!");
                                window.location.href = 'list-product';
                            } else {
                                throw new Error("Không thể thêm option.");
                            }
                        })
                        .catch(error => {
                            console.error("Lỗi:", error);
                            alert(`Đã xảy ra lỗi: ${error.message}`);
                        });
                } else {
                    throw new Error("Không thể upload ảnh.");
                }
            })
            .catch(error => {
                console.error("Lỗi:", error.message);
                console.log(error);
                alert(`Đã xảy ra lỗi: ${error.message}`);
            });
    });

});
