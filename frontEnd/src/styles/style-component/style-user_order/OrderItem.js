$(document).ready(function () {
    const viewDetail = $('.btn_detail')

    viewDetail.on('click', function () {
        const data = $(this).data('src')
        const message = {
            type: 'openOrderDetail',
            src: data
        };

        console.log(message)
        // console.log(data)
        window.parent.postMessage(message , 'http://localhost:63342/web-programming/frontEnd/src/pages/UserProfile.html');
        console.log("Da goi data")



    })
})