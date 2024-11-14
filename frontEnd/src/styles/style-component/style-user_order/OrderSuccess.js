$(document).ready(function () {
    const btn = $('#btn');

    btn.on('click', function(){
        const message ={
            type: "navigate",
            data: btn.attr('data-src')
        }
        window.parent.postMessage(message, '*');
    })





})