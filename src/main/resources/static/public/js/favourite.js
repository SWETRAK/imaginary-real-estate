const likedKey = 'liked'

const debugLocalStorage = () => {
    console.log(localStorage.getItem(likedKey))
}

const addLike = (offer) => {
    let liked = localStorage.getItem(likedKey);
    let likedJSON = JSON.parse(liked);
    if (likedJSON == null) {
        let newLiked = JSON.stringify([offer])
        localStorage.setItem(likedKey, newLiked);
    } else {
        const likedJSON1 = likedJSON.filter((value) => {
            return value === offer; // zostaw wszystko co nie jest tym czego szukamy
        });
        if (likedJSON1.length === 0) {
            likedJSON.push(offer);
            let newLiked = JSON.stringify(likedJSON);
            localStorage.setItem(likedKey, newLiked);
        }
    }
    debugLocalStorage();
}

const removeLike = (offer) => {
    let liked = localStorage.getItem(likedKey);
    let likedJSON = JSON.parse(liked);
    likedJSON = likedJSON.filter((value) => {
        return value.name !== offer.name; // zostaw wszystko co nie jest tym czego szukamy
    });
    let newLiked = JSON.stringify(likedJSON);
    localStorage.setItem(likedKey, newLiked);
    debugLocalStorage();
}

const loadContent = () => {
    const liked = localStorage.getItem(likedKey);
    if(liked === null) {
        console.log("brak zgloszeń");
        return;
    }

    console.log("sa zgloszenia");

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: liked,
        redirect: 'follow'
    };

    fetch("https://irea-app.herokuapp.com/api/v1/get/liked", requestOptions)
        .then(response => response.json())
        .then(result => {
            let myDiv = document.getElementById("dynamic-content");
            myDiv.innerHTML = "";
            console.log(result);
            result.forEach((offer) => {
                myDiv.innerHTML += "" +
                    "    <div className=\"card my-margin\">\n" +
                    "        <div className=\"my-card-content\">\n" +
                    "            <div className=\"my-media\">\n" +
                    "                <figure className=\"my-400x300\">\n" +
                    "                    <img src=\"https://spring-irea.s3.eu-central-1.amazonaws.com/"+ offer.frontImage.imageFileName +"\"\n" +
                    "                         alt=\"Placeholder image\">\n" +
                    "                </figure>\n" +
                    "            </div>\n" +
                    "            <div className=\"my-media-content\">\n" +
                    "                <p className=\"title is-4\">" + offer.title + "</p>\n" +
                    "                <p className=\"subtitle is-6\">" + offer.address + "</p>\n" +
                    "                <p className=\"is-4\"> Area: <strong>" + offer.area + "m2</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n" +
                    "                    className=\"fa-solid fa-bed\"></i> <strong>" + offer.bedrooms + "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n" +
                    "                    className=\"fa-solid fa-bath\"></i> <strong>" + offer.bathrooms + "</strong></p>\n" +
                    "                <div className=\"content\">\n" + offer.description + "\n" +
                    "                </div>\n" +
                    "                <a href=\"/details/" + offer.id + "\" className=\"button is-info\">Details</a>\n" +
                    "                <br>\n" +
                    "                    <button className=\"button is-danger\" onClick=\"removeLike(" + offer.id + ")\">Unlike</button>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>"
            })
        })
        .catch(error => console.log('error', error));
}