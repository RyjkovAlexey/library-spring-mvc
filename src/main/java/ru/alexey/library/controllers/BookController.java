package ru.alexey.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexey.library.dao.BookDAO;
import ru.alexey.library.dao.PersonDAO;
import ru.alexey.library.models.Book;
import ru.alexey.library.models.BookWithOwner;
import ru.alexey.library.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, PersonDAO personDAO1) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO1;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.findAll());

        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());

        return "books/new";
    }

    @PostMapping()
    public String create(
            @Valid
            @ModelAttribute("book") Book book,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(
            Model model,
            @PathVariable("id") int id
    ) {
        Optional<BookWithOwner> bookWithOwnerOptional = bookDAO.findByByIdWithOwner(id);

        BookWithOwner bookWithOwner = bookWithOwnerOptional.get();

        Book book = bookWithOwner.getBook();
        Optional<Person> person = bookWithOwner.getPerson();

        model.addAttribute("book", book);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            model.addAttribute("people", personDAO.findAll());
            model.addAttribute("person", new Person());
        }

        return "/books/show";
    }

    @PatchMapping("/{id}/back")
    public String back(
            @PathVariable("id") int id
    ) {
        Book book = bookDAO.findById(id).get();
        book.setOwnerId(null);
        bookDAO.update(id, book);

        return String.format("/{%s}", book.getId());
    }

    @PatchMapping("/{id}/give")
    public String give(
            @PathVariable("id") int id,
            @ModelAttribute("book") Book book
    ) {
        book.setOwnerId(id);

        bookDAO.update(book.getId(), book);

        return "redirect:/books";
    }
}
