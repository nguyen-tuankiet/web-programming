$(document).ready(function () {
    const back = $('#back')
    back.on('click', function () {
        const message = {
            type: 'navigate',
            data: back.attr('data-src'),
        }
        window.parent.postMessage(message, '*');
    })


    const pay = $('#pay')
    pay.on('click', function () {
        const message = {
            type: 'navigate',
            url: pay.attr('data-src'),
        }
        console.log(message);
        window.parent.postMessage(message, '*');
    })
})