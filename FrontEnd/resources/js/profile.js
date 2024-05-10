$(document).ready(function() {
    $('.multiSelect2').select2({
        placeholder: "Select Categories",
        width: 'resolve'
    }).val(['1', '2']).trigger('change');
    if ($('#likedCategoriesValues').val()) {
        let likedCategoriesValuesList = $('#likedCategoriesValues').val().split(",");
        $('.multiSelect2').val(likedCategoriesValuesList).trigger('change');
    }
});