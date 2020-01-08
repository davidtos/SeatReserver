window.onload = function () {

};

function search(){
    var searchWords = document.getElementById('searchInput');
    searchWords = searchWords.value;

    var data = null;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            searchResults(JSON.parse(this.responseText));
        }
    });

    xhr.open("GET", '/movies/search/' + searchWords);
    xhr.send(data);
}

function searchResults(movies){
    console.log(movies);
    let myNode = document.getElementById("searchResults");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
    createSearchResultView(movies,myNode);
}

function createSearchResultView(movies, node){
    for (let i = 0; i < movies.length; i++) {
        let movie = movies[i];
        let movieContainer = document.createElement('div');
        movieContainer.className = "col-auto";

        let posterContainer = document.createElement('div');
        posterContainer.className = "col";
        posterContainer.appendChild(createMoviePoster(movie));

        let titleContainer = document.createElement('div');
        titleContainer.innerText = movie.title.substring(0,15);
        titleContainer.className = "col";

        movieContainer.appendChild(posterContainer);
        movieContainer.appendChild(titleContainer);
        movieContainer.onclick = function() {selectMovie(movie); };
        node.appendChild(movieContainer);
    }
}

function createMoviePoster(movie){
    let moviePoster = document.createElement('img');
    moviePoster.setAttribute("src",movie.image);
    moviePoster.style.height = "250px";
    moviePoster.style.width = "150px";
    moviePoster.style.paddingRight = "0px";
    return moviePoster;
}

function selectMovie(movie){
    let data = null;

    let xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            showPerformances(JSON.parse(this.responseText));
        }
    });

    xhr.open("GET", '/movies/performances/' + movie.id);
    xhr.send(data);
}

function showPerformances(performances){
    let myNode = document.getElementById("performances");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }

    let td1 = document.createElement('td');
    td1.innerText = "has 3d";
    let td2 = document.createElement('td');
    td2.innerText = "has atmos";
    let td3 = document.createElement('td');
    td3.innerText = "start time";

    let row = document.createElement('tr');
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);

    myNode.appendChild(row);

    for (let i = 0; i < performances.length; i++) {
        let performance = performances[i];
        let td1 = document.createElement('td');
        td1.innerText = performance.has_3d;
        let td2 = document.createElement('td');
        td2.innerText = performance.has_atmos;
        let td3 = document.createElement('td');
        td3.innerText = performance.start;

        let row = document.createElement('tr');
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        myNode.appendChild(row);
    }
}

function reserveFutureSeats(){

    let reserveMovieTitle = document.getElementById("reserveMovieTitle").value;
    let chairRow = document.getElementById("chairRow").value;
    let charColumn = document.getElementById("charColumn").value;
    let amountOfSeats = document.getElementById("amountOfSeats").value;
    let reserveJacketSeat = document.getElementById("reserveJacketSeat").checked;
    let frontOrBackSeatAllowed = document.getElementById("frontOrBackSeatAllowed").checked;


    let data = JSON.stringify({"title":reserveMovieTitle,
        "row":chairRow,
        "column":charColumn,
        "amountOfSeats":amountOfSeats,
        "reserveJacketSeat":reserveJacketSeat,
        "frontAndBackSeatsAllowed":frontOrBackSeatAllowed});

    let xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            console.log(this.responseText);
        }
    });

    xhr.open("POST", "http://localhost:8080/reserve");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);
}

