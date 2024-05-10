    // Call the function with the listingId from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const listingId = urlParams.get('listingId');
    var listedPrice,highestBid;
    fetchListingData(listingId);
    fetchBidData(listingId);

    async function fetchListingData(listingId) {
    try {
    const response = await fetch(`http://localhost:8080/listing/search-by-id/${listingId}`);
    if (!response.ok) {
    console.error(`Failed to fetch listing data: ${response.status}`);
    return;
}
    const listing = await response.json();
    highestBid=listedPrice=listing.content.price;

    document.getElementById('item-image').src = `file:${listing.content.image}`;
    document.getElementById('item-name').innerHTML = listing.content.name;
    document.getElementById('category-details').innerHTML = listing.content.category;
    document.getElementById('description-details').innerHTML = listing.content.description;
    document.getElementById('ending-datetime').innerHTML = formatDate(listing.content.ending);
    document.getElementById('listed-price').innerHTML = listing.content.price;

    // Passing time-remain element to updateTimeRemaining function
    updateTimeRemaining(listing.content.ending, document.getElementById('time-remain'));
} catch (error) {
    console.error('Error fetching listing data:', error);
}
}

    async function fetchBidData(listId) {
    fetch(`http://localhost:8080/bid/search-by-list-id/${listId}`)
        .then(response => response.json())
        .then(data => {
            if (data.code === "00") {

                const bids = data.content;

                const parentContainer = document.getElementById('bids-for-item');

                console.log(data);

                bids.forEach(bid => {

                    const bidElement = document.createElement('li');
                    bidElement.classList.add('list-group-item');

                    bidElement.innerHTML=bid.price;

                    parentContainer.appendChild(bidElement);

                    if(bid.price>=highestBid){
                        highestBid=bid.price;
                        document.getElementById('your-bid').value = parseInt(highestBid) + 1;
                    }
                });

            } else if (data.code === "01") {
                document.getElementById('bids-for-item').innerHTML = "No Bids";
                console.error("Failed to fetch listings:" + data.message);
            }
            else {
                console.error("Failed to fetch listings:" + data.message);
            }

        }).catch(error => console.error("Error fetching bids:", error));
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
    // document.querySelector('.btn-view').disabled = true;
} else {
    var days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
    var hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

    timeRemainElement.innerHTML = "Time remaining: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s";
}
}, 1000);
}
