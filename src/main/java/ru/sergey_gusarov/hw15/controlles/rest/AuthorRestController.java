package ru.sergey_gusarov.hw15.controlles.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sergey_gusarov.hw15.domain.books.Author;
import ru.sergey_gusarov.hw15.exception.NotFoundException;
import ru.sergey_gusarov.hw15.service.books.AuthorService;
import ru.sergey_gusarov.hw15.service.books.BookService;

import java.util.List;

@RestController
public class AuthorRestController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorRestController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping("/addAuthor")
    public List<Author> addAuthor(@RequestBody Author author) {
        authorService.save(author);
        return authorService.findAll();
    }

    @DeleteMapping("/deleteAuthor/{authorId}")
    public List<Author> deleteAuthor(@PathVariable String authorId) {
        authorService.deleteById(authorId);
        return authorService.findAll();
    }

    @PutMapping("/editAuthor")
    public Author editAuthor(@RequestBody Author author) {
        Author authorFromDb = authorService.getById(author.getId()).orElseThrow(NotFoundException::new);
        authorFromDb.setName(author.getName());
        authorService.save(authorFromDb);
        return authorService.getById(authorFromDb.getId()).orElseThrow(NotFoundException::new);
    }

}
