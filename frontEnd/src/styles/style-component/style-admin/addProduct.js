    const optionGroup = document.createElement('div');
    const select = document.createElement('select');
    const input = document.createElement('input');
    const removeButton = document.createElement('button');



function addOptionGroup() {
    optionGroup.classList.add('option-group');

    select.classList.add('option-select');
    select.innerHTML = `
            <option value="size">Kích thước</option>
            <option value="color">Màu sắc</option>
            <option value="material">Chất liệu</option>
            <option value="style">Kiểu dáng</option>
            <option value="title">Tiêu đề</option>
        `;

    input.type = 'text';
    input.classList.add('option-input');
    input.placeholder = 'Nhập các thẻ';

    removeButton.classList.add('remove-option-button');
    removeButton.innerHTML = '×';
    removeButton.onclick = function () {
        removeOptionGroup(removeButton);
    };

    optionGroup.appendChild(select);
    optionGroup.appendChild(input);
    optionGroup.appendChild(removeButton);

    document.getElementById('optionsContainer').appendChild(optionGroup);
}

function removeOptionGroup(button) {
    const optionGroup = button.parentElement;
    document.getElementById('optionsContainer').removeChild(optionGroup);
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
