package ru.sergey_gusarov.hw15.controlles.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sergey_gusarov.hw15.domain.books.Author;
import ru.sergey_gusarov.hw15.domain.books.Book;
import ru.sergey_gusarov.hw15.domain.books.BookComment;
import ru.sergey_gusarov.hw15.domain.books.Genre;
import ru.sergey_gusarov.hw15.exception.NotFoundException;
import ru.sergey_gusarov.hw15.service.books.AuthorService;
import ru.sergey_gusarov.hw15.service.books.BookService;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping("/addBook")
    public List<Book> addBook(@RequestBody Book book) {
        bookService.save(book);
        return bookService.findAll();
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public List<Book> deleteBook(@PathVariable String bookId) {
        bookService.deleteById(bookId);
        return bookService.findAll();
    }

    @PutMapping("/editBook")
    public Book editBook(@RequestBody Book book) {
        Book bookFromDb = bookService.getById(book.getId()).orElseThrow(NotFoundException::new);
        bookFromDb.setTitle(book.getTitle());
        bookService.save(bookFromDb);
        return bookService.getById(bookFromDb.getId()).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/deleteGenreFromBook/{bookId}")
    public List<Genre> deleteBook(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        Book bookFromDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        Genre genreForDel = bookFromDb.getGenres().stream()
                .filter(p -> p.getName().equals(requestBody.get("genreName")))
                .findFirst().get();
        bookFromDb.getGenres().remove(genreForDel);
        bookService.save(bookFromDb);
        Book bookFromUpdateDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        return bookFromUpdateDb.getGenres();
    }

    @DeleteMapping("/deleteCommentFromBook/{bookId}")
    public List<BookComment> deleteComment(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        Book bookFromDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        BookComment commentForDel = bookFromDb.getBookComments().stream()
                .filter(p -> p.getText().equals(requestBody.get("commentText")))
                .findFirst().get();
        bookFromDb.getBookComments().remove(commentForDel);
        bookService.save(bookFromDb);
        Book bookFromUpdateDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        return bookFromUpdateDb.getBookComments();
    }

    @DeleteMapping("/deleteAuthorFromBook/{bookId}")
    public List<Author> deleteAuthor(@PathVariable String bookId, @RequestBody HashMap requestBody) {
        Book bookFromDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        Author authorForDel = bookFromDb.getAuthors().stream()
                .filter(p -> p.getId().equals(requestBody.get("authorId")))
                .findFirst().get();
        bookFromDb.getAuthors().remove(authorForDel);
        bookService.save(bookFromDb);
        Book bookFromUpdateDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        return bookFromUpdateDb.getAuthors();
    }
}
