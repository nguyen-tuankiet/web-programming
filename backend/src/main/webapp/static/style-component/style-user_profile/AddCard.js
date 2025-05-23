function openAddCardOverlay() {
    document.getElementById("add-card-overlay").style.display = "block";
}


function closeAddCardOverlay() {
    document.getElementById("add-card-overlay").style.display = "none";
}


window.onclick = function(event) {
    const overlay = document.getElementById("add-card-overlay");
    const container = document.querySelector(".add-card-container");
    if (event.target === overlay) {
        closeAddCardOverlay();
    }

}

$('#name').on('input', function() {
    this.value = this.value.toUpperCase();
});


$('#card-number').on('input', function() {
    let value = this.value.replace(/\D/g, '').slice(0,16); // bỏ ký tự không phải số
    let formatted = value.match(/.{1,4}/g);
    if (formatted) {
        this.value = formatted.join(' ');
    } else {
        this.value = '';
    }
});


$('#cvv').on('input', function() {
    this.value = this.value.replace(/\D/g, '').slice(0,3);
});



$(document).ready(function() {
    const card_form = $("#card_form");
    card_form.submit(function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const cardNumber = $("#card-number").val()
        const cvv = $("#cvv").val();

        if (cvv.length !== 3) {
            alert("Mã CVV phải gồm 3 chữ số.");
            return;
        }



        const expiry = $('#expiry').val().trim();
        if (!isExpiryValid(expiry)) {
            alert("Ngày hết hạn không hợp lệ hoặc đã quá hạn.");
            return;
        }

        const cardNumberRaw = cardNumber.replace(/\s/g, '');
        if (cardNumberRaw.length !== 16) {
            alert("Số thẻ phải đủ 16 chữ số");
            return;
        }

        const payload={
            name: $("#name").val(),
            cardNumber: cardNumberRaw,
            expiry: expiry,
            cvv: cvv,
            primaryCard: $("#primary-card").is(":checked")
        }

        fetch("add-card", {
            method: "POST",
            body: JSON.stringify(payload),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function(response) {
            if (response.ok) {
                window.location.reload();
                return response.json();
            }
        })

    })
})


function isExpiryValid(expiry) {
    const regex = /^(0[1-9]|1[0-2])\/\d{4}$/;
    if (!regex.test(expiry)) return false;

    const parts = expiry.split('/');
    const month = parseInt(parts[0], 10);
    const year = parseInt(parts[1], 10);

    const now = new Date();
    const currentYear = now.getFullYear();
    const currentMonth = now.getMonth() + 1; // zero-based

    if (year < currentYear) return false;
    if (year === currentYear && month < currentMonth) return false;

    return true;
}
