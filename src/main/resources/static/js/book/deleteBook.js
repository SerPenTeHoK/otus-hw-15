 function deleteBook(idDeleteBook){
        var formData = {
            id: idDeleteBook
        }
        $.ajax({
            type : "DELETE",
            contentType : "application/json",
            url : "/deleteBook/" + idDeleteBook,
            data : JSON.stringify(formData),
            dataType : "json",
            success : function(result) {
                console.log(result);
                markupBooksTable(result);
            },
            error : function(e) {
                console.error("ERROR: ", e);
            }
        });
}