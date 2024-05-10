$(document).ready(function(){
    $('.searchInput').keyup(function(){
        let value = $(this).val().toLowerCase();
        $('.col-md-4').each(function() {
            if ($(this).find('p.card-text').text().toLowerCase().includes(value)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
});


$(document).ready(function() {
    // Listen for form submission
    $('#addModal form').submit(function(e) {
        e.preventDefault(); // Prevent default form submission

        // Retrieve form data
        var name = $('#name').val();
        var description = $('#description').val();
        var category = $('#category').val();
        var ending = $('#ending').val();
        var price = parseFloat($('#price').val());
        var imageFile = $('#file')[0].files[0]; // Get the selected image file
        console.log(imageFile); // Log image file for debugging

        // Check if a file is selected
        if (!imageFile) {
            alert('Please select an image.');
            return;
        }

        // Create FormData object to capture form data including file
        var formData = new FormData();
        formData.append('name', name);
        formData.append('description', description);
        formData.append('category', category);
        formData.append('ending', ending);
        formData.append('price', price);
        formData.append('imageFile', imageFile.name); // Append the image file to FormData

        // Display FormData object in the console (for debugging)
        for (var pair of formData.entries()) {
            console.log(pair[0] + ', ' + pair[1]);
        }

        // Make POST request to the API using FormData for file upload
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/listing/add',
            data: formData, // Send FormData object directly
            contentType: false, // Important: prevent jQuery from setting contentType
            processData: false, // Important: prevent jQuery from processing data
            success: function(response) {
                console.log(response); // Log response for debugging
                alert('Listing added successfully!');
                // Optionally, redirect to another page after successful submission
                // window.location.href = 'success.html';
            },
            error: function(xhr, status, error) {
                console.error(error); // Log error for debugging
                alert('Failed to add listing. Please try again.');
            }
        });
    });
});

