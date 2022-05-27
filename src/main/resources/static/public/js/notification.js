const closeNotification = (element) => {
        element.parentNode.parentNode.removeChild(element.parentNode);
}

const notificationSuccess = (message) => {
    let notificationDiv = document.getElementById("notifications");
    notificationDiv.innerHTML += "<div class=\"notification is-success\">\n" +
        "  <button class=\"delete\" onclick='closeNotification(this)'></button>\n" +
        message +
        "</div>"
}

const notificationDanger = (message) => {
    let notificationDiv = document.getElementById("notifications");
    notificationDiv.innerHTML += "<div class=\"notification is-danger\">\n" +
        "  <button class=\"delete\" onclick='closeNotification(this)'></button>\n" +
        message +
        "</div>"
}


const notificationWarning = (message) => {
    let notificationDiv = document.getElementById("notifications");
    notificationDiv.innerHTML += "<div class=\"notification is-warning\">\n" +
        "  <button class=\"delete\" onclick='closeNotification(this)'></button>\n" +
        message +
        "</div>"
}