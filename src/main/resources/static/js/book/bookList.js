function getBooks(){
    var resultBook = $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/books",
        data : "",
        dataType : "json",
        success : function(result) {
            console.log("success table books!");
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        async: false
    });
    return resultBook.responseText;
}