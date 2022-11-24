package ru.alexey.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexey.library.dao.PersonDAO;
import ru.alexey.library.models.Person;
import ru.alexey.library.utils.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator validator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator validator) {
        this.personDAO = personDAO;
        this.validator = validator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.findAll());

        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("person", personDAO.findById(id));

        return "people/edit";
    }
}
