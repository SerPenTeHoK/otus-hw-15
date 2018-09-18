package ru.sergey_gusarov.hw15.domain.books;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "book")
public class Book {
    @Id
    @Indexed
    private String id;
    @Indexed
    private String title;
    private List<Genre> genres = new ArrayList<>();
    // А как вы предпочитаете? set или list - по логике должен быть set, но удобней работать с list.
    @DBRef
    private List<Author> authors = new ArrayList<>();
    private List<BookComment> bookComments = new ArrayList<>();

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(60);
        sb.append("Book{ id=")
                .append(getId())
                .append(", title='")
                .append(getTitle())
                .append("'");
        sb.append(getAuthors());
        sb.append(getGenres());
        sb.append(getBookComments());
        return sb.toString();
    }
}
