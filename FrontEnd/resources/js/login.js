function login() {
    var username = $("#username").val();
    var password = $("#password").val();

    console.log(username);
    console.log(password);

    // Make AJAX request to check user
    $.ajax({
        url: "http://localhost:8080/user/view/" + username + "/" + password,
        type: "GET",
        success: function(response) {
            // Check response from server
            if (response.code==="00") {
                // If user is found, store username and password in localStorage
                localStorage.setItem('username', username);
                localStorage.setItem('password', password);
                localStorage.setItem('userid', response.content.id);
                localStorage.setItem('name', response.content.name);

                // If user is found, redirect or do home page
                window.location.replace('../HTML/home.html');
            } else {
                // If user is not found, show error message
                console.log(response);
                alert("User not found!");
                $(".error-message").text("Invalid username or password.");
            }
        },
        error: function(xhr, status, error) {
            // Handle error
            console.error(xhr.responseText);
            alert("User not found!");
            $(".error-message").text("Invalid username or password.");
        }
    });
}
