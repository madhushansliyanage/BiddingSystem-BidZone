// home.js

// Function to fetch and display bid data
function fetchAndDisplayBids(userId) {
    // Construct the API URL using the provided userId
    const apiUrl = `http://localhost:8080/bid/highestbidaccordingtouser/${userId}/${getCurrentDateTime()}`;

    // Make an AJAX request to the API
    $.ajax({
        url: apiUrl,
        type: "GET",
        success: function(response) {
            // Check if the API response is successful
            if (response.code === "00") {
                // Clear existing table rows
                $("#bidTableBody").empty();

                // Iterate over the bid items in the response content
                response.content.forEach(function(bid) {
                    // Create a new table row and populate it with bid data
                    const row = `
                        <tr>
                            <td>${bid.bidId}</td>
                            <td>${bid.listingId}</td>
                            <td>${bid.listingName}</td>
                            <td>${bid.listingCategory}</td>
                            <td>${bid.listingDescription}</td>
                            <td>${formatDateTime(bid.listingEnding)}</td>
                            <td>${formatDateTime(bid.bidTimestamp)}</td>
                            <td>${bid.bidPrice}</td>
                            <td><button class="btn btn-primary" onclick="processBid(${bid.bidId})">Pay</button></td>
                        </tr>
                    `;

                    // Append the row to the table body
                    $("#bidTableBody").append(row);
                });
            } else {
                console.error("Failed to fetch bid data:", response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error("API request failed:", error);
        }
    });
}

// Function to get the current date and time in the required format
function getCurrentDateTime() {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    const hours = String(currentDate.getHours()).padStart(2, '0');
    const minutes = String(currentDate.getMinutes()).padStart(2, '0');
    const seconds = String(currentDate.getSeconds()).padStart(2, '0');

    // Format the date-time string in the required format for your API
    const currentDateTimeString = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;

    return currentDateTimeString;
}

// Function to format date and time from ISO format to a readable format
function formatDateTime(dateTimeString) {
    const dateTime = new Date(dateTimeString);
    return dateTime.toLocaleString(); // Convert to local date and time format
}

// Function to handle processing a bid (e.g., opening a modal or performing an action)
function processBid(bidId) {
    console.log("Processing bid:", bidId);
}

// Example usage:
$(document).ready(function() {
    // Retrieve userId from localStorage
    const userId = localStorage.getItem("userid");
    console.log("User ID:", userId);

    // Fetch and display bids for the logged-in user
    fetchAndDisplayBids(userId);
});
