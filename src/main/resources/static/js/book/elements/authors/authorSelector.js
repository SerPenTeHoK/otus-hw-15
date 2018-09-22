function markupAuthorSelectorForBook(authorsData){
    if(authorsData){
        var authorsSelectorData = authorsData;
        var authorsSelector = document.getElementById("comboBoxAuthor");
        authorsSelector.parentNode.removeChild(authorsSelector);
    }else {
        var authorsSelectorData = window.allAuthors;
    }
    var comboBoxAuthorDiv = document.getElementById("comboBoxAuthorDiv");
    var comboBoxAuthors = document.createElement("select");
    comboBoxAuthors.setAttribute("id", "comboBoxAuthor");
    comboBoxAuthors.setAttribute("name", "comboBoxAuthor");
    authorsSelectorData.forEach(function(rowData) {
        var optionAuthor = document.createElement("option");
        optionAuthor.text = rowData["name"];
        optionAuthor.value = rowData["id"];
        comboBoxAuthors.appendChild(optionAuthor);
    });
    comboBoxAuthorDiv.appendChild(comboBoxAuthors);
}