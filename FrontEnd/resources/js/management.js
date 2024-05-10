$(document).ready(function () {
  $(".addButton").click(function () {
    $("#modalTitle").text("Add User");
    $("#submitButton").text("Add");
    $("#userForm").attr("action", "/management");
    $("#username").val("");
    $("#password").val("");
    $("#history").prop("checked", false);
    $("#management").prop("checked", false);
  });

  $(".editButton").click(function () {
    $("#modalTitle").text("Edit User");
    $("#submitButton").text("Edit");
    $("#userForm").attr(
      "action",
      "/management/" + $(this).parents("tr").find("td:eq(0)").html()
    );
    $("#username").val($(this).parents("tr").find("td:eq(1)").html());
    $("#password").val("");
    if ($(this).parents("tr").find("td:eq(3)").html().includes("check")) {
      $("#history").prop("checked", true);
    } else {
      $("#history").prop("checked", false);
    }
    if ($(this).parents("tr").find("td:eq(4)").html().includes("check")) {
      $("#management").prop("checked", true);
    } else {
      $("#management").prop("checked", false);
    }
  });

  // Delete button click event handler
  $(document).on("click", ".deleteButton", function () {
    var userId = $(this).data("user-id"); // Get user ID from data-user-id attribute
    console.log(userId);

    // Show delete confirmation modal
    $("#deleteConfirmationModal").modal("show");

    // Handle delete confirmation
    $("#confirmDeleteBtn").on("click", function () {
      // Send DELETE request to the server
      $.ajax({
        url: "http://localhost:8080/user/delete/" + userId,
        type: "DELETE",
        success: function (response) {
          // Handle success
          console.log("User deleted successfully");

          // Close the modal
          $("#deleteConfirmationModal").modal("hide");

          // Automatically refresh the page
          location.reload(); // Reloads the current page

          // Alternatively, you can remove the user's row from the table without page reload
          // $(this).closest("tr").remove();
        },
        error: function (xhr, status, error) {
          // Handle error
          console.error("Failed to delete user: " + error);

          // Close the modal on error (optional)
          $("#deleteConfirmationModal").modal("hide");
        },
      });
    });
  });

  // Handle click event on the edit button
  $(document).on("click", ".editButton", function () {
    // Get the user ID from the data attribute
    var userId = $(this).data("user-id");

    // Make an AJAX request to fetch user data
    $.ajax({
      url: "http://localhost:8080/user/view/" + userId,
      type: "GET",
      success: function (user) {
        // Populate the form fields with the retrieved user data
        localStorage.setItem("updateUserId", userId);
        $("#usernameEdit").val(user.content.username);
        $("#passwordEdit").val(user.content.password);
        $("#emailEdit").val(user.content.email);
        $("#nameEdit").val(user.content.name);
        $("#surnameEdit").val(user.content.surname);
        $("#ageEdit").val(user.content.age);

        // Set the gender radio button based on user's gender
        if (user.content.gender === "Male") {
          $("#maleRadioEdit").prop("checked", true);
        } else if (user.content.gender === "Female") {
          $("#femaleRadioEdit").prop("checked", true);
        }

        // Show the modal
        $("#userUpdateModal").modal("show");
      },
      error: function () {
        alert("Failed to fetch user data.");
        localStorage.removeItem("updateUserId");
      },
    });
  });

  $(".searchInput").keyup(function () {
    let value = $(this).val().toLowerCase();
    $("#managementTable tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
    });
  });
});
