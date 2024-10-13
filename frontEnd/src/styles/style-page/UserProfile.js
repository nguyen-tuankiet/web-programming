document.addEventListener('DOMContentLoaded', () => {

    window.addEventListener('message', (event) => {

        if (event.data.type === 'openUserProfile') {
            console.log('Thông điệp đã được nhận từ iframe:', event.data.src);
            const iframe = document.getElementById('main_content'); // Đảm bảo rằng bạn có ít nhất một iframe trong document

            if (iframe) {
                iframe.src = event.data.src;
                console.log('Đã thay đổi src của iframe thành:', iframe.src);

            } else {
                console.error('Không tìm thấy iframe trong document.');
            }
        }
        else{
            console.log("Out");

        }
    });
});
