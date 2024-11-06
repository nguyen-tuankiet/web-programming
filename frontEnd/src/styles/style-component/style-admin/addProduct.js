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