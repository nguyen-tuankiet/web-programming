$(document).ready(function () {

    //Cover

    const cover = $('#cover');
    const btn_upload_cover = $('#upload_cover');
    const file_cover = $('#file_cover');



    btn_upload_cover.on('click', function(){
        console.log("clicked");
        file_cover.click();

    })

    file_cover.on("change", function(event){
        const file = event.target.files[0];
        if (file){
            const reader = new FileReader();
            console.log(reader);
            reader.onload = function (e) {
                cover.attr('src', reader.result);
            }
            reader.readAsDataURL(file);

        }

    })


    //Image
    const avatar = $('#avatar');
    const btn_upload_avatar = $('#upload_avatar');
    const file_avatar = $('#file_avatar');



    btn_upload_avatar.on('click', function(){
        console.log("clicked");
        file_avatar.click();

    })

    file_avatar.on("change", function(event){
        const file = event.target.files[0];
        if (file){
            const reader = new FileReader();
            console.log(reader);
            reader.onload = function (e) {
                avatar.attr('src', reader.result);
            }
            reader.readAsDataURL(file);

        }

    })





})