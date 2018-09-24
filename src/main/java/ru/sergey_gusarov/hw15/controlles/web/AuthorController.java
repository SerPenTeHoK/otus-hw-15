package ru.sergey_gusarov.hw15.controlles.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/authors")
    public String listAuthorPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }
    @RequestMapping("/author")
    public String authorPage(@ModelAttribute Author author, Model model) {
        Author authorFromDb = authorService.getById(author.getId()).orElseThrow(NotFoundException::new);
        model.addAttribute("author", authorFromDb);
        authorService.save(authorFromDb);
        return "authorEdit";
    }
}
