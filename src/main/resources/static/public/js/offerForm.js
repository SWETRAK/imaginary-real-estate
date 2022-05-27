let emailError = false;
let titleError = false;
let addressError = false;
let priceError = false;
let areaError = false;
let descriptionError = false;


document.getElementById("front_photo").onchange = () => {
    const frontImageInput = document.getElementById("front_photo");
    const frontImageLabel = document.getElementById("front_photo_label");
    if(frontImageInput.files.length > 0) {
        frontImageLabel.textContent = frontImageInput.files[0].name;
    } else {
        frontImageLabel.textContent = "Don't selected"
    }
}

document.getElementById("add_photo1").onchange = () => {
    const addImage1 = document.getElementById("add_photo1");
    const addImage1Label = document.getElementById("add_photo1_label");
    if(addImage1.files.length > 0) {
        addImage1Label.textContent = addImage1.files[0].name;
    } else {
        addImage1Label.textContent = "Don't selected";
    }
}

document.getElementById("add_photo2").onchange = () => {
    const addImage2 = document.getElementById("add_photo2");
    const addImage2Label = document.getElementById("add_photo2_label");
    if(addImage2.files.length > 0) {
        addImage2Label.textContent = addImage2.files[0].name;
    } else {
        addImage2Label.textContent = "Don't selected";
    }

}

document.getElementById("add_photo3").onchange = () => {
    const addImage3 = document.getElementById("add_photo3");
    const addImage3Label = document.getElementById("add_photo3_label");
    if(addImage3.files.length > 0) {
        addImage3Label.textContent = addImage3.files[0].name;
    } else {
        addImage3Label.textContent = "Don't selected";
    }

}

document.getElementById("add_photo4").onchange = () => {
    const addImage4 = document.getElementById("add_photo4");
    const addImage4Label = document.getElementById("add_photo4_label");
    if(addImage4.files.length > 0) {
        addImage4Label.textContent = addImage4.files[0].name;
    } else {
        addImage4Label.textContent = "Don't selected";
    }

}

document.getElementById("add_photo5").onchange = () => {
    const addImage5 = document.getElementById("add_photo5");
    const addImage5Label = document.getElementById("add_photo5_label");
    if(addImage5.files.length > 0) {
        addImage5Label.textContent = addImage5.files[0].name;
    } else {
        addImage5Label.textContent = "Don't selected";
    }
}

document.getElementById("title").onchange = () => {
    const title = document.getElementById("title").value;
    const titleLabel = document.getElementById("title-message");
    if( title.length === 0) {
        titleLabel.classList.remove("is-hidden");
        titleError = true;
    } else {
        titleLabel.classList.add("is-hidden");
        titleError = false;
    }
}

document.getElementById("address").onchange = () => {
    const address = document.getElementById("address").value;
    const addressLabel = document.getElementById("address-message");
    if( address.length === 0) {
        addressLabel.classList.remove("is-hidden");
        addressError = true;
    } else {
        addressLabel.classList.add("is-hidden");
        addressError = false;
    }
}

document.getElementById("price").onchange = () => {
    const price = document.getElementById("price").value;
    const priceLabel = document.getElementById("price-message");
    if( price.length === 0) {
        priceLabel.classList.remove("is-hidden");
        priceError = true;
    } else {
        priceLabel.classList.add("is-hidden");
        priceError = false;
    }
}

document.getElementById("area").onchange = () => {
    const area = document.getElementById("area").value;
    const areaLabel = document.getElementById("area-message");
    if( area.length === 0) {
        areaLabel.classList.remove("is-hidden");
        areaError = true;
    } else {
        areaLabel.classList.add("is-hidden");
        areaError = false;
    }
}

document.getElementById("description").onchange = () => {
    const description = document.getElementById("description").value;
    const descriptionLabel = document.getElementById("description-message");
    if( description.length === 0) {
        descriptionLabel.textContent = "Description field can't be empty";
        descriptionLabel.classList.remove("is-hidden");
        descriptionError = true;
    } else {
        descriptionLabel.classList.add("is-hidden");
        descriptionError = false;
    }

    if( description.length > 2048) {
        descriptionLabel.textContent = "Description field max text length is 2048. Your text is to long.";
        descriptionLabel.classList.remove("is-hidden");
        descriptionError = true;
    } else {
        descriptionLabel.classList.add("is-hidden");
        descriptionError = false;
    }
}


document.getElementById("email").onchange = () => {
    const email = document.getElementById("email").value
    const emailLabel = document.getElementById("email-message");
    if(!String(email)
        .toLowerCase()
        .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        ) || email.length === 0) {
        emailLabel.classList.remove("is-hidden");
        emailError = true;
    } else {
        emailLabel.classList.add("is-hidden");
        emailError = false;
    }
}

document.getElementById("submit_button").addEventListener("click", (event) => {
    event.preventDefault();

    if(emailError || areaError || descriptionError || titleError || priceError || addressError ) {
        notificationWarning("Inserted values are incorrect. Correct them and try again.");
        return;
    }

    const title = document.getElementById("title").value;
    const address = document.getElementById("address").value;
    const price = parseInt(document.getElementById("price").value);
    const area = parseInt(document.getElementById("area").value);
    const bedrooms = parseInt(document.getElementById("bedrooms").value);
    const bathrooms = parseFloat(document.getElementById("bathrooms").value);
    const description = document.getElementById("description").value;
    const email = document.getElementById("email").value;
    const terms = document.getElementById("terms").checked;
    // IMAGES
    const frontImageInput = document.getElementById("front_photo");
    const addImage1 = document.getElementById("add_photo1");
    const addImage2 = document.getElementById("add_photo2");
    const addImage3 = document.getElementById("add_photo3");
    const addImage4 = document.getElementById("add_photo4");
    const addImage5 = document.getElementById("add_photo5");


    if(isNaN(price) || isNaN(area) || isNaN(bedrooms) || isNaN(bathrooms) ) {
        notificationWarning("Inserted values are incorrect. Correct them and try again.");
        return;
    }

    if(terms === false) {
        notificationWarning("You must agree with privacy policy and with user terms");
        return;
    }

    const formdata = new FormData();
    formdata.append("title", title);
    formdata.append("email", email);
    formdata.append("address", address);
    formdata.append("price", price);
    formdata.append("area", area);
    formdata.append("bedrooms", bedrooms);
    formdata.append("bathrooms", bathrooms);
    formdata.append("description", description);
    formdata.append("terms", terms);


    if(frontImageInput.files.length === 0) {
        notificationWarning("Please select front offer file");
        return;
    }
    formdata.append("front_photo", frontImageInput.files[0], frontImageInput.files[0].name);

    if(addImage1.files.length === 1) {
        formdata.append("add_photo1", addImage1.files[0], addImage1.files[0].name);
    }

    if(addImage2.files.length === 1) {
        formdata.append("add_photo2", addImage2.files[0], addImage2.files[0].name);
    }

    if(addImage3.files.length === 1) {
        formdata.append("add_photo3", addImage3.files[0], addImage3.files[0].name);
    }

    if(addImage4.files.length === 1) {
        formdata.append("add_photo4", addImage4.files[0], addImage4.files[0].name);
    }

    if(addImage5.files.length === 1) {
        formdata.append("add_photo5", addImage5.files[0], addImage5.files[0].name);
    }

    let requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    const progressBar = document.getElementById("progress");

    progressBar.classList.remove("is-hidden");
    fetch("https://irea-app.herokuapp.com/api/v1/create", requestOptions)
        .then(response => {
            if (response.status === 200) {
                response.json();
            } else {
                return;
            }
        })
        .then(result => {
            progressBar.classList.add("is-hidden");

            document.getElementById("title").value = "";
            document.getElementById("address").value = "";
            document.getElementById("price").value ="";
            document.getElementById("area").value = "";
            document.getElementById("bedrooms").value = 1;
            document.getElementById("bathrooms").value = 1;
            document.getElementById("description").value = "";
            document.getElementById("email").value = "";
            document.getElementById("terms").checked = false;

            notificationSuccess("Success. Your offer is ready");
        })
        .catch(error => {
            progressBar.classList.add("is-hidden");
            notificationDanger("Ups. Something went wrong. Try later or consult with your network administrator");

        });
})


