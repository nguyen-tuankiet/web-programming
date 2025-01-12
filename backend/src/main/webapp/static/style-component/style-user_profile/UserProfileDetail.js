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
            fetch(`admin/uploadImage`, {
                method: "POST",
                body: formData,
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

    const name = $('#name');
    const displayName = $('#displayName');
    const gender = $('input[name="gender"]:checked');
    const birthYear =$('#year')
    const birthMonth =$('#month')
    const birthDay =$('#day')
    const email = $('#email')
    const phone = $('#phone')
    const birth = `${birthYear.val()}-${birthMonth.val().padStart(2, '0')}-${birthDay.val().padStart(2, '0')}`;

    const formData = new FormData();
    formData.append("name", name.val());
    formData.append("displayName", displayName.val());
    formData.append("gender", gender.val());
    formData.append("birth", birth);
    formData.append("email", email.text());
    formData.append("phone", phone.text());


    console.log(formData);

    fetch(`updateUser`, {
        method: 'POST',
        headers: {   'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    }).then(response => {
        response.json()
    })
}