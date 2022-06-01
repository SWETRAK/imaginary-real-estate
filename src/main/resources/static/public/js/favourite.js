const likedKey = 'liked'

const debugLocalStorage = () => {
    console.log(localStorage.getItem(likedKey))
}

const addLike = (offer) => {
    let liked = localStorage.getItem(likedKey);
    let likedJSON = JSON.parse(liked);
    if(likedJSON == null ) {
        let newLiked = JSON.stringify([offer])
        localStorage.setItem(likedKey, newLiked);
    } else {
        likedJSON = likedJSON.filter((value) => {
            return value === offer; // zostaw wszystko co nie jest tym czego szukamy
        });
        if(likedJSON.length === 0) {
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