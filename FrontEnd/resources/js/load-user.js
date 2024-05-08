//get all user 
$(document).ready(function () {
    // Fetch users from the API
    $.ajax({
        url: 'http://localhost:8080/user/view',
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            if (response.code === "00") {
                // If successful, loop through the users and add them to the table
                response.content.forEach(function (user) {
                    $('#managementTable').append(
                        '<tr>' +
                        '<td>' + user.id + '</td>' +
                        '<td>' + user.username + '</td>' +
                        '<td>' + user.password + '</td>' +
                        '<td>' + user.email + '</td>' +
                        '<td>' + user.name + '</td>' +
                        '<td>' + user.surname + '</td>' +
                        '<td>' + user.age + '</td>' +
                        '<td>' + user.gender + '</td>' +
                        '<td>' + user.likedCategories + '</td>' +
                        '<td>' +
                        '<button type="button" class="btn btn-primary editButton" data-toggle="modal" data-target="#userUpdateModal" data-user-id="'+user.id+'">Edit</button>' +
                        '<button type="button" class="btn btn-primary button-red deleteButton" data-toggle="modal" data-target="#deleteModal" data-user-id="'+user.id+'">Delete</button>' +
                        '</td>' +
                        '</tr>'
                    );
                });
            } else {
                // Handle error
                console.error("Failed to fetch users: " + response.message);
            }
        },
        error: function (xhr, status, error) {
            // Handle error
            console.error("Failed to fetch users: " + error);
        }
    });
    
    // Add user form submission
    $("#userForm").submit(function (event) {
        // Prevent default form submission
        event.preventDefault();

        // Gather user data from form
        var userData = {
            "username": $("#username").val(),
            "password": $("#password").val(),
            "email": $("#email").val(),
            "name": $("#name").val(),
            "surname": $("#surname").val(),
            "age": $("#age").val(),
            "gender": $("input[name='gender']:checked").val()
        };

        // Send user data to the backend
        $.ajax({
            url: 'http://localhost:8080/user/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: function (response) {
                // Handle success
                console.log("User added successfully");
                // Reset form
                $("#userForm")[0].reset();
            },
            error: function (xhr, status, error) {
                // Handle error
                console.error("Failed to add user: " + error);
            }
        });
    });
});
