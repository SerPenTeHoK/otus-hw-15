package ru.sergey_gusarov.hw15.controlles.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public List<Genre> deleteBook(@PathVariable String bookId, @RequestBody HashMap requesBody) {
        Book bookFromDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        Genre genreForDel = bookFromDb.getGenres().stream()
                .filter(p -> p.getName().equals(requesBody.get("genreName")))
                .findFirst().get();
        bookFromDb.getGenres().remove(genreForDel);
        bookService.save(bookFromDb);
        Book bookFromUpdateDb = bookService.findById(bookId).orElseThrow(NotFoundException::new);
        return bookFromUpdateDb.getGenres();
    }

}
