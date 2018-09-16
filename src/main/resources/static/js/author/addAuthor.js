$(document).ready(function() {
    $("#addAuthor").submit(function (event) {
        event.preventDefault();
        addAuthor();
    });
    function addAuthor(){
        var formData = {
            name :  $("#name").val(),
        }
        $.ajax({
            type : "POST",
            url : "/addAuthor",
            data : JSON.stringify(formData),
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            success : function(result) {
                $("#name").innerHTML = "";
                markupAuthorsTable(result);
                console.log("success add author!");
            },
            error : function(e) {
                console.log("ERROR: ", e);
                alert('Duplicate name = ' + $("#name").val());
            }
        });
    }
})