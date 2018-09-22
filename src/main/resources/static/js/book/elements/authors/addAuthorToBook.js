$(document).ready(function() {
    $("#addAuthorForm").submit(function (event) {
        event.preventDefault();
        addAuthorToBook();
    });
    function addAuthorToBook(){
        var formData = {
            name :  "aaa",
        }
        $.ajax({
            type : "POST",
            url : "/addAuthorToBook/" + window.book.id,
            data : JSON.stringify(formData),
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            success : function(result) {
                markupCommentTable(result);
                console.log("success add author!");
            },
            error : function(e) {
                console.log("ERROR: ", e);
                alert("ERROR: "+ e);
            }
        });
    }
})