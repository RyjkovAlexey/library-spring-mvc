package ru.alexey.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alexey.library.models.Book;
import ru.alexey.library.utils.BookRowMapper;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO implements Dao<Book> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
    }

    @Override
    public Optional<Book> findById(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM book WHERE id=?",
                        new BookRowMapper(),
                        new Object[]{id}
                )
                .stream()
                .findAny();
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book (name, author, year) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear()
        );
    }

    @Override
    public void update(int id, Book updated) {
        jdbcTemplate.update(
                "UPDATE book SET name=?, author=?, year=? WHERE id=?",
                updated.getName(), updated.getAuthor(), updated.getYear(), id
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
