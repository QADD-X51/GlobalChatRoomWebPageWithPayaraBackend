const baseUrl = "http://localhost:8080/PayaraWebApplication-1.0-SNAPSHOT/main";
const baseAddressUrl = window.location.href.replace(/\/[^\/]*$/, "/");
let sendChatTextButton = document.getElementById('sendChatText');
let logoutButton = document.getElementById('logoutButton');
let chatTextToSendField = document.getElementById('chatTextToSend');
var lastFormattedDate = "";

function padZero(num) {
    return num < 10 ? "0" + num : num;
}

function getAllMessages() {
    const getAllURL = `${baseUrl}/message/all`;
    fetch(getAllURL, {
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
            const messageChatContainer = document.getElementById('message-chatContainer');
            messageChatContainer.innerHTML = '';
            data.forEach(function (messageEntry) {
                var messageContainer = document.createElement('div');
                messageContainer.className = 'messageContainer';

                if (messageEntry.username === sessionStorage.getItem("username")) {
                    messageContainer.style.backgroundColor = "#4682b4";
                }

                var bubble = document.createElement('div');
                bubble.className = 'bubble';

                var shortUsernameSpan = document.createElement('span');
                shortUsernameSpan.className = 'shortUsername';
                shortUsernameSpan.textContent = messageEntry.username.slice(0, 2);

                var fullUsernameSpan = document.createElement('span');
                fullUsernameSpan.className = 'fullUsername';
                fullUsernameSpan.textContent = messageEntry.username;

                bubble.appendChild(shortUsernameSpan);
                bubble.appendChild(fullUsernameSpan);

                var spanElement = document.createElement('span');
                spanElement.className = 'messageChat';
                spanElement.textContent = messageEntry.message;

                let aux = shortUsernameSpan.textContent;

                bubble.addEventListener('mouseenter', function () {
                    shortUsernameSpan.textContent = fullUsernameSpan.textContent;
                });

                bubble.addEventListener('mouseleave', function () {
                    shortUsernameSpan.textContent = aux;
                });

                var dateString = messageEntry.date;

                var dateObject = new Date(dateString);

                var year = dateObject.getFullYear();
                var month = dateObject.getMonth() + 1;
                var day = dateObject.getDate();
                var hour = dateObject.getHours();
                var minute = dateObject.getMinutes();

                var formattedTime = padZero(hour) + ":" + padZero(minute);
                var formattedDateTime = year + "/" + padZero(month) + "/" + padZero(day) + " " + padZero(hour) + ":" + padZero(minute);

                var bubbleData = document.createElement('div');
                bubbleData.className = 'bubble';

                var shortDataSpan = document.createElement('span');
                shortDataSpan.className = 'shortUsername';
                shortDataSpan.textContent = formattedTime;

                var fullDataSpan = document.createElement('span');
                fullDataSpan.className = 'fullUsername';
                fullDataSpan.textContent = formattedDateTime;

                bubbleData.appendChild(shortDataSpan);
                bubbleData.appendChild(fullDataSpan);

                let auxData = shortDataSpan.textContent;

                bubbleData.addEventListener('mouseenter', function () {
                    shortDataSpan.textContent = fullDataSpan.textContent;
                });

                bubbleData.addEventListener('mouseleave', function () {
                    shortDataSpan.textContent = auxData;
                });

                messageContainer.appendChild(bubble);
                messageContainer.appendChild(spanElement);
                messageContainer.appendChild(bubbleData);

                messageChatContainer.appendChild(messageContainer);
            });
            var currentDate = new Date();
            lastFormattedDate = currentDate.getFullYear() + '-' +
                padZero(currentDate.getMonth() + 1) + '-' +
                padZero(currentDate.getDate()) + 'T' +
                padZero(currentDate.getHours()) + ':' +
                padZero(currentDate.getMinutes()) + ':' +
                padZero(currentDate.getSeconds()) + '.' +
                (currentDate.getMilliseconds() / 1000).toFixed(3).slice(2, 5);
        });
}

function updateMessages() {
    var currentDate = new Date();
    const getAllURL = `${baseUrl}/message/${lastFormattedDate}`;
    lastFormattedDate =
        currentDate.getFullYear() + '-' +
        padZero(currentDate.getMonth() + 1) + '-' +
        padZero(currentDate.getDate()) + 'T' +
        padZero(currentDate.getHours()) + ':' +
        padZero(currentDate.getMinutes()) + ':' +
        padZero(currentDate.getSeconds()) + '.' +
        (currentDate.getMilliseconds() / 1000).toFixed(3).slice(2, 5);
    fetch(getAllURL, {
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
            const messageChatContainer = document.getElementById('message-chatContainer');
            data.forEach(function (messageEntry) {
                console.log(messageEntry);
                var messageContainer = document.createElement('div');
                messageContainer.className = 'messageContainer';

                if (messageEntry.username === sessionStorage.getItem("username")) {
                    messageContainer.style.backgroundColor = "#4682b4";
                }

                var bubble = document.createElement('div');
                bubble.className = 'bubble';

                var shortUsernameSpan = document.createElement('span');
                shortUsernameSpan.className = 'shortUsername';
                shortUsernameSpan.textContent = messageEntry.username.slice(0, 2);

                var fullUsernameSpan = document.createElement('span');
                fullUsernameSpan.className = 'fullUsername';
                fullUsernameSpan.textContent = messageEntry.username;

                bubble.appendChild(shortUsernameSpan);
                bubble.appendChild(fullUsernameSpan);

                var spanElement = document.createElement('span');
                spanElement.className = 'messageChat';
                spanElement.textContent = messageEntry.message;

                let aux = shortUsernameSpan.textContent;

                bubble.addEventListener('mouseenter', function () {
                    shortUsernameSpan.textContent = fullUsernameSpan.textContent;
                });

                bubble.addEventListener('mouseleave', function () {
                    shortUsernameSpan.textContent = aux;
                });

                var dateString = messageEntry.date;

                var dateObject = new Date(dateString);

                var year = dateObject.getFullYear();
                var month = dateObject.getMonth() + 1;
                var day = dateObject.getDate();
                var hour = dateObject.getHours();
                var minute = dateObject.getMinutes();

                var formattedTime = padZero(hour) + ":" + padZero(minute);
                var formattedDateTime = year + "/" + padZero(month) + "/" + padZero(day) + " " + padZero(hour) + ":" + padZero(minute);

                var bubbleData = document.createElement('div');
                bubbleData.className = 'bubble';

                var shortDataSpan = document.createElement('span');
                shortDataSpan.className = 'shortUsername';
                shortDataSpan.textContent = formattedTime;

                var fullDataSpan = document.createElement('span');
                fullDataSpan.className = 'fullUsername';
                fullDataSpan.textContent = formattedDateTime;

                bubbleData.appendChild(shortDataSpan);
                bubbleData.appendChild(fullDataSpan);

                let auxData = shortDataSpan.textContent;

                bubbleData.addEventListener('mouseenter', function () {
                    shortDataSpan.textContent = fullDataSpan.textContent;
                });

                bubbleData.addEventListener('mouseleave', function () {
                    shortDataSpan.textContent = auxData;
                });

                messageContainer.appendChild(bubble);
                messageContainer.appendChild(spanElement);
                messageContainer.appendChild(bubbleData);

                messageChatContainer.appendChild(messageContainer);
            });
        });
}

setInterval(updateMessages, 1000);

sendChatTextButton.onclick = function () {
    var userText = chatTextToSendField.value;
    if (userText.length > 0) {
        const getAllURL = `${baseUrl}/message/add`;
        fetch(getAllURL, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                message: userText,
                senderId: sessionStorage.getItem("id"),
            })
        });
        chatTextToSendField.value = '';
    }
}

chatTextToSendField.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        var userText = chatTextToSendField.value;
        if (userText.length > 0) {
            const getAllURL = `${baseUrl}/message/add`;
            fetch(getAllURL, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    message: userText,
                    senderId: sessionStorage.getItem("id"),
                })
            });
            chatTextToSendField.value = '';
        }
    }
})

logoutButton.onclick = function () {
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("id");
    const redirectUrl = baseAddressUrl + "login.html";
    window.location.replace(redirectUrl);
}
$(window).on('load', function () {
    getAllMessages();
    var loggedUsername = sessionStorage.getItem("username");
    $('#usernameNavBar').text(loggedUsername);
});