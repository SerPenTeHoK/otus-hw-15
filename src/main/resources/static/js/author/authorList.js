function createTableAuthors(authorsData) {
    var mainDiv = document.getElementById("mainDiv");
    while (mainDiv.firstChild) {
        mainDiv.removeChild(mainDiv.firstChild);
    }

    var editAuthorCreateForm = document.createElement("FORM");
    editAuthorCreateForm.setAttribute("id", "createAuthorForm");

    authorCreateSecondNameP.innerHTML = "Author secondName: ";

    var authors = JSON.parse(getAuthors());

}