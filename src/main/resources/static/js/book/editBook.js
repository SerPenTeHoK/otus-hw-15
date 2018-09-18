$(document).ready(function() {
    $("#editBookForm").submit(function (event) {
        event.preventDefault();
        editBook();
    });
    function editBook(){
        var formData = {
            id : $("#id").val(),
            name : $("#title").val(),
        }
        $.ajax({
            type : "PUT",
            contentType : "application/json",
            url : "/editBook",
            data : JSON.stringify(formData),
            dataType : "json",
            success : function(result) {
                console.log("success edit book!");
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
})