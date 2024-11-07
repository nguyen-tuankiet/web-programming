function addOptionGroup() {
    const optionGroup = document.createElement('div');
    optionGroup.classList.add('option-group');

    const select = document.createElement('select');
    select.classList.add('option-select');
    select.innerHTML = `
            <option value="size">Kích thước</option>
            <option value="color">Màu sắc</option>
            <option value="material">Chất liệu</option>
            <option value="style">Kiểu dáng</option>
            <option value="title">Tiêu đề</option>
        `;

    const input = document.createElement('input');
    input.type = 'text';
    input.classList.add('option-input');
    input.placeholder = 'Nhập các thẻ';

    const removeButton = document.createElement('button');
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
    const files = Array.from(event.target.files); // Tạo một mảng từ các tệp đã chọn
    const imagePreviewContainer = document.getElementById('imagePreviewContainer');
    const uploadIcon = document.getElementById('uploadIcon');
    const dragDropText = document.getElementById('dragDropText');


    uploadIcon.style.display = 'none';
    dragDropText.style.display = 'none';


    let currentImages = Array.from(imagePreviewContainer.getElementsByTagName('img'));
    let totalImages = currentImages.length + files.length;


    if (totalImages > 3) {
        files.splice(3 - currentImages.length);
    }


    files.forEach(file => {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            imagePreviewContainer.appendChild(img);
        };
        reader.readAsDataURL(file);
    });


    const moreImagesDiv = document.querySelector('.more-images');
    if (moreImagesDiv) {
        moreImagesDiv.remove();
    }


    if (totalImages > 3) {
        const moreImagesDiv = document.createElement('div');
        moreImagesDiv.className = 'more-images';
        moreImagesDiv.innerText = `+${totalImages - 3}`;
        imagePreviewContainer.appendChild(moreImagesDiv);
    }
});
