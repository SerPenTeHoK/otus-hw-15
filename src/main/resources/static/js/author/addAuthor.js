$(document).ready(function() {
    $("#addAuthorForm").submit(function (event) {
        event.preventDefault();
        addAuthor();
    });
    function addAuthor(){
        var formData = {
            name :  $("#name").val(),
        }
        $.ajax({
            type : "POST",
            url : "/author",
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
                alert('Maybe duplicate name = ' + $("#name").val());
            }
        });
    }
})