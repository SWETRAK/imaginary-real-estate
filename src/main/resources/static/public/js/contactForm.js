const contactForm = (element, id) => {

    window.event.preventDefault();

    const email = document.getElementById("contact-email").value;

    if (email.length === 0) {
        notificationWarning("Inserted values are incorrect. Correct them and try again.");
        return;
    }

    const formdata = new FormData();
    formdata.append("email", email);
    let requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch(`https://irea-app.herokuapp.com/api/v1/send/email/${id}`, requestOptions)
        .then(response => response.text())
        .then(result => {
            // TODO: Sprawdzanie ciała result aby zobaczyć czy jest odpowiedni wynik requesta
            notificationSuccess("Messages sent");
            console.log(result);
            document.getElementById("contact-email").value = "";
            element.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.classList.remove('is-active');
        })
        .catch(error => {
            notificationDanger("Ups. Something went wrong. Try later or consult with your network administrator");
            document.getElementById("contact-email").value = "";
        });
}