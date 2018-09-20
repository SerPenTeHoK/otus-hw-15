function deleteAuthorFromBook(authorId){
    event.preventDefault();

    var formData = {
        authorId: authorId,
        bookid: window.book.id
    }
    $.ajax({
        type : "DELETE",
        contentType : "application/json",
        url : "/deleteAuthorFromBook/" + window.book.id,
        data : JSON.stringify(formData),
        dataType : "json",
        success : function(result) {
            console.log(result);
            markupAuthorTable(result);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}