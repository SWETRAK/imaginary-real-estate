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
        return value !== offer; // zostaw wszystko co nie jest tym czego szukamy
    });
    let newLiked = JSON.stringify(likedJSON);
    localStorage.setItem(likedKey, newLiked);
    debugLocalStorage();

    loadContent();

}

const loadContent = () => {
    const liked = localStorage.getItem(likedKey);
    if(liked === null) {
        return;
    }

    let myDiv = document.getElementById("dynamic-content");

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: liked,
        redirect: 'follow'
    };

    myDiv.innerHTML = `<div id="progress">
                            <div class="my-margin">
                                <progress class="progress is-success is-small" max="100">20%</progress>
                            </div>
                        </div>`;


    fetch("https://irea-app.herokuapp.com/api/v1/get/liked", requestOptions)
        .then(response => response.json())
        .then(result => {
            myDiv.innerHTML = "";
            if(result.length === 0) {
                myDiv.innerHTML = `<div class="my-margin">
                                        <div class="notification is-danger">
                                            <h2 class="subtitle has-text-white">Ups!!! You haven't got liked posts. Go to <a href="/offers">offers page</a> and make some love.</h2>
                                        </div>
                                    </div>`;
            } else {
                result.forEach((offer) => {
                    myDiv.innerHTML += "" +
                        "    <div class=\"card my-margin\">\n" +
                        "        <div class=\"my-card-content\">\n" +
                        "            <div class=\"my-media\">\n" +
                        "                <figure class=\"my-400x300\">\n" +
                        "                    <img src=\"https://spring-irea.s3.eu-central-1.amazonaws.com/"+ offer.frontImage.imageFileName +"\"\n" +
                        "                         alt=\"Placeholder image\">\n" +
                        "                </figure>\n" +
                        "            </div>\n" +
                        "            <div class=\"my-media-content\">\n" +
                        "                <p class=\"title is-4\">" + offer.title + "</p>\n" +
                        "                <p class=\"subtitle is-6\">" + offer.address + "</p>\n" +
                        "                <p class=\"is-4\"> Area: <strong>" + offer.area + "m2</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n" +
                        "                    class=\"fa-solid fa-bed\"></i> <strong>" + offer.bedrooms + "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n" +
                        "                    class=\"fa-solid fa-bath\"></i> <strong>" + offer.bathrooms + "</strong></p>\n" +
                        "                <div class=\"content\">\n" + offer.description + "\n" +
                        "                </div>\n" +
                        "                <a href=\"/details/" + offer.id + "\" class=\"button is-info\">Details</a>\n" +
                        "                <br>\n" +
                        "                    <button class=\"button is-danger\" onClick=\"removeLike(" + offer.id + ")\">Unlike</button>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>"
                });
            }
        })
        .catch(error => console.log('error', error));
}