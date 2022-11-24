package ru.alexey.library.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.alexey.library.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setOwnerId(rs.getInt("owner_id"));

        return book;
    }
}
