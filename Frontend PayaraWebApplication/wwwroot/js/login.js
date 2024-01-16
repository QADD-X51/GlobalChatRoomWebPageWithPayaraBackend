const baseUrl = "http://localhost:8080/PayaraWebApplication-1.0-SNAPSHOT/main";
const baseAddressUrl = window.location.href.replace(/\/[^\/]*$/, "/");
const currentUrl = window.location.href;

let loginButton = document.getElementById("loginButton");
let registerButton = document.getElementById("registerButton");
let usernameField = document.getElementById("usernameField");
let passwordField = document.getElementById("passwordField");
let confirmPasswordField = document.getElementById("confirmPasswordField");
let title = document.getElementById("title");
let errorText = document.getElementById("errorText");
const asciiRegex = /^[a-zA-Z0-9_-]+$/;

confirmPasswordField.style.maxHeight = "0px";

loginButton.onclick = function () {
    if (loginButton.classList.contains('disabled')) {
        confirmPasswordField.style.maxHeight = "0px";
        title.innerHTML = "Log In";
        registerButton.classList.add("disabled");
        loginButton.classList.remove("disabled");
        errorText.innerHTML = " ";
    }
    else if (!loginButton.classList.contains('disabled')) {
        if (usernameField.querySelector('input').value == "") {
            errorText.innerHTML = "Username field is empty.";
        }
        else if (!isValidASCIIString(usernameField.querySelector('input').value)) {
            errorText.innerHTML = "Username contains disallowed characters.";
        }
        else if (passwordField.querySelector('input').value == "") {
            errorText.innerHTML = "Password field is empty.";
        }
        else {
            const loginURL = `${baseUrl}/user/login/${usernameField.querySelector('input').value}/${passwordField.querySelector('input').value}`;
            fetch(loginURL, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json',
                }
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.json().then(error => { throw error; });
                }
            })
            .then(data => {
                console.log(data);
                if (data.validationResult === "Ok") {
                    sessionStorage.setItem("id", data.userId);
                    sessionStorage.setItem("username", usernameField.querySelector('input').value);
                    const redirectUrl = baseAddressUrl + "lobbyChat.html";
                    window.location.replace(redirectUrl);
                }
                else {
                    errorText.innerHTML = data.validationResult;
                }
                })
            .catch(error => {
                console.error(error);
                errorText.innerHTML = error;
            });
        }
    }
}

registerButton.onclick = function () {
    if (registerButton.classList.contains('disabled')) {
        confirmPasswordField.style.maxHeight = "65px";
        title.innerHTML = "Register";
        registerButton.classList.remove("disabled");
        loginButton.classList.add("disabled");
        errorText.innerHTML = " ";
    }
    else if (!registerButton.classList.contains('disabled')) {
        if (passwordField.querySelector('input').value == confirmPasswordField.querySelector('input').value &&
            passwordField.querySelector('input').value != "") {
            const registerURL = `${baseUrl}/user/register`;
            fetch(registerURL, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: usernameField.querySelector('input').value,
                    password: passwordField.querySelector('input').value,
                })
            })
                .then(response => {
                    if (response.ok) {
                        confirmPasswordField.style.maxHeight = "0px";
                        title.innerHTML = "Log In";
                        registerButton.classList.add("disabled");
                        loginButton.classList.remove("disabled");
                        errorText.innerHTML = " ";
                        return response.text();
                    } else {
                        return response.text().then(error => { throw error; });
                    }
                })
                .then(data => {
                    console.log(data);
                })
                .catch(error => {
                    console.error(error);
                    errorText.innerHTML = error;
                });
        }
        else if (usernameField.querySelector('input').value == ""){
            errorText.innerHTML = "Username field is empty.";
        }
        else if (passwordField.querySelector('input').value == "") {
            errorText.innerHTML = "Password field is empty.";
        }
        else if (confirmPasswordField.querySelector('input').value == "") {
            errorText.innerHTML = "Confirm Password field is empty.";
        }
        else if (passwordField.querySelector('input').value != confirmPasswordField.querySelector('input').value) {
            errorText.innerHTML = "Passwords don't match. Try again.";
        }
    }
}

function isValidASCIIString(input) {
    return asciiRegex.test(input);
}

usernameField.addEventListener("input", function () {
    let inputElement = this.querySelector("input");
    let inputValue = inputElement.value;

    if (!isValidASCIIString(inputValue)) {
        inputValue = inputValue.replace(/[^a-zA-Z0-9_-]/g, '');
        inputElement.value = inputValue;
    }
});

passwordField.addEventListener("input", function () {
    let inputElement = this.querySelector("input");
    let inputValue = inputElement.value;

    if (!isValidASCIIString(inputValue)) {
        inputValue = inputValue.replace(/[^a-zA-Z0-9_-]/g, '');
        inputElement.value = inputValue;
    }
});

confirmPasswordField.addEventListener("input", function () {
    let inputElement = this.querySelector("input");
    let inputValue = inputElement.value;

    if (!isValidASCIIString(inputValue)) {
        inputValue = inputValue.replace(/[^a-zA-Z0-9_-]/g, '');
        inputElement.value = inputValue;
    }
});

