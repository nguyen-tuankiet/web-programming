$(document).ready(function () {
    const upload_avatar = $('#upload_avatar');
    const btn_upload= $('#btn_upload');
    const avatar = $('#avatar');
    const save = $('#save');

    btn_upload.on('click', function () {
        upload_avatar.click();
    })

    upload_avatar.on('change', function (event) {
        const file = event.target.files[0];
        if (file){

            const formData = new FormData();
            formData.append("file", file);
            fetch(`api/uploadImage`, {
                method: "POST",
                body: formData,
                credentials: 'include',
            })
            .then(
                response => response.json()
            ).then(data => {
                if (data.statusCode === 200) {
                    const imageId = data.data[0].id;

                    fetch(`update-avatar`,{
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({imageId: imageId}),
                    })
                }
            })

            const reader = new FileReader();
            reader.onload = function (e) {
                avatar.attr('src', e.target.result);
            }
            reader.readAsDataURL(file);


        }
    })







    save.on('click', function (event) {
        update_profile();
    })









})


function update_profile() {
    const name = $('#name').val().trim();
    const displayName = $('#displayName').val().trim();
    const gender = $('input[name="gender"]:checked');
    const genderValue = gender.length > 0 ? gender.val() : null;

    const phone = $('#phone').val().trim();
    if (!isValidPhoneNumber(phone)) {
        alert("Số điện thoại không hợp lệ. Vui lòng nhập đúng định dạng.");
        return;
    }


    const formData = new FormData();
    formData.append("fullName", name);
    formData.append("displayName", displayName);
    formData.append("gender", genderValue);
    formData.append("phone", phone);

    for (let [key, value] of formData.entries()) {
        console.log(`${key}: ${value}`);
    }


    const jsonObject = {};
    formData.forEach((value, key) => {
        jsonObject[key] = value;
    });


    fetch(`updateUser`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(jsonObject),
    }).then(response => {
         return  response.json()
    }).then(data => {
        if (data.success) {
            alert("Update success! ");
        }
    })
}

function isValidPhoneNumber(phone) {
    const phoneRegex = /^(03|05|07|08|09)\d{8}$/;
    return phoneRegex.test(phone);
}