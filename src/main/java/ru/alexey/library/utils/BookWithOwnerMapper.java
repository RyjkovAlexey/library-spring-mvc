package ru.alexey.library.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.alexey.library.models.Book;
import ru.alexey.library.models.BookWithOwner;
import ru.alexey.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookWithOwnerMapper implements RowMapper<BookWithOwner> {
    @Override
    public BookWithOwner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.println(rs.getMetaData().getColumnName(i));
        }

        book.setId(rs.getInt("bookid"));
        book.setName(rs.getString("bookname"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setOwnerId(rs.getInt("owner_id"));

        if (rs.getString("personid") == null) return new BookWithOwner(book, Optional.empty());

        Person person = new Person();

        person.setId(rs.getInt("personid"));
        person.setName(rs.getString("personname"));
        person.setYearOfBirth(rs.getInt("year_of_birth"));

        return new BookWithOwner(book, Optional.of(person));
    }
}
