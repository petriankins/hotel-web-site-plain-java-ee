function switchLang(node) {
    window.location.href="/locale?lang=" + node.value;
}

function checkForm(form) {

    if (form.password.value != "" && form.password.value == form.passwordConfirm.value) {
        if (form.password.value.length < 6) {
            alert("Error: Password must contain at least six characters!");
            form.password.focus();
            return false;
        }
        if (form.password.value == form.login.value) {
            alert("Error: Password must be different from Username!");
            form.password.focus();
            return false;
        }
    } else {
        alert("Error: Please check that you've entered and confirmed your password!");
        form.pwd1.focus();
        return false;
    }

    alert("You entered a valid password: " + form.password.value);
    return true;
}