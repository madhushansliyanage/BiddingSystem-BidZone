$(document).ready(function(){
    $('.addButton').click(function(){
        $('#modalTitle').text('Add User');
        $('#submitButton').text('Add');
        $('#userForm').attr('action', '/management');
        $('#username').val("");
        $('#password').val("");
        $('#history').prop("checked", false);
        $('#management').prop("checked", false);
    });

    $('.editButton').click(function(){
        $('#modalTitle').text('Edit User');
        $('#submitButton').text('Edit');
        $('#userForm').attr('action', '/management/' + $(this).parents('tr').find('td:eq(0)').html());
        $('#username').val($(this).parents('tr').find('td:eq(1)').html());
        $('#password').val("");
        if ($(this).parents('tr').find('td:eq(3)').html().includes("check")) {
            $('#history').prop("checked", true);
        } else {
            $('#history').prop("checked", false);
        }
        if ($(this).parents('tr').find('td:eq(4)').html().includes("check")) {
            $('#management').prop("checked", true);
        } else {
            $('#management').prop("checked", false);
        }
    });

    // $('.deleteButton').click(function(){
    //     console.log("hi");
    //     $('#deleteForm').attr('action', '/management/delete/' + $(this).parents('tr').find('td:eq(0)').html());
    // });

    // Delete button click event handler
    $(document).on('click', '.deleteButton', function() {
        var userId = $(this).data('user-id'); // Get user ID from data-user-id attribute
        console.log(userId);

        // Confirm deletion
            // Send DELETE request to the server
            $.ajax({
                url: 'http://localhost:8080/user/delete/' + userId,
                type: 'DELETE',
                success: function(response) {
                    // Handle success
                    console.log('User deleted successfully');
                    // Optionally, you can remove the user's row from the table
                    // $(this).parents('tr').remove();
                },
                error: function(xhr, status, error) {
                    // Handle error
                    console.error('Failed to delete user: ' + error);
                }
            });
        
    });

    $('.searchInput').keyup(function(){
        let value = $(this).val().toLowerCase();
        $("#managementTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});


