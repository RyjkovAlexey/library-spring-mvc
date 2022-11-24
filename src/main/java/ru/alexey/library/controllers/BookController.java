package ru.alexey.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexey.library.dao.BookDAO;
import ru.alexey.library.models.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.findAll());

        return "books/index";
    }

    @GetMapping("/new")
    public String newBook (Model model) {
        model.addAttribute("book", new Book());

        return "books/new";
    }
}
