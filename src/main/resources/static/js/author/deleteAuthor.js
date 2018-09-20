 function deleteAuthor(authorId){
        var formData = {
            id: authorId
        }
        $.ajax({
            type : "DELETE",
            contentType : "application/json",
            url : "/deleteAuthor/" + authorId,
            data : JSON.stringify(formData),
            dataType : "json",
            success : function(result) {
                console.log(result);
                markupAuthorsTable(result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
}