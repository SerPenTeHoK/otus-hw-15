$(document).ready(function() {
    $("#addBookForm").submit(function (event) {
        event.preventDefault();
        addBook();
    });
    function addBook(){
        var formData = {
            title :  $("#title").val(),
        }
        $.ajax({
            type : "POST",
            url : "/addBook",
            data : JSON.stringify(formData),
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            success : function(result) {
                $("#title").innerHTML = "";
                markupBooksTable(result);
                console.log("success add book!");
            },
            error : function(e) {
                console.log("ERROR: ", e);
                alert('Duplicate name = ' + $("#name").val());
            }
        });
    }
})