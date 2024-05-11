// Get current system date and time
const currentDate = new Date();
const year = currentDate.getFullYear();
const month = String(currentDate.getMonth() + 1).padStart(2, '0');
const day = String(currentDate.getDate()).padStart(2, '0');
const hours = String(currentDate.getHours()).padStart(2, '0');
const minutes = String(currentDate.getMinutes()).padStart(2, '0');
const seconds = String(currentDate.getSeconds()).padStart(2, '0');

// Format the date-time string in the required format for your API
const currentDateTimeString = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
console.log(currentDateTimeString);

// Fetch data from API with current system date and time
fetch(`http://localhost:8080/listing/search-by-date/${currentDateTimeString}`)
    .then(response => response.json())
    .then(data => {
        // Check if API response is successful
        if (data.code === "00") {
            // Get the content (listings) array
            const listings = data.content;

            // Get the container element to append listings
            const container = document.getElementById('listings-container');

            console.log(data);
            // Iterate through each listing and create HTML elements
            listings.forEach(listing => {
                // Create HTML elements for listing
                const listingElement = document.createElement('div');
                listingElement.classList.add('col-md-4');

                listingElement.innerHTML = `
                        <div class="card mb-4 shadow-sm">
                            <div>
                              <img src="file:${listing.image}" width="100%" height="400">
                            </div>
                            
                            <div class="card-body">

                              <div class="d-flex justify-content-between align-items-center">
                                <p class="card-text text-center">${listing.name}</p>
                                <p class="card-text text-center">Rs.${listing.price}</p>
                              </div>
                              
                              <div class="d-flex justify-content-between align-items-center">
                                  <div class="btn-group">
                                  <button type="button" class="btn btn-sm btn-outline-secondary btn-view" 
                                  onclick="setListingIdTolocalStorage(${listing.id})">
                              View
                          </button>
                                  </div>
                                  <small class="text-muted">
                                    <span id="ending-time">${formatDate(listing.ending)}</span><br/>
                                    <span id="time-remain" style="color:brown"></span>
                                  </small>

                              </div>
                            </div>
                        </div>
                    `;

                // Append the listing element to the container
                container.appendChild(listingElement);

                // Update time remaining for each listing
                updateTimeRemaining(listing.ending, listingElement.querySelector('#time-remain'));
            });
        } else if (data.code === "01") {
            document.getElementById('listings-container').innerHTML = "NO Active Listings";
            console.error("Failed to fetch listings:" + data.message);
        }
        else {
            console.error("Failed to fetch listings:" + data.message);
        }
    })
    .catch(error => console.error("Error fetching listings:", error));

function setListingIdTolocalStorage(listId){
    console.log(listId);
    localStorage.setItem('listingId',listId);
    location.href = './listing.html';
}

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2); // Adding 1 because month index starts from 0
    const day = ('0' + date.getDate()).slice(-2);
    const hours = ('0' + date.getHours()).slice(-2);
    const minutes = ('0' + date.getMinutes()).slice(-2);
    const seconds = ('0' + date.getSeconds()).slice(-2);

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

// Function to calculate and update remaining time
function updateTimeRemaining(endTimeString, timeRemainElement) {
    // Creating a Date object from the extracted string
    var endTime = new Date(endTimeString);

    var updateInterval = setInterval(function () {
        var now = new Date();
        var timeDiff = endTime - now;

        if (timeDiff <= 0) {
            timeRemainElement.innerHTML = "Expired"; // Change text if time is expired
            clearInterval(updateInterval); // Stop updating time when expired
            document.querySelector('.btn-view').disabled = true;
        } else {
            var days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
            var hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

            timeRemainElement.innerHTML = "Time remaining: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s";
        }
    }, 1000);
}

