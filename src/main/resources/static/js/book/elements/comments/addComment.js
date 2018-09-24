$(document).ready(function() {
    $("#addCommentForm").submit(function (event) {
        event.preventDefault();
        addComment();
    });
    function addComment(){
        var formData = {
            text :  $("#text").val(),
        }
        $.ajax({
            type : "POST",
            url : "/addCommentToBook/" + window.book.id,
            data : JSON.stringify(formData),
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            success : function(result) {
                $("#name").innerHTML = "";
                markupCommentTable(result);
                console.log("success add comment!");
            },
            error : function(e) {
                console.log("ERROR: ", e);
                alert("ERROR: "+ e);
            }
        });
    }
})