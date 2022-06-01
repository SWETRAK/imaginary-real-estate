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






}

document.onload = () => {

// <div className="card my-margin">
//         <div className="my-card-content">
//             <div className="my-media">
//                 <figure className="my-400x300">
//                     <img src="https://spring-irea.s3.eu-central-1.amazonaws.com/{{ frontImage.imageFileName }}"
//                          alt="Placeholder image">
//                 </figure>
//             </div>
//             <div className="my-media-content">
//                 <p className="title is-4">{{title}}</p>
//                 <p className="subtitle is-6">{{address}}</p>
//                 <p className="is-4"> Area: <strong>{{area}}m2</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
//                     className="fa-solid fa-bed"></i> <strong>{{bedrooms}}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
//                     className="fa-solid fa-bath"></i> <strong>{{bathrooms}}</strong></p>
//                 <div className="content">
//                     {{description}}
//                 </div>
//                 <a href="/details/{{ id }}" className="button is-info">Details</a>
//                 <br>
//                     <button className="button is-success" onClick="addLike({{ id }})">Like</button>
//             </div>
//         </div>
//     </div>
//




}