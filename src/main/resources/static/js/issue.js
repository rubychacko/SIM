function validate() {
    if (document.myForm.form_name.value == "") {
        alert("Please provide your name!");
        document.myForm.form_name.focus();
        return false;
    } else {
        let NAME = document.getElementById("form_name").value;
    }
    if (document.myForm.form_lastname.value == "") {
        alert("Please provide your name!");
        document.myForm.form_lastname.focus();
        return false;
    } else {
        let LNAME = document.getElementById("form_lastname").value;
    }
    if (document.myForm.form_email.value == "") {
        alert("Please provide your Email!");
        document.myForm.form_email.focus();
        return false;
    }
    alert("Issue submitted successfully!");
    return (true);
}

function validateEmail() {
    let emailID = document.myForm.form_email.value;
    atpos = emailID.indexOf("@");
    dotpos = emailID.lastIndexOf(".");
    if (atpos < 1 || (dotpos - atpos < 2)) {
        alert("Please enter correct email ID");
        document.myForm.form_email.focus();
        return false;
    } else {
        return true;
    }
    return (true)
}
