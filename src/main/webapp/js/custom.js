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
    document.getElementById('submitBtn').click();
}
function checkDates(form) {
    var checkInDate = new Date(form.checkIn.value);
    var checkOutDate = new Date(form.checkOut.value);
    if (checkInDate != null && checkOutDate != null) {
        var currentDate = new Date();
        currentDate.setHours(0, 0, 0, 0);
        if (checkInDate < currentDate || checkOutDate < currentDate) {
            document.getElementById("date in past").setAttribute("style", "");
            return false;
        }
        if (checkInDate.getTime() > checkOutDate.getTime()) {
            document.getElementById("dates not valid").setAttribute("style", "");
            return false;
        }
    }
    else {
        document.getElementById("empty dates").setAttribute("style", "");
        return false;
    }
    document.getElementById("success").setAttribute("style", "");
    document.getElementById('submitBtn').click();
}
