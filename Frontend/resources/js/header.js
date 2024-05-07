document.addEventListener('DOMContentLoaded', function() {
    // Retrieve stored username from localStorage
    var storedUsername = localStorage.getItem('name');

    // Check if the username is present in localStorage
    if (storedUsername) {
        // Get a reference to the <span> element inside the <a> element
        var usernamePlaceholder = document.getElementById('username-placeholder');

        // Update the text content of the <span> element with the retrieved username
        if (usernamePlaceholder) {
            usernamePlaceholder.textContent = storedUsername + "!";
        } else {
            console.error("Element with ID 'username-placeholder' not found");
        }
    } else {
        console.error("Username not found in localStorage");
    }
});