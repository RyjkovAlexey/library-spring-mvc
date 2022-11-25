package ru.alexey.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexey.library.dao.PersonDAO;
import ru.alexey.library.models.Person;
import ru.alexey.library.utils.PersonValidator;

import javax.validation.Valid;

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

    @GetMapping("/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("person", personDAO.findById(id));

        return "people/edit";
    }

    @GetMapping("/{id}")
    public String show(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personDAO.findById(id));

        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);

        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("Person")
            @Valid Person person,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        validator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.update(id, person);

        return "redirect:/people";
    }
}
