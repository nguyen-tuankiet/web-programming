$(document).ready(function () {
    const upload_avatar = $('#upload_avatar');
    const btn_upload= $('#btn_upload');
    const avatar = $('#avatar');

    btn_upload.on('click', function () {
        upload_avatar.click();
    })

    upload_avatar.on('change', function (event) {
        const file = event.target.files[0];
        if (file){

            const formData = new FormData();
            formData.append("file", file);
            fetch(`admin\uploadImage`, {
                method: "POST",
                headers: {
                    "Content-Type": "multipart/form-data",
                },
                body: formData,
            })
            .then(response => response.json())

            const reader = new FileReader();
            reader.onload = function (e) {
                avatar.attr('src', e.target.result);
            }
            reader.readAsDataURL(file);


        }
    })
})