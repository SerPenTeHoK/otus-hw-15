$(document).ready(function() {
    $("#editAuthorForm").submit(function (event) {
        event.preventDefault();
        editAuthor();
    });
    function editAuthor(){
        var formData = {
            id : $("#id").val(),
            name : $("#name").val()
        }
        $.ajax({
            type : "PUT",
            contentType : "application/json",
            url : "/editAuthor",
            data : JSON.stringify(formData),
            dataType : "json",
            success : function(result) {
                console.log("success edit author!");
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
})