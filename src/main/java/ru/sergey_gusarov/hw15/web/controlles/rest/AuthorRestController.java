package ru.sergey_gusarov.hw15.web.controlles.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sergey_gusarov.hw15.domain.books.Author;
import ru.sergey_gusarov.hw15.domain.books.Book;
import ru.sergey_gusarov.hw15.exception.NotFoundException;
import ru.sergey_gusarov.hw15.service.books.AuthorService;
import ru.sergey_gusarov.hw15.service.books.BookService;

import java.util.List;

@Controller
public class AuthorRestController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public AuthorRestController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping("/addAuthor")
    public List<Author> addAuthor(@RequestBody Author author){
        authorService.save(author);
        return authorService.findAll();
    }

    @DeleteMapping("/deleteAuthor/{authorId}")
    public List<Author> deleteAuthor(@PathVariable String authorId){
        authorService.deleteById(authorId);
        return authorService.findAll();
    }

    @PutMapping("/editAuthor")
    public Author editAuthor(@RequestBody Author author){
        Author authorFromDb = authorService.getById(author.getId()).orElseThrow(NotFoundException::new);
        authorFromDb.setName(author.getName());
        authorService.save(authorFromDb);
        return authorService.getById(authorFromDb.getId()).orElseThrow(NotFoundException::new);
    }

}
