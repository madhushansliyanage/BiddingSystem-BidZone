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

    $('.deleteButton').click(function(){
        console.log("hi");
        $('#deleteForm').attr('action', '/management/delete/' + $(this).parents('tr').find('td:eq(0)').html());
    });

    $('.searchInput').keyup(function(){
        let value = $(this).val().toLowerCase();
        $("#managementTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});