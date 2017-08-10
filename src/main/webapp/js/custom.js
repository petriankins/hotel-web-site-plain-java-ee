function switchLang(node) {
    var currentPath = encodeURIComponent(window.location.href.replace(window.location.protocol + "//" + window.location.host, ""));
    window.location.href="/locale?lang=" + node.value + "&redirectTo=" + currentPath;
}

function checkForm(form) {

    if (form.password.value != "" && form.password.value == form.passwordConfirm.value) {
        if (form.password.value.length < 6) {
            alert("Error: Password must contain at least six characters!");
            form.password.focus();
            return false;
        }
    } else {
        alert("Error: Please check that you've entered and confirmed your password!");
        form.password.focus();
        return false;
    }

    alert("You entered a valid password!");
    document.getElementById('submitBtn').click();
}
function checkDates(form) {
    var checkInDate = new Date(form.checkIn.value);
    var checkOutDate = new Date(form.checkOut.value);
    if (checkInDate != null && checkOutDate != null) {

        if (checkInDate.getTime() > checkOutDate.getTime()) {
            alert("The first date is after the second date!");
            form.checkIn.focus();
            return false;
        }
    }
    else {
        alert("Please fill the dates ");
        form.checkInt.focus();
        return false;
    }
    document.getElementById('submitBtn').click();
}
