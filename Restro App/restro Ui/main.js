var restaurants = [];
var restaurant = {
    id: 0,
    name: '',
    city: '',
    address: '',
    cuisine: '',
    rating: 0,
    imageurl : '',

}
function getRestaurants() {
    fetch("http://localhost:8083/restaurants")
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            restaurants = data;
            mapRestaurantsToCard();
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

function mapRestaurantsToCard() {
    var cards = `<div class="row">`;

    for (let i = 0; i < restaurants.length; i++) {
        var ratingStr = "";

        for (let j = 0; j < 5; j++) {
            if (j < restaurants[i].rating)
                ratingStr += `<i class="bi bi-star-fill text-warning me-1"></i>`;
            else
                ratingStr += `<i class="bi bi-star text-warning me-1"></i>`;
        }

        cards += `<div class="card col-3 m-2">
        <img src="${restaurants[i].imageurl}" class="card-img-top">
            <div class="card-body">
                <h4 class="card-title mb-3" >${restaurants[i].name}</h4>
                <h6 class="card-subtitle mb-2">${restaurants[i].city}</h6>
                <p class="card-text">${restaurants[i].address}</p>
                
                <i class="bi bi-trash text-danger delete-icon" onclick="deleteRestaurant(${restaurants[i].id})"></i>
                
                
                </div>
                <ul class="list-group list-group-flush">
                <li class="list-group-item">${restaurants[i].cuisine}</li>
                <li class="list-group-item">${ratingStr}</li>
                </ul>  
                </div>`;
    }

    cards += `</div>`;
    document.getElementById("restaurantCards").innerHTML = cards;
}


function restaurantSubmit() {
    restaurant.name = document.getElementById("name").value;
    restaurant.city = document.getElementById("city").value;
    restaurant.address = document.getElementById("address").value;
    restaurant.cuisine = document.getElementById("cuisine").value;
    restaurant.rating = document.getElementById("rating").value; 
    restaurant.imageurl = document.getElementById("imageurl").value;

    console.log(restaurant);

    fetch("http://localhost:8083/restaurants", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },body: JSON.stringify(restaurant)
    })
    .then(response => response.json())
    .then(data => {
        getRestaurants(); 
    })
    
}




function deleteRestaurant(id) {
    fetch(`http://localhost:8083/restaurants/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        getRestaurants();
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}


function restaurantUpdate() {
    const name = document.getElementById("update-name").value;
    const updatedRestaurant = {
        address: document.getElementById("update-address").value,
        city: document.getElementById("update-city").value,
        cuisine: document.getElementById("update-cuisine").value,
        rating: document.getElementById("update-rating").value,
        imageurl: document.getElementById("update-imageurl").value
    };

    fetch(`http://localhost:8083/restaurants/updateByName/${name}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedRestaurant)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        getRestaurants(); // Refresh the list after updating
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
        // alert('Update failed: ' + error.message); // Display error message
    });
}