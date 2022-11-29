package ru.alexey.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alexey.library.models.Book;
import ru.alexey.library.models.Person;
import ru.alexey.library.utils.BookRowMapper;
import ru.alexey.library.utils.PersonRowMapper;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO implements Dao<Person> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    @Override
    public Optional<Person> findById(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM person WHERE id=?",
                        new PersonRowMapper(), new Object[]{id}
                )
                .stream()
                .findAny();
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person (name, year_of_birth) VALUES (?,?)",
                person.getName(), person.getYearOfBirth()
        );
    }

    @Override
    public void update(int id, Person updated) {
        jdbcTemplate.update(
                "UPDATE person SET name=?, year_of_birth=? WHERE id=?",
                updated.getName(), updated.getYearOfBirth(), id
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Optional<Person> findByName(String name) {
        return jdbcTemplate.query(
                        "SELECT * FROM person WHERE name=?",
                        new PersonRowMapper(), new Object[]{name}
                )
                .stream()
                .findAny();
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE owner_id=?",
                new BookRowMapper(),
                new Object[]{id}
        );
    }
}